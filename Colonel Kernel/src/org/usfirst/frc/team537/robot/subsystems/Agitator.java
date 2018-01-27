package org.usfirst.frc.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.AgitatorDefault;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Agitator extends Subsystem {
	private final TalonSRX agitator = new TalonSRX(RobotMap.CAN.AGITATOR);

	public Agitator() {
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
		setDefaultCommand(new AgitatorDefault());
	}

	/**
	 * Drives the agitator from the input rate.
	 * 
	 * @param rate The input speed.
	 */
	public void agitate(double rate) {
		SmartDashboard.putNumber("Agitator Rate", rate);
		agitator.set(ControlMode.PercentOutput, -rate * RobotMap.Robot.AGITATOR_SPEED);
	}

	public void reset() {
		agitator.set(ControlMode.PercentOutput, 0.0);
	}

	public void stop() {
		agitator.set(ControlMode.PercentOutput, 0.0);
	}

	private void dashboard() {
	}
}
