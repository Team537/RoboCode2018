package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.helpers.Colour;

import edu.wpi.first.wpilibj.command.Command;

public class CommandLedsDefault extends Command {
	public CommandLedsDefault() {
		requires(Robot.subsystemLeds);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double time = (double) System.currentTimeMillis() / 1000.0;
		double r = 2.0 * Math.sin(time);
		double b = 1.5 * Math.cos(time);
		double g = 1.0 - (2.0f * (r + b));
		Robot.subsystemLeds.setColour(new Colour(r, g, b));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.subsystemLeds.reset();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
