package org.usfirst.frc.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.DriveArcade;
import org.usfirst.frc.team537.robot.commands.DriveDefault;
import org.usfirst.frc.team537.robot.toolbox.Maths;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A robot subsystem that controls a "tank" drive system, providing control modes for 6 CAN Talons.
 */
public class Drive extends Subsystem implements PIDOutput {
	private final TalonSRX driveLeft3 = new TalonSRX(RobotMap.CAN.DRIVE_LEFT_MINI);
	private final TalonSRX driveLeft2 = new TalonSRX(RobotMap.CAN.DRIVE_LEFT_NORMAL);
	private final TalonSRX driveLeft1 = new TalonSRX(RobotMap.CAN.DRIVE_LEFT_MASTER);

	private final TalonSRX driveRight3 = new TalonSRX(RobotMap.CAN.DRIVE_RIGHT_MINI);
	private final TalonSRX driveRight2 = new TalonSRX(RobotMap.CAN.DRIVE_RIGHT_NORMAL);
	private final TalonSRX driveRight1 = new TalonSRX(RobotMap.CAN.DRIVE_RIGHT_MASTER);
	
//	private final PIDController anglePID = new PIDController(0.002, 0.0, 0.0, 0.0, Robot.ahrs, this);
	private double setpointLeft;
	private double setpointRight;

