package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandGyroReset extends Command {
	public CommandGyroReset() {
		requires(Robot.subsystemGyro);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.subsystemGyro.reset();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		end();
	}
}
