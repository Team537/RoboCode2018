package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandRampsDefault extends Command {
	public CommandRampsDefault() {
		requires(Robot.subsystemRamps);
	}

	@Override
	protected void initialize() {
		Robot.subsystemRamps.reset();
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
		Robot.subsystemRamps.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
