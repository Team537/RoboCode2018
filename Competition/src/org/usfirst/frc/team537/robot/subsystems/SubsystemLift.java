package org.usfirst.frc.team537.robot.subsystems;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SubsystemLift extends Subsystem {
	private TalonSRX talonLift = new TalonSRX(RobotMap.CAN.LIFT);

	public SubsystemLift() {
		setName("Lift");
	}

	@Override
	protected void initDefaultCommand() {
		SmartDashboard.putData("Lift Deploy", new CommandLiftDeploy());
		
		setDefaultCommand(new CommandLiftDefault());
	}

	public void setSpeed(double speed) {
		talonLift.set(ControlMode.PercentOutput, speed);
	}
	
	public void reset() {
		stop();
	}
	
	public void stop() {
		talonLift.set(ControlMode.PercentOutput, 0.0);
	}
}
