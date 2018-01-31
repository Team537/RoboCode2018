package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.helpers.Maths;
import org.usfirst.frc.team537.robot.subsystems.SwerveModule;

import edu.wpi.first.wpilibj.command.Command;

public class CommandDriveDefault extends Command {
	public CommandDriveDefault() {
		requires(Robot.subsystemDrive);
	}

	@Override
	protected void initialize() {
		Robot.subsystemDrive.reset();
		Robot.subsystemDrive.setMode(SwerveModule.SwerveMode.ModeSpeed);
	}

	@Override
	protected void execute() {
		double gyro = Math.toRadians(Robot.subsystemGyro.getAngle());
		double rotation = Robot.oi.joystickPrimary.getRawAxis("DriveRotation");
		rotation = 0.7 * sensitivity(Maths.deadband(0.1, rotation), 0.2);
		double strafe = Robot.oi.joystickPrimary.getRawAxis("DriveStrafe");
		strafe = sensitivity(Maths.deadband(0.07, strafe), 0.5);
		double forward = Robot.oi.joystickPrimary.getRawAxis("DriveForward");
		forward = sensitivity(Maths.deadband(0.07, forward), 0.5);
		Robot.subsystemDrive.setTarget(gyro, rotation, strafe, forward);
	}
	
	private double sensitivity(double value, double factor) {
		return ((1.0 - factor) * value) + (factor * Math.pow(value, 3.0));
	}

	@Override
	protected boolean isFinished() {
		return false;
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
