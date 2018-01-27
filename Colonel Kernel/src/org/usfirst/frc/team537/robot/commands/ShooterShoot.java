package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterShoot extends Command {
	public ShooterShoot() {
		requires(Robot.shooter);
		setInterruptible(true);
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.shooter.reset();
	//	Robot.shooter.setBreakmode(false);
	//	Scheduler.getInstance().add(new LightsCycle(this, 0.4, Lights.Colour.values()));
	}

	/**
	 * The execute method is called repeatedly until this Command either
	 * finishes or is canceled.
	 */
	@Override
	protected void execute() {
		Robot.shooter.shoot(RobotMap.Robot.SHOOTER_SPEED);
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
		Robot.shooter.stop();
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
