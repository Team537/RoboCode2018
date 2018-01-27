package org.usfirst.frc.team537.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class Delay extends Command {
	public Delay(double time) {
		setInterruptible(false);
		setTimeout(time);
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
	}

	/**
	 * The execute method is called repeatedly until this Command either
	 * finishes or is canceled.
	 */
	@Override
	protected void execute() {
	}

	/**
	 * This returns true when this Command no longer needs to run execute.
	 */
	@Override
	protected boolean isFinished() {
		return super.isTimedOut();
	}

	/**
	 * Called once after isFinished returns true.
	 */
	@Override
	protected void end() {
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
		super.end();
	}
}
