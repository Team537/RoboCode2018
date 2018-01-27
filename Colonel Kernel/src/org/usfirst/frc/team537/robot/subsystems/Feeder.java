package org.usfirst.frc.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.FeederDefault;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Feeder extends Subsystem {
	private final TalonSRX feeder = new TalonSRX(RobotMap.CAN.FEEDER);

	public Feeder() {
		feeder.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);

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
		setDefaultCommand(new FeederDefault());
	}

	/**
	 * Drives the climber from the input rate.
	 * 
	 * @param rate The input speed.
	 */
	public void feed(double rate) {
		if (!Robot.shooter.nearSpeed()) {
			rate = 0.0;
		}

		SmartDashboard.putNumber("Feeder Rate", rate);
		feeder.set(ControlMode.PercentOutput, -rate * RobotMap.Robot.FEEDER_SPEED);
	}

	public void reset() {
		feeder.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
		feeder.set(ControlMode.PercentOutput, 0.0);
	}

	public void stop() {
		feeder.set(ControlMode.PercentOutput, 0.0);
	}

	private void dashboard() {
	//	SmartDashboard.putNumber("Feeder Speed", feeder.getSpeed()); // Native units.
	//	SmartDashboard.putNumber("Feeder Encoder Error", feeder.getError() * 4.0f);
	//	SmartDashboard.putNumber("Feeder Encoder Position", feeder.getEncPosition());
	}
}
