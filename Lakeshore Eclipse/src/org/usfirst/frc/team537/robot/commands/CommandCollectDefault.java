package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandCollectDefault extends Command {
	public CommandCollectDefault() {
		requires(Robot.subsystemCollect);
	}

	@Override
	protected void initialize() {
		Robot.subsystemCollect.reset();
	}

	@Override
	protected void execute() {
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.subsystemCollect.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