	public Drive() {
		driveLeft3.set(ControlMode.Follower, RobotMap.CAN.DRIVE_LEFT_MASTER);
		driveLeft2.set(ControlMode.Follower, RobotMap.CAN.DRIVE_LEFT_MASTER);

		driveLeft1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
	//	driveLeft1.configEncoderCodesPerRev(255);
	//	driveLeft1.setVoltageRampRate(10.0); // 0V to 10V in one second.
	//	driveLeft1.reverseOutput(false);
	//	driveLeft1.reverseSensor(false);
		
		driveRight3.set(ControlMode.Follower, RobotMap.CAN.DRIVE_RIGHT_MASTER);

		driveRight2.set(ControlMode.Follower, RobotMap.CAN.DRIVE_RIGHT_MASTER);

		driveLeft1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
	//	driveRight1.configEncoderCodesPerRev(255);
	//	driveRight1.setVoltageRampRate(10.0); // 0V to 10V in one second.
	//	driveRight1.reverseOutput(false);
	//	driveRight1.reverseSensor(false);
		
		setPIDF(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
	    
	    reset(); ////// TODO: REMOVE //////
	    
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
		if (RobotMap.Driver.ARCADE_DRIVE) {
			setDefaultCommand(new DriveArcade());
		} else {
			setDefaultCommand(new DriveDefault());
		}
	}

	/**
	 * Sets the PID and F for the drive train.
	 * 
	 * @param pl Left P.
	 * @param il Left I.
	 * @param dl Left D.
	 * @param fl Left F.
	 * @param pr Right P.
	 * @param ir Right I.
	 * @param dr Right D.
	 * @param fr Right f.
	 */
	public void setPIDF(double pl, double il, double dl, double fl, double pr, double ir, double dr, double fr) {
		driveLeft1.config_kP(RobotMap.kPIDLoopIdx, pl, RobotMap.kTimeoutMs);
        driveLeft1.config_kI(RobotMap.kPIDLoopIdx, il, RobotMap.kTimeoutMs); 
        driveLeft1.config_kD(RobotMap.kPIDLoopIdx, dl, RobotMap.kTimeoutMs);
        driveLeft1.config_kF(RobotMap.kPIDLoopIdx, fl, RobotMap.kTimeoutMs);
        
        driveRight1.config_kP(RobotMap.kPIDLoopIdx, pr, RobotMap.kTimeoutMs);
        driveRight1.config_kI(RobotMap.kPIDLoopIdx, ir, RobotMap.kTimeoutMs); 
        driveRight1.config_kD(RobotMap.kPIDLoopIdx, dr, RobotMap.kTimeoutMs);
        driveRight1.config_kF(RobotMap.kPIDLoopIdx, fr, RobotMap.kTimeoutMs);
	}

	/**
	 * Drives the drive train from left and right speeds.
	 * 
	 * @param left The input left speed.
	 * @param right The input right speed.
	 */
	public void speed(double speedLeft, double speedRight) {
		// Sets the Talons to the drive at % speeds.
		driveLeft1.set(ControlMode.PercentOutput, setpointLeft = Maths.clamp(speedLeft * RobotMap.Robot.DRIVE_SPEED, -1.0, 1.0));
		driveRight1.set(ControlMode.PercentOutput, setpointRight = Maths.clamp(-speedRight * RobotMap.Robot.DRIVE_SPEED, -1.0, 1.0));
	}

	/**
	 * Drives the drive train from left and right encoder rates.
	 * 
	 * @param left The input left encoder rate.
	 * @param right The input right encoder rate.
	 */
	public void rate(double left, double right) {
		// Sets the Talons to the drive at rates.
		driveLeft1.set(ControlMode.Velocity, setpointLeft = left);
		driveRight1.set(ControlMode.Velocity, setpointRight = -right);
	}

	/**
	 * Drives the drive train to a left and right encoder distance.
	 * 
	 * @param left The input left distance (inches).
	 * @param right The input right distance (inches).
	 */
	public void distance(double left, double right) {
		// Sets the Talons to the drive distances.
		driveLeft1.set(ControlMode.Position, setpointLeft = left * RobotMap.Digital.DRIVE_IN_TO_ENCODER);
		driveRight1.set(ControlMode.Position, setpointRight = -right * RobotMap.Digital.DRIVE_IN_TO_ENCODER);
	}

	/**
	 * A method from PID Source used to output from the angle PID to the drive systems.
	 */
	@Override
	public void pidWrite(double output) {
		SmartDashboard.putNumber("Drive PID Output", output);

		double speedLeft = output * RobotMap.Robot.DRIVE_SPEED;
		double speedRight = output * RobotMap.Robot.DRIVE_SPEED;
		
		// (NEGATIVE -> LEFT), (POSITIVE -> RIGHT).
		driveLeft1.set(ControlMode.PercentOutput, setpointLeft = Math.max(speedLeft, 0.4));
		driveRight1.set(ControlMode.PercentOutput, setpointRight = Math.max(speedRight, 0.4));
	}

	public double getSetpointLeft() {
		return setpointLeft;
	}

	public double getSetpointRight() {
		return setpointRight;
	}
	
	/**
	 * Gets if the target has been met.
	 * 
	 * @return If the target has been met.
	 */
	public boolean atTarget() {
		if (driveLeft1.getControlMode().equals(ControlMode.Position)) { // Distance.
			if (Maths.nearTarget(driveLeft1.getSelectedSensorVelocity(RobotMap.kPIDLoopIdx), getSetpointLeft(), 0.07 * RobotMap.Digital.DRIVE_IN_TO_ENCODER)) {
				if (Maths.nearTarget(driveRight1.getSelectedSensorVelocity(RobotMap.kPIDLoopIdx), getSetpointRight(), 0.07 * RobotMap.Digital.DRIVE_IN_TO_ENCODER)) {
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * Resets the drive train, and sets the encoder positions to 0.
	 */
	public void reset() {
		setPIDF(0.0, 0.0, 0.0, 0.0, 0, 0, 0, 0);
		driveLeft1.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
		driveLeft1.set(ControlMode.PercentOutput, setpointLeft = 0.0);

		driveRight2.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
		driveRight2.set(ControlMode.PercentOutput, setpointRight = 0.0);
	}

	/**
	 * Stops the entire drive train, disables speeds and PIDs.
	 */
	public void stop() {
		driveLeft1.set(ControlMode.PercentOutput, 0.0);
		
		driveRight1.set(ControlMode.PercentOutput, 0.0);
	}

	private void dashboard() {
	//	SmartDashboard.putNumber("Drive Setpoint Left", driveLeft1.getSetpoint());
	//	SmartDashboard.putNumber("Drive Setpoint Right", driveRight1.getSetpoint());

	//	SmartDashboard.putNumber("Drive Error Left", driveLeft1.getError() * 4.0);
	//	SmartDashboard.putNumber("Drive Error Right", driveRight1.getError() * 4.0);

	//	SmartDashboard.putNumber("Drive Encoder Speed Left", driveLeft1.getEncVelocity());
	//	SmartDashboard.putNumber("Drive Encoder Speed Right", driveRight1.getEncVelocity());

	//	SmartDashboard.putNumber("Drive Encoder Pos Left", driveLeft1.getEncPosition());
	//	SmartDashboard.putNumber("Drive Encoder Pos Right", driveRight1.getEncPosition());

	//	SmartDashboard.putNumber("NavX Yaw", Robot.ahrs.getYaw());
	}
}

