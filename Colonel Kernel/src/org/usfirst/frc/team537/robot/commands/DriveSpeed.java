package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class DriveSpeed extends Command {
	private double speedLeft;
	private double speedRight;

	public DriveSpeed(double speedLeft, double speedRight, double time) {
		requires(Robot.drive);
		setInterruptible(true);
		setTimeout(time);
		this.speedLeft = speedLeft;
		this.speedRight = speedRight;
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
	//	Robot.drive.setToMode(ControlMode.PercentOutput);
		Robot.drive.speed(speedLeft, speedRight);
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
		return super.isTimedOut();
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
