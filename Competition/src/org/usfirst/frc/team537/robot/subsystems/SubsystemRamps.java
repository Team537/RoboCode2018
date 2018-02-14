package org.usfirst.frc.team537.robot.subsystems;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.CommandRampsDefault;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SubsystemRamps extends Subsystem {
	private TalonSRX rampRelease  = new TalonSRX(RobotMap.CAN.RAMP_RELEASE);
	private TalonSRX rampLiftLeft = new TalonSRX(RobotMap.CAN.RAMP_LIFT_LEFT);
	private TalonSRX rampLiftRight = new TalonSRX(RobotMap.CAN.RAMP_LIFT_RIGHT);
	
	public SubsystemRamps() {
		setName("Ramp");
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CommandRampsDefault());
	}

	public void setRelease(double speed) {
		rampRelease.set(ControlMode.PercentOutput, speed);
	}

	public void setLiftLeft(double velocity) {
		rampLiftLeft.set(ControlMode.Velocity, velocity);
	}

	public void setLiftRight(double velocity) {
		rampLiftRight.set(ControlMode.Velocity, velocity);
	}
	
	public void reset() {
		stop();
	}
	
	public void stop() {
		rampRelease.set(ControlMode.PercentOutput, 0.0);
		rampLiftLeft.set(ControlMode.PercentOutput, 0.0);
		rampLiftRight.set(ControlMode.PercentOutput, 0.0);
	}
}
