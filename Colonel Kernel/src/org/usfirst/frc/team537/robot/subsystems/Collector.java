package org.usfirst.frc.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.CollectorDefault;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Collector extends Subsystem {
	private final TalonSRX collector = new TalonSRX(RobotMap.CAN.COLLECTOR);
	
	private double setpoint;

	public Collector() {
		collector.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
	//	collector.enableBrakeMode(false);
	//	collector.configEncoderCodesPerRev(98);
		collector.config_kP(RobotMap.kPIDLoopIdx, 0.0, RobotMap.kTimeoutMs);
		collector.config_kI(RobotMap.kPIDLoopIdx, 0.0, RobotMap.kTimeoutMs); 
		collector.config_kD(RobotMap.kPIDLoopIdx, 0.0, RobotMap.kTimeoutMs);
		collector.config_kF(RobotMap.kPIDLoopIdx, 1.0, RobotMap.kTimeoutMs); // 1023.0 / 610.0
	//	collector.configPeakOutputVoltage(+12.0, 0.0);

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
		setDefaultCommand(new CollectorDefault());
	}

	/**
	 * Drives the collector from the input rate.
	 * 
	 * @param rate
	 *            The input speed.
	 */
	public void collect(double rate) {
		collector.set(ControlMode.PercentOutput, setpoint = -rate * RobotMap.Robot.COLLECT_SPEED);
	}

	public double getSetpoint() {
		return setpoint;
	}

	public void reset() {
		collector.set(ControlMode.PercentOutput, setpoint = 0.0);
	}

	public void stop() {
		collector.set(ControlMode.PercentOutput, setpoint = 0.0);
	}

	private void dashboard() {
	//	SmartDashboard.putNumber("Collector Encoder Speed", collector.getEncVelocity()); // Native units.
	//	SmartDashboard.putNumber("Collector Encoder Error", collector.getError() * 4.0f);
	//	SmartDashboard.putNumber("Collector Encoder Position", collector.getEncPosition());
	}
}
