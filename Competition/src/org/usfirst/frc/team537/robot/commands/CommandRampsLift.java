package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandRampsLift extends Command {
	private int ramp;
	private double velocity;
	
	public CommandRampsLift(int ramp, double velocity) {
		requires(Robot.subsystemRamps);
		this.ramp = ramp;
		this.velocity = velocity;
	}

	@Override
	protected void initialize() {
		Robot.subsystemRamps.reset();
	}

	@Override
	protected void execute() {
		if (ramp == 0) {
			Robot.subsystemRamps.setLiftLeft(velocity);
		} else if (ramp == 1) {
			Robot.subsystemRamps.setLiftRight(velocity);
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.subsystemRamps.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
