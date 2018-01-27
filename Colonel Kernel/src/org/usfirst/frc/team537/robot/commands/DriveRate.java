package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class DriveRate extends Command {
	private double rateLeft;
	private double rateRight;

	public DriveRate(double rateLeft, double rateRight, double time) {
		requires(Robot.drive);
		setInterruptible(true);
		setTimeout(time);
		this.rateLeft = rateLeft;
		this.rateRight = rateRight;
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
	//	Robot.drive.setToMode(ControlMode.Velocity);
		Robot.drive.setPIDF(0.0, 0.0, 0.0, 1023.0 / 640.0, 0.0, 0.0, 0.0, 1023.0 / 640.0);
		Robot.drive.rate(rateLeft, rateRight);
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
		return this.isTimedOut();
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
		super.end();
	}
}
