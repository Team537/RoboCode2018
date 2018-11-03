package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CommandDriveLock extends Command {
	public CommandDriveLock() {
	//	requires(Robot.subsystemDrive);
	}

	@Override
	protected void initialize() {
		double gyro = Robot.subsystemGyro.getAngle();
		Robot.subsystemDrive.setControllerRotate(gyro);
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
		Robot.subsystemDrive.getControllerRotate().disable();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
