package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FeederFeed extends Command {
	public FeederFeed() {
		requires(Robot.feeder);
		setInterruptible(true);
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.feeder.reset();
	}

	/**
	 * The execute method is called repeatedly until this Command either
	 * finishes or is canceled.
	 */
	@Override
	protected void execute() {
		if (Robot.shooter.nearSpeed()) {
			Robot.feeder.feed(0.9);
		}
	}

	/**
	 * This returns true when this Command no longer needs to run execute.
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}

	/**
	 * Called once after isFinished returns true.
	 */
	@Override
	protected void end() {
		Robot.feeder.stop();
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
