package org.usfirst.frc.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.CommandDriveDefault;
import org.usfirst.frc.team537.robot.helpers.Maths;
import org.usfirst.frc.team537.robot.helpers.PID;
import org.usfirst.frc.team537.robot.subsystems.SwerveModule.SwerveMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SubsystemDrive extends Subsystem implements PIDOutput {
	private SwerveModule backRight = new SwerveModule(
		"Back Right", RobotMap.CAN.DRIVE_BACK_RIGHT_ANGLE, RobotMap.CAN.DRIVE_BACK_RIGHT_DRIVE,
		new PID(5.2, 0.0, 9.8)
	);
	private SwerveModule frontRight = new SwerveModule(
		"Front Right", RobotMap.CAN.DRIVE_FRONT_RIGHT_ANGLE, RobotMap.CAN.DRIVE_FRONT_RIGHT_DRIVE,
		new PID(5.2, 0.0, 9.8)
	);
	private SwerveModule frontLeft = new SwerveModule(
		"Front Left", RobotMap.CAN.DRIVE_FRONT_LEFT_ANGLE, RobotMap.CAN.DRIVE_FRONT_LEFT_DRIVE,
		new PID(5.2, 0.0, 9.8)
	);
	private SwerveModule backLeft = new SwerveModule(
		"Back Left", RobotMap.CAN.DRIVE_BACK_LEFT_ANGLE, RobotMap.CAN.DRIVE_BACK_LEFT_DRIVE,
		new PID(5.2, 0.0, 9.8)
	);
	private PIDController controllerRotate;

	public SubsystemDrive() {
		setName("Drive");
		Timer timerDashboard = new Timer();
		timerDashboard.schedule(new TimerTask() {
			@Override
			public void run() {
				dashboard();
			}
		}, 0, 100);
	}

	@Override
	protected void initDefaultCommand() {
		this.controllerRotate = new PIDController(0.01, 0.0, 0.002, Robot.subsystemGyro, Robot.subsystemDrive);
		this.controllerRotate.setInputRange(0.0, 360.0);
		this.controllerRotate.setOutputRange(-0.5, 0.5);
		this.controllerRotate.setPercentTolerance(0.07);
		this.controllerRotate.setContinuous();
		this.controllerRotate.disable();
		
		setDefaultCommand(new CommandDriveDefault());
	}

	public void dashboard() {
		SmartDashboard.putBoolean("Drive At Target", isAtTarget());
		SmartDashboard.putBoolean("Drive At Angle", isAtAngle(10.0));
		backLeft.dashboard();
		backRight.dashboard();
		frontLeft.dashboard();
		frontRight.dashboard();
	}

	public void setTarget(double gyro, double rotation, double strafe, double forward) {
		if (controllerRotate.isEnabled()) {
			rotation = controllerRotate.get();
		}

		boolean driverControl = isDriverControl();
		double fwd2 = (forward * Math.cos(gyro)) + strafe * Math.sin(gyro);
		double str2 = (-forward * Math.sin(gyro)) + strafe * Math.cos(gyro);

		double r = RobotMap.Robot.RATIO / 2.0;
		double a = str2 - rotation * ((RobotMap.Robot.LENGTH / r) * 0.5);
		double b = str2 + rotation * ((RobotMap.Robot.LENGTH / r) * 0.5);
		double c = fwd2 - rotation * ((RobotMap.Robot.WIDTH / r) * 0.5);
		double d = fwd2 + rotation * ((RobotMap.Robot.WIDTH / r) * 0.5);

		double frs = Math.sqrt((b * b) + (c * c));
		double fls = Math.sqrt((a * a) + (c * c));
		double bls = Math.sqrt((a * a) + (d * d));
		double brs = Math.sqrt((b * b) + (d * d));
		
		double fra = Math.atan2(b, c) * (180.0 / Math.PI);
		double fla = Math.atan2(b, d) * (180.0 / Math.PI);
		double bla = Math.atan2(a, d) * (180.0 / Math.PI);
		double bra = Math.atan2(a, c) * (180.0 / Math.PI);

		double maxSpeed = Maths.maxValue(frs, fls, bls, brs);

		if (maxSpeed > 1.0) {
			frs /= maxSpeed;
			fls /= maxSpeed;
			bls /= maxSpeed;
			brs /= maxSpeed;
		}
		
		if ((driverControl && !isAtAngle(100.0)) || (!isDriverControl() && !isAtAngle(8.0))) {
			frs = 0.0;
			fls = 0.0;
			bls = 0.0;
			brs = 0.0;
		}

		frontRight.setTarget(fra, frs * RobotMap.Robot.DRIVE_SPEED, driverControl);
		frontLeft.setTarget(fla, fls * RobotMap.Robot.DRIVE_SPEED, driverControl);
		backLeft.setTarget(bla, bls * RobotMap.Robot.DRIVE_SPEED, driverControl);
		backRight.setTarget(bra, brs * RobotMap.Robot.DRIVE_SPEED, driverControl);
	}

	public void setTarget(double gyro, double angle, double forward) {
		double f = Maths.normalizeAngle(angle - gyro);
		
		frontRight.setTarget(f, forward, false);
		frontLeft.setTarget(f, forward, false);
		backLeft.setTarget(f, forward, false);
		backRight.setTarget(f, forward, false);
	}

	@Override
	public void pidWrite(double output) {
	}

	public void setMode(SwerveModule.SwerveMode swerveMode) {
		frontRight.setMode(swerveMode);
		frontLeft.setMode(swerveMode);
		backLeft.setMode(swerveMode);
		backRight.setMode(swerveMode);
	}

	public PIDController getControllerRotate() {
		return controllerRotate;
	}
	
	public void setControllerRotate(double setpoint) {
		controllerRotate.reset();
		controllerRotate.setSetpoint(setpoint);
		controllerRotate.enable();
	}

	public boolean isAtTarget() {
		return frontRight.isAtTarget() && frontLeft.isAtTarget() && backLeft.isAtTarget() && backRight.isAtTarget();
	}
	
	public boolean isAtAngle(double error) {
		return frontRight.isAtAngle(error) && frontLeft.isAtAngle(error) && backLeft.isAtAngle(error) && backRight.isAtAngle(error);
	}
	
	public boolean isDriverControl() {
		return frontRight.getMode() == SwerveMode.ModeSpeed;
	}
	
	public void reset() {
		backLeft.reset();
		backRight.reset();
		frontLeft.reset();
		frontRight.reset();
	}
	
	public void stop() {
		controllerRotate.disable();
		backLeft.stop();
		backRight.stop();
		frontLeft.stop();
		frontRight.stop();
	}
}
