package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.toolbox.Maths;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class DriveArcade extends Command {
	public DriveArcade() {
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
	 * The execute method is called repeatedly until this Command either finishes or is cancelled.
	 */
	@Override
	protected void execute() {
		double axisX = 0.0f; 
		double axisY = 0.0f; 
		boolean slow = false;
		
		switch (RobotMap.Driver.CONTROL)
		{
		case F310:
			axisX = Robot.oi.joystickPrimary.getRawAxis(RobotMap.AxesF310.STICK_LEFT_X);
			axisY = -Robot.oi.joystickPrimary.getRawAxis(RobotMap.AxesF310.STICK_LEFT_Y);
			slow = Robot.oi.joystickPrimary.getRawButton(5);
			break;
		case EXTREME:
			axisX = Robot.oi.joystickPrimary.getRawAxis(RobotMap.AxesExtreme.STICK_X);
			axisY = -Robot.oi.joystickSecondary.getRawAxis(RobotMap.AxesExtreme.STICK_Y);
		//	slow = Robot.oi.joystickPrimary.getRawButton(5);
			break;
		}
		
		double left = axisY + axisX;
		double right = axisY - axisX;
		left = ((1.0 - RobotMap.Driver.SENSITIVITY) * left) + (RobotMap.Driver.SENSITIVITY * Math.pow(left, 3.0));
		right = ((1.0 - RobotMap.Driver.SENSITIVITY) * right) + (RobotMap.Driver.SENSITIVITY * Math.pow(right, 3.0));
		left = Maths.deadband(RobotMap.Robot.DRIVE_SPEED_MIN, left);
		right = Maths.deadband(RobotMap.Robot.DRIVE_SPEED_MIN, right);

		if (slow) {
			left = left * 0.5;
			right = right * 0.5;
		}
		
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
	 * Called when another command which requires one or more of the same subsystems is scheduled to run.
	 */
	@Override
	protected void interrupted() {
		this.end();
	}
}
