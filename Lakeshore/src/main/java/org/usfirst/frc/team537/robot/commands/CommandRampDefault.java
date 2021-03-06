package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.subsystems.SubsystemRamp.RampSide;

import edu.wpi.first.wpilibj.command.Command;

public class CommandRampDefault extends Command {
	private RampSide side;
	
	public CommandRampDefault(RampSide side) {
		requires(side.subsystem);
		this.side = side;
	}

	@Override
	protected void initialize() {
	//	side.subsystem.reset();
	}

	@Override
	protected void execute() {
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		side.subsystem.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
