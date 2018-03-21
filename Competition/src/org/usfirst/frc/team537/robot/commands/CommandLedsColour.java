package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.helpers.Colour;

import edu.wpi.first.wpilibj.command.Command;

public class CommandLedsColour extends Command {
	private Colour colour;
	
	public CommandLedsColour(Colour colour) {
		requires(Robot.subsystemLeds);
		this.colour = colour;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.subsystemLeds.setColour(colour);
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
