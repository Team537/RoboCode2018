package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterAutomatic extends Command {
	private Timer timer;
	
	public ShooterAutomatic() {
		requires(Robot.agitator);
		requires(Robot.shooter);
		requires(Robot.feeder);
		setInterruptible(false);
		
		this.timer = new Timer();
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		Robot.agitator.reset();
		Robot.shooter.reset();
	//	Robot.shooter.setBreakmode(false);
	//	Scheduler.getInstance().add(new LightsCycle(this, 0.4, Lights.Colour.values()));
		
		timer.reset();
		timer.start();
	}
	
	/**
	 * The execute method is called repeatedly until this Command either finishes or is canceled.
	 */
	@Override
	protected void execute() {
		Robot.shooter.shoot(3075.0); // further distance than usual
		
		if (timer.get() > 2.2) {
			Robot.agitator.agitate(-1.0);
			Robot.feeder.feed(0.8);
		}
	}

	/**
	 * This returns true when this Command no longer needs to run execute.
	 */
	@Override
	protected boolean isFinished() {
		return timer.get() > 9;
	}

	/**
	 * Called once after isFinished returns true.
	 */
	@Override
	protected void end() {
		Robot.agitator.stop();
		Robot.feeder.stop();
		Robot.shooter.stop();
		
		timer.stop();
	}

	/**
	 * Called when another command which requires one or more of the same subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
		this.end();
	}
}
