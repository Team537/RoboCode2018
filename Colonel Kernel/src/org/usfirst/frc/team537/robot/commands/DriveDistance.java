package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command {
	private double distanceLeft;
	private double distanceRight;

	public DriveDistance(double distanceLeft, double distanceRight) {
		requires(Robot.drive);
		setInterruptible(false);
		setTimeout((Math.abs(distanceLeft) + Math.abs(distanceRight)) * 0.5);
		this.distanceLeft = distanceLeft;
		this.distanceRight = distanceRight;
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.drive.reset();
	//	Robot.drive.setToMode(ControlMode.Position);
		Robot.drive.setPIDF(
				0.30, 0.0, 0.15, 0.0, // Left.
				0.30, 0.0, 0.15, 0.0 // Right.
		);
		Robot.drive.distance(distanceLeft, distanceRight);
	}

	/**
	 * The execute method is called repeatedly until this Command either
	 * finishes or is cancelled.
	 */
	@Override
	protected void execute() {
	}

	/**
	 * This returns true when this Command no longer needs to run execute.
	 */
	@Override
	protected boolean isFinished() {
		return isTimedOut(); // Robot.drive.atTarget() ||
	}

	/**
	 * Called once after isFinished returns true.
	 */
	@Override
	protected void end() {
		Robot.drive.stop();
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
