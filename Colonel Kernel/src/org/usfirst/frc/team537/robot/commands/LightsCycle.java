package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.subsystems.Lights.Colour;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class LightsCycle extends Command {
	private Command listener;
	private double period;
	private Colour[] colours;
	
	private Timer timer;
	private int colourStage;

	public LightsCycle(Command listener, double period, Colour... colours) {
		requires(Robot.lights);
		setInterruptible(true);
		this.listener = listener;
		this.period = period;
		this.colours = colours;
		
		this.timer = new Timer();
		this.colourStage = 0;
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
		Robot.lights.set(colours[0]);
	}
	
	/**
	 * The execute method is called repeatedly until this Command either finishes or is cancelled.
	 */
	@Override
	protected void execute() {
		if (timer.get() > period) {
			colourStage++;
			
			if (colourStage >= colours.length) {
				colourStage = 0;
			}
			
			Robot.lights.set(colours[colourStage]);			
			timer.reset();
		}
	}

	/**
	 * This returns true when this Command no longer needs to run execute.
	 */
	@Override
	protected boolean isFinished() {
		return (listener != null) ? !listener.isRunning() : false;
	}

	/**
	 * Called once after isFinished returns true.
	 */
	@Override
	protected void end() {
		timer.stop();
		Robot.lights.set(Colour.getDefault());
	}

	/**
	 * Called when another command which requires one or more of the same subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
		this.end();
	}
}
