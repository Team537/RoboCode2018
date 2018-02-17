package org.usfirst.frc.team537.robot.subsystems;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.CommandRampsDefault;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SubsystemRamps extends Subsystem {
	private Solenoid deployLeft = new Solenoid(RobotMap.Solenoid.RAMP_DEPLOY_LEFT);
	private Solenoid deployRight = new Solenoid(RobotMap.Solenoid.RAMP_DEPLOY_RIGHT);
	private TalonSRX rampLiftLeft = new TalonSRX(RobotMap.CAN.RAMP_LIFT_LEFT);
	private TalonSRX rampLiftRight = new TalonSRX(RobotMap.CAN.RAMP_LIFT_RIGHT);
	
	public SubsystemRamps() {
		setName("Ramp");
		deployLeft.set(true);
		deployRight.set(true);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CommandRampsDefault());
	}

	public void releaseLeft() {
		deployLeft.set(false);
	}

	public void releaseRight() {
		deployRight.set(false);
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
		rampLiftLeft.set(ControlMode.PercentOutput, 0.0);
		rampLiftRight.set(ControlMode.PercentOutput, 0.0);
	}
}
