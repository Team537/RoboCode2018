package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandRampRightDefault extends Command {
	public CommandRampRightDefault() {
		requires(Robot.subsystemRampRight);
	}

	@Override
	protected void initialize() {
		Robot.subsystemRampRight.reset();
	}

	@Override
	protected void execute() {
		if (Robot.oi.joystickSecondary.getRawButton("RampDeployRight")) {
			Robot.subsystemRampRight.setToggled(true);

			if (Robot.oi.joystickSecondary.getRawButton("RampUpRight")) {
				Robot.subsystemRampRight.setLift(1.0);
			} else if (Robot.oi.joystickSecondary.getRawButton("RampDownRight")) {
				Robot.subsystemRampRight.setLift(-0.7);
			} else {
				Robot.subsystemRampRight.setLift(0.0);
			}
		} else {
			Robot.subsystemRampRight.setToggled(false);
			Robot.subsystemRampRight.setLift(0.0);
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.subsystemRampRight.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
