package org.usfirst.frc.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SubsystemLift extends Subsystem {
	private TalonSRX talonLift = new TalonSRX(RobotMap.CAN.LIFT);
	private DigitalInput switchBottom = new DigitalInput(1);
	private DigitalInput switchTop = new DigitalInput(0);
	private double currentPosition;

	public SubsystemLift() {
		setName("Lift");
		
		talonLift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
		talonLift.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 10, RobotMap.kTimeoutMs);
		talonLift.setSensorPhase(true); 
		talonLift.configForwardSoftLimitThreshold(RobotMap.Robot.LIFT_LIMIT_TOP, RobotMap.kTimeoutMs);
		talonLift.configForwardSoftLimitEnable(false, RobotMap.kTimeoutMs);
		talonLift.configReverseSoftLimitThreshold(RobotMap.Robot.LIFT_LIMIT_BOTTOM, RobotMap.kTimeoutMs);
		talonLift.configReverseSoftLimitEnable(false, RobotMap.kTimeoutMs);

		talonLift.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
		
		currentPosition = 0.0;

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
		SmartDashboard.putData("Lift Deploy", new CommandLiftDeploy());
		
		setDefaultCommand(new CommandLiftDefault());
	}
	
	public void dashboard() {
		SmartDashboard.putNumber("Lift Height", currentPosition);
		SmartDashboard.putBoolean("Lift Bottom", !switchBottom.get());
		SmartDashboard.putBoolean("Lift Top", !switchTop.get());
	}

	public void setSpeed(double speed) {
		currentPosition = talonLift.getSelectedSensorPosition(RobotMap.kPIDLoopIdx);

		if (speed < 0.0 && !switchBottom.get()) {
			speed = 0.0;
		} else if (speed > 0.0 && !switchTop.get()) {
			speed = 0.0;
		}
		
		if (speed < 0.0 && currentPosition - RobotMap.Robot.LIFT_DEADBAND >= RobotMap.Robot.LIFT_LIMIT_BOTTOM) {
			double progress = (RobotMap.Robot.LIFT_LIMIT_BOTTOM - currentPosition) / RobotMap.Robot.LIFT_DEADBAND;
			
			if (progress < 0.0) {
				speed = 0.0;
			} else {
				speed *= Math.pow(progress, 3.0);
			}
		} else if (speed > 0.0 && currentPosition - RobotMap.Robot.LIFT_DEADBAND <= RobotMap.Robot.LIFT_LIMIT_TOP) {
			double progress = Math.abs(currentPosition - RobotMap.Robot.LIFT_LIMIT_TOP) / RobotMap.Robot.LIFT_DEADBAND;

			if (progress < 0.0) {
				speed = 0.0;
			} else {
				speed *= Math.pow(progress, 3.0);
			}
		}
		
		talonLift.set(ControlMode.PercentOutput, speed);
	}
	
	public void reset() {
		stop();
	}
	
	public void stop() {
		talonLift.set(ControlMode.PercentOutput, 0.0);
	}
}
