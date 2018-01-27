package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.toolbox.Maths;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDefault extends Command {
	public DriveDefault() {
		requires(Robot.drive);
		setInterruptible(true);
	}

	/**
	 * Called just before this Command runs the first time.
	 */
	@Override
	protected void initialize() {
	//	Robot.drive.setToMode(ControlMode.PercentOutput);
	}

	/**
	 * The execute method is called repeatedly until this Command either
	 * finishes or is cancelled.
	 */
	@Override
	protected void execute() {
		double left = 0.0f; 
		double right = 0.0f; 
		double scalar = 1.0f;
		
		switch (RobotMap.Driver.CONTROL)
		{
		case F310:
			left = -Robot.oi.joystickPrimary.getRawAxis(RobotMap.AxesF310.STICK_LEFT_Y);
			right = -Robot.oi.joystickPrimary.getRawAxis(RobotMap.AxesF310.STICK_RIGHT_Y);
			break;
		case EXTREME:
			left = -Robot.oi.joystickPrimary.getRawAxis(RobotMap.AxesExtreme.STICK_Y);
			right = -Robot.oi.joystickSecondary.getRawAxis(RobotMap.AxesExtreme.STICK_Y);
			break;
		}
		
		scalar = Robot.oi.joystickBox.getRawButton(RobotMap.ControlBox.CLIMB_TOGGLE) ? 0.444 : 1.0;
		
		left = ((1.0 - RobotMap.Driver.SENSITIVITY) * left) + (RobotMap.Driver.SENSITIVITY * Math.pow(left, 3.0));
		right = ((1.0 - RobotMap.Driver.SENSITIVITY) * right) + (RobotMap.Driver.SENSITIVITY * Math.pow(right, 3.0));
		left = Maths.deadband(RobotMap.Robot.DRIVE_SPEED_MIN, left);
		right = Maths.deadband(RobotMap.Robot.DRIVE_SPEED_MIN, right);
		
		left *= scalar;
		right *= scalar;

		Robot.drive.speed(left, right);
	}

	/**
	 * This returns true when this Command no longer needs to run execute.
	 */
	@Override
	protected boolean isFinished() {
		return false;
	}

	/**
	 * Called once after isFinished returns true.
	 */
	@Override
	protected void end() {
		Robot.drive.stop();
	}

	/**
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
		this.end();
	}
}
