package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.subsystems.Lights.Colour;

import edu.wpi.first.wpilibj.command.Command;

public class LightsColour extends Command {
	private Command listener;
	private Colour colour;

	public LightsColour(Command listener, Colour colour) {
		requires(Robot.lights);
		setInterruptible(true);
		this.listener = listener;
		this.colour = colour;
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
	}

	/**
	 * The execute method is called repeatedly until this Command either
	 * finishes or is cancelled.
	 */
	@Override
	protected void execute() {
		Robot.lights.set(colour);
	}

	/**
	 * This returns true when this Command no longer needs to run execute.
	 */
	@Override
	protected boolean isFinished() {
		return (listener != null) ? !listener.isRunning() : false;
	}

	/**
	 * Called once after isFinished returns true.
	 */
	@Override
	protected void end() {
		Robot.lights.set(Colour.getDefault());
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
		this.end();
	}
}
