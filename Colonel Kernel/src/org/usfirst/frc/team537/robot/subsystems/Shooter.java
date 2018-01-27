package org.usfirst.frc.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.ShooterDefault;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	private final TalonSRX shooterMaster = new TalonSRX(RobotMap.CAN.SHOOTER_MASTER);
	private final TalonSRX shooterSlave = new TalonSRX(RobotMap.CAN.SHOOTER_SLAVE);
	
	private double setpoint;

	public Shooter() {
		shooterMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
	//	shooterMaster.configEncoderCodesPerRev(14);
		shooterMaster.config_kP(RobotMap.kPIDLoopIdx, 42.0, RobotMap.kTimeoutMs);
		shooterMaster.config_kI(RobotMap.kPIDLoopIdx, 0.0, RobotMap.kTimeoutMs); 
		shooterMaster.config_kD(RobotMap.kPIDLoopIdx, 0.0, RobotMap.kTimeoutMs);
		shooterMaster.config_kF(RobotMap.kPIDLoopIdx, 1023.0 / 590.0, RobotMap.kTimeoutMs); 
	//	shooterMaster.configPeakOutputVoltage(+12.0, 0.0);

		shooterSlave.set(ControlMode.Follower, RobotMap.CAN.SHOOTER_MASTER);

		reset();

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
		setDefaultCommand(new ShooterDefault());
	}

	/**
	 * Drives the shooter from the input rate.
	 * 
	 * @param rate
	 *            The input speed.
	 */
	public void shoot(double rate) {
		// IF RATE IS NEGATIVE: THE ROBOT WILL KILL ITSELF.
		shooterMaster.set(ControlMode.Velocity, setpoint = Math.abs(rate));
	}

	public double getSetpoint() {
		return setpoint;
	}

	public boolean nearSpeed() {
		return getSetpoint() > 0.0 && Math.abs(setpoint - shooterMaster.getSelectedSensorVelocity(RobotMap.kPIDLoopIdx)) < RobotMap.Robot.SHOOTER_MAX_ERROR;
	}

	public void reset() {
		shooterMaster.set(ControlMode.Velocity, 0.0);
		shooterMaster.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
	}

	public void stop() {
		shooterMaster.set(ControlMode.Velocity, 0.0);
	}

	private void dashboard() {
		// System.out.println("[Shooter] Error: " + shooterMaster.getError() + "
		// Current: " + shooterMaster.getOutputCurrent());

	//	SmartDashboard.putNumber("Shooter Amps", shooterMaster.getOutputCurrent());
	//	SmartDashboard.putNumber("Shooter Speed", shooterMaster.getSpeed());
	//	SmartDashboard.putBoolean("Shooter Near Speed", nearSpeed());
	//	SmartDashboard.putNumber("Shooter Encoder Speed", shooterMaster.getEncVelocity()); // Native
																							// units.
	//	SmartDashboard.putNumber("Shooter Encoder Error", shooterMaster.getError());
	//	SmartDashboard.putNumber("Shooter Encoder Position", shooterMaster.getEncPosition());
	}

}
