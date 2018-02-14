package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandCollectSpeed extends Command {
	private double speed;
	
	public CommandCollectSpeed(double speed) {
		requires(Robot.subsystemCollect);
		this.speed = speed;
	}

	@Override
	protected void initialize() {
		Robot.subsystemCollect.reset();
	}

	@Override
	protected void execute() {
		Robot.subsystemCollect.setSpeed(speed);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.subsystemCollect.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
