package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandRampsRelease extends Command {
	private int ramp;
	
	public CommandRampsRelease(int ramp) {
		requires(Robot.subsystemRamps);
		this.ramp = ramp;
	}

	@Override
	protected void initialize() {
		Robot.subsystemRamps.reset();
	}

	@Override
	protected void execute() {
		if (ramp == 0) {
			Robot.subsystemRamps.releaseLeft();
		} else if (ramp == 1) {
			Robot.subsystemRamps.releaseRight();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return true;
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
