package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveAngle extends Command {
	private Timer timer;
	private double angle;

	public DriveAngle(double angle) {
		requires(Robot.drive);
		setInterruptible(false);
		this.timer = new Timer();
		this.angle = angle;
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.drive.reset();
	//	Robot.drive.setToMode(ControlMode.PercentOutput);

		Robot.ahrs.reset();

		timer.reset();
		timer.start();
	}

	/**
	 * The execute method is called repeatedly until this Command either
	 * finishes or is cancelled.
	 */
	@Override
	protected void execute() {
		double error = angle - Robot.ahrs.getYaw();
		double left = 0;
		double right = 0;
		double Kp = 0.0085;

		left = -error * Kp;
		right = error * Kp;

		SmartDashboard.putNumber("Drive Angle Error", error);

		Robot.drive.rate(left, right);
	}

	/**
	 * This returns true when this Command no longer needs to run execute.
	 */
	@Override
	protected boolean isFinished() {
		return Robot.drive.atTarget() || timer.get() > 5.0;
	}

	/**
	 * Called once after isFinished returns true.
	 */
	@Override
	protected void end() {
		Robot.drive.stop();
		timer.reset();
		timer.stop();
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
