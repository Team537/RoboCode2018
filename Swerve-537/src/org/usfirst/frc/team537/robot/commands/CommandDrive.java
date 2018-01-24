package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class CommandDrive extends Command {
	public CommandDrive() {
		requires(Robot.subsystemDrive);
	}

	@Override
	protected void initialize() {
		Robot.subsystemDrive.reset();
	}

	@Override
	protected void execute() {
		double rotation = Math.pow(Robot.oi.joystickPrimary.getRawAxis(RobotMap.JoystickAxes.STICK_Z), 3.0);
		rotation = Maths.deadband(0.07f, rotation);
		double strafe = Robot.oi.joystickPrimary.getRawAxis(RobotMap.JoystickAxes.STICK_X);
		strafe = Maths.deadband(0.07f, strafe);
		double forward = -Robot.oi.joystickPrimary.getRawAxis(RobotMap.JoystickAxes.STICK_Y);
		forward = Maths.deadband(0.07f, forward);
		double gyro = Math.toRadians(Robot.ahrs.getAngle());
		Robot.subsystemDrive.drive(rotation, strafe, forward, gyro);
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
