package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.subsystems.SwerveModule;

import edu.wpi.first.wpilibj.command.Command;

public class CommandDriveSpeed extends Command {
	private double strafe;
	private double forward;
	
	public CommandDriveSpeed(double strafe, double forward) {
		requires(Robot.subsystemDrive);
		this.strafe = strafe;
		this.forward = forward;
	}

	@Override
	protected void initialize() {
		Robot.subsystemDrive.reset();
		Robot.subsystemDrive.setMode(SwerveModule.SwerveMode.ModeSpeed);
	}

	@Override
	protected void execute() {
		double gyro = Math.toRadians(Robot.subsystemGyro.getAngle());
		Robot.subsystemDrive.setTarget(gyro, 0.0, strafe, forward);
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
