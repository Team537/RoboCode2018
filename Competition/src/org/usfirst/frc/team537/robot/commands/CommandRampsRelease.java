package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.subsystems.SubsystemRamps.RampSide;

import edu.wpi.first.wpilibj.command.Command;

public class CommandRampsRelease extends Command {
	private RampSide side;
	
	public CommandRampsRelease(RampSide side) {
	//	requires(Robot.subsystemRamps);
		this.side = side;
	}

	@Override
	protected void initialize() {
		Robot.subsystemRamps.reset();
	}

	@Override
	protected void execute() {
		Robot.subsystemRamps.release(side);
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
