package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.subsystems.SwerveModule;

import edu.wpi.first.wpilibj.command.Command;

public class CommandDriveDistance extends Command {
	private double angle;
	private double distance;
	
	public CommandDriveDistance(double angle, double distance) {
		requires(Robot.subsystemDrive);
		this.angle = angle;
		this.distance = distance * RobotMap.Digital.DRIVE_M_TO_ENCODER;
	}

	@Override
	protected void initialize() {
		Robot.subsystemDrive.reset();
		Robot.subsystemDrive.setMode(SwerveModule.SwerveMode.ModeDistance);
	}

	@Override
	protected void execute() {
		double gyro = Robot.subsystemGyro.getAngle();
		Robot.subsystemDrive.setTarget(gyro, angle, distance);
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
