package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.subsystems.SwerveModule;

import edu.wpi.first.wpilibj.command.Command;

public class CommandDriveRate extends Command {
	private double angle;
	private double rate;
	
	public CommandDriveRate(double angle, double rate) {
		requires(Robot.subsystemDrive);
		this.angle = angle;
		this.rate = rate;
	}

	@Override
	protected void initialize() {
		Robot.subsystemDrive.reset();
		Robot.subsystemDrive.setMode(SwerveModule.SwerveMode.ModeRate);
	}

	@Override
	protected void execute() {
		double gyro = Math.toRadians(Robot.subsystemGyro.getAngle());
		Robot.subsystemDrive.setTarget(gyro, angle, rate);
	}

	@Override
	protected boolean isFinished() {
		return Robot.subsystemDrive.isAtTarget();
	}

	@Override
	protected void end() {
		Robot.subsystemDrive.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
