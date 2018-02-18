package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.subsystems.SubsystemRamps.RampSide;

import edu.wpi.first.wpilibj.command.Command;

public class CommandRampsLift extends Command {
	private RampSide side;
	private double speed;
	
	public CommandRampsLift(RampSide side, double speed) {
		requires(Robot.subsystemRamps);
		this.side = side;
		this.speed = speed;
	}

	@Override
	protected void initialize() {
		Robot.subsystemRamps.reset();
	}

	@Override
	protected void execute() {
		Robot.subsystemRamps.setLift(side, speed);
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
