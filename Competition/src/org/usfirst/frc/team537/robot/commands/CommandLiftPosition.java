package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandLiftPosition extends Command {
	private double position;
	
	public CommandLiftPosition(double position) {
		requires(Robot.subsystemLift);
		this.position = position;
	}

	@Override
	protected void initialize() {
		Robot.subsystemLift.reset();
	}

	@Override
	protected void execute() {
		Robot.subsystemLift.setPosition(position);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.subsystemLift.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
