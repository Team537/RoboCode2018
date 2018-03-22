package org.usfirst.frc.team537.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class CommandNothing extends Command {
	private double timeout;
	private Timer timer;
	
	public CommandNothing(double timeout) {
		this.timeout = timeout;
		this.timer = new Timer();
	}

	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return timer.get() > timeout;
	}

	@Override
	protected void end() {
		timer.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
