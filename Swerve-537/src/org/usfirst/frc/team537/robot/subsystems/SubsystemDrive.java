package org.usfirst.frc.team537.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc.team537.robot.*;
import org.usfirst.frc.team537.robot.commands.CommandDrive;

import java.util.Timer;
import java.util.TimerTask;

public class SubsystemDrive extends Subsystem {
	public static class DriveModule {
		private String name;
		private TalonSRX angle;
		private TalonSRX drive;
		private double angleCurrent;
		private double angleMin, angleMax;
		private int driveSign;

		public DriveModule(String name, TalonSRX angle, TalonSRX drive, double p, double i, double d, boolean inverted, boolean resetEnc) {
			this.name = name;
			this.angle = angle;
			this.drive = drive;
			this.angleCurrent = 0;
			this.angleMin = (int) Float.POSITIVE_INFINITY;
			this.angleMax = (int) Float.NEGATIVE_INFINITY;
			this.driveSign = 1;

			this.angle.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);
			this.angle.setInverted(inverted);
	        this.angle.config_kP(RobotMap.kPIDLoopIdx, p, RobotMap.kTimeoutMs);
	        this.angle.config_kI(RobotMap.kPIDLoopIdx, i, RobotMap.kTimeoutMs); 
	        this.angle.config_kD(RobotMap.kPIDLoopIdx, d, RobotMap.kTimeoutMs);
		//	this.angle.config_kF(RobotMap.kPIDLoopIdx, f, RobotMap.kTimeoutMs);
			
			if (!resetEnc) {
			//	this.angle.setSelectedSensorPosition(this.angle.getPulseWidthPosition() & 0xFFF, 0, RobotMap.kTimeoutMs);
			} else {
				this.angle.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
			}
			
		//	this.angle.enable();

			this.drive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, RobotMap.kTimeoutMs);
			this.drive.setInverted(inverted);
		//	this.drive.enable();
		}

		public void set(double speed, double angle) {
			angle = Maths.normalizeAngle(-angle + 360.0);
			angleCurrent = (this.angle.getSelectedSensorPosition(RobotMap.kPIDLoopIdx) & 0xFFF) / 4096.0f; // getPulseWidthPosition() 
			
			if (angleCurrent < angleMin) {
				angleMin = angleCurrent;
			} else if (angleCurrent > angleMax) {
				angleMax = angleCurrent;
			}

			double t = angle /= 360.0;
			double i = angleCurrent;
			
			double j = i - Math.floor(i);
			double k = (j >= 0.5) ? j - 0.5 : j + 0.5;
			double r = (t >= k) ? t + Math.floor(i) - 1.0 : t + Math.floor(i);
			r = (r < 0.0) ? r + 1 : r;
			
			this.angle.set(ControlMode.Position, r);
			this.drive.set(ControlMode.PercentOutput, driveSign * speed);
		}

		public void dashboard() {
			SmartDashboard.putNumber(name + " Angle", angleCurrent * 360); 
		//	SmartDashboard.putNumber(name + " Drive Speed", drive.getSpeed());
		//	SmartDashboard.putNumber(name + " Drive Position", drive.getEncPosition());
		}

		public void reset() {
		//	drive.setPosition(0.0);
		}

		public void stop() {
			drive.set(ControlMode.PercentOutput, 0.0);
		}
	}

	private DriveModule backRight;
	private DriveModule frontRight;
	private DriveModule frontLeft;
	private DriveModule backLeft;

	public SubsystemDrive() {
		this.backRight = new DriveModule(
				"Back Right",
				new TalonSRX(RobotMap.CAN.DRIVE_BACK_RIGHT_ANGLE),
				new TalonSRX(RobotMap.CAN.DRIVE_BACK_RIGHT_DRIVE),
				-5.0, 0.0, 10.0,
				true,
				true
		);
		this.frontRight = new DriveModule(
				"Front Right",
				new TalonSRX(RobotMap.CAN.DRIVE_FRONT_RIGHT_ANGLE),
				new TalonSRX(RobotMap.CAN.DRIVE_FRONT_RIGHT_DRIVE),
				-5.0, 0.0, 10.0,
				false,
				true
		);
		this.frontLeft = new DriveModule(
				"Front Left",
				new TalonSRX(RobotMap.CAN.DRIVE_FRONT_LEFT_ANGLE),
				new TalonSRX(RobotMap.CAN.DRIVE_FRONT_LEFT_DRIVE),
				-5.0, 0.0, 10.0,
				false,
				true
		);
		this.backLeft = new DriveModule(
				"Back Left",
				new TalonSRX(RobotMap.CAN.DRIVE_BACK_LEFT_ANGLE),
				new TalonSRX(RobotMap.CAN.DRIVE_BACK_LEFT_DRIVE),
				-5.0, 0.0, 10.0,
				false,
				true
		);

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
		setDefaultCommand(new CommandDrive());
	}

	public void dashboard() {
		backLeft.dashboard();
		backRight.dashboard();
		frontLeft.dashboard();
		frontRight.dashboard();
	}

	public void drive(double rotation, double strafe, double forward, double gyro) {
		SmartDashboard.putNumber("Drive Input Rotation", rotation);
		SmartDashboard.putNumber("Drive Input Strafe", strafe);
		SmartDashboard.putNumber("Drive Input Forward", forward);
		SmartDashboard.putNumber("Drive Input Gyro", gyro);

		double fwd2 = (forward * Math.cos(gyro)) + strafe * Math.sin(gyro);
		double str2 = (-forward * Math.sin(gyro)) + strafe * Math.cos(gyro);

		double r = RobotMap.Robot.RATIO / 2.0;
		double a = str2 - rotation * ((RobotMap.Robot.LENGTH / r) * 0.5);
		double b = str2 + rotation * ((RobotMap.Robot.LENGTH / r) * 0.5);
		double c = fwd2 - rotation * ((RobotMap.Robot.WIDTH / r) * 0.5);
		double d = fwd2 + rotation * ((RobotMap.Robot.WIDTH / r) * 0.5);

		SmartDashboard.putNumber("Drive Maths R", r);
		SmartDashboard.putNumber("Drive Maths A", a);
		SmartDashboard.putNumber("Drive Maths B", b);
		SmartDashboard.putNumber("Drive Maths C", c);
		SmartDashboard.putNumber("Drive Maths D", d);

		double frs = Math.sqrt((b * b) + (c * c));
		double fls = Math.sqrt((a * a) + (c * c));
		double bls = Math.sqrt((a * a) + (d * d));
		double brs = Math.sqrt((b * b) + (d * d));
		
		double fra = Math.atan2(b, c) * (80.0 / Math.PI);
		double fla = Math.atan2(b, d) * (80.0 / Math.PI);
		double bla = Math.atan2(a, d) * (80.0 / Math.PI);
		double bra = Math.atan2(a, c) * (80.0 / Math.PI);

		double maxSpeed = Maths.maxValue(frs, fls, bls, brs);

		if (maxSpeed > 1.0) {
			frs /= maxSpeed;
			fls /= maxSpeed;
			bls /= maxSpeed;
			brs /= maxSpeed;
		}

		frontRight.set(frs, fra);
		frontLeft.set(fls, fla);
		backLeft.set(bls, bla);
		backRight.set(brs, bra);
	}

	public void reset() {
		//	Robot.ahrs.reset();
		backLeft.reset();
		backRight.reset();
		frontLeft.reset();
		frontRight.reset();
	}
	
	public void stop() {
		backLeft.stop();
		backRight.stop();
		frontLeft.stop();
		frontRight.stop();
	}
}
