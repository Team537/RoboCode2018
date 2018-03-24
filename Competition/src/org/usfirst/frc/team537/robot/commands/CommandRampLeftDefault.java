package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandRampLeftDefault extends Command {
	public CommandRampLeftDefault() {
		requires(Robot.subsystemRampLeft);
	}

	@Override
	protected void initialize() {
		Robot.subsystemRampLeft.reset();
	}

	@Override
	protected void execute() {
		if (Robot.oi.joystickSecondary.getRawButton("RampDeployLeft")) {
			Robot.subsystemRampLeft.setToggled(true);

			if (Robot.oi.joystickSecondary.getRawButton("RampUpLeft")) {
				Robot.subsystemRampLeft.setLift(1.0);
			} else if (Robot.oi.joystickSecondary.getRawButton("RampDownLeft")) {
				Robot.subsystemRampLeft.setLift(-0.7);
			} else {
				Robot.subsystemRampLeft.setLift(0.0);
			}
		} else {
			Robot.subsystemRampLeft.setToggled(false);
			Robot.subsystemRampLeft.setLift(0.0);
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.subsystemRampLeft.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
