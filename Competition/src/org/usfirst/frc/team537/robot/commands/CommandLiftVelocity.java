package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandLiftVelocity extends Command {
	private double velocity;
	
	public CommandLiftVelocity(double velocity) {
		requires(Robot.subsystemLift);
		this.velocity = velocity;
	}

	@Override
	protected void initialize() {
		Robot.subsystemLift.reset();
	}

	@Override
	protected void execute() {
		Robot.subsystemLift.setVelocity(velocity);
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
