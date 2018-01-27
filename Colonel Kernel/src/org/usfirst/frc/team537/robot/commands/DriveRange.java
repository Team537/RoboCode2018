package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveRange extends Command {
	private double range;

	public DriveRange(double range) {
		requires(Robot.drive);
		setInterruptible(true);
		this.range = range;
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
	//	Robot.drive.setToMode(ControlMode.PercentOutput);
	}

	/**
	 * The execute method is called repeatedly until this Command either
	 * finishes or is cancelled.
	 */
	@Override
	protected void execute() {
		if (isFinished()) {
			Robot.drive.speed(0.0, 0.0);
			return;
		}

		double distance = Robot.lidar.getRange() - range;
		double speed = Math.cbrt(distance / 3.0) / 3.0;
		Robot.drive.speed(-speed, -speed);
		SmartDashboard.putNumber("Drive Range Distance", distance);
	}

	/**
	 * This returns true when this Command no longer needs to run execute.
	 */
	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.lidar.getRange() - range) < 2.0f;
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
