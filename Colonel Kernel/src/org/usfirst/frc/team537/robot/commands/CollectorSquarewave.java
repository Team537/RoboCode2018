package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CollectorSquarewave extends Command {
	private double fConst = 3.0; // Smaller creates a more rounded curve, higher
									// creates a more square curve.

	public CollectorSquarewave() {
		requires(Robot.collector);
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.collector.reset();
	}

	/**
	 * The execute method is called repeatedly until this Command either
	 * finishes or is canceled.
	 */
	@Override
	protected void execute() {
		double currentTime = System.currentTimeMillis() / 1000.0;
		double speed = customFunction(currentTime);
		Robot.collector.collect(speed);
	}

	private double customFunction(double seconds) {
		double squareRootValue = Math.sqrt((1.0 + Math.pow(fConst, 2.0)) / (1.0 + Math.pow(fConst, 2.0) * Math.pow(Math.cos(seconds * 2.0), 2.0)));
		double value = -((squareRootValue * Math.cos(seconds * 2.0) / 1.6) - 0.3);
		return value;
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
		Robot.collector.stop();
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
