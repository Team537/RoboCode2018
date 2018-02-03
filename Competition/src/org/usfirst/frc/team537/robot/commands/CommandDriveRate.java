package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.subsystems.SwerveModule;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class CommandDriveRate extends Command {
	private double angle;
	private double rate;
	private double timeout;
	private Timer timer;
	
	public CommandDriveRate(double angle, double rate, double timeout) {
		requires(Robot.subsystemDrive);
		this.angle = angle;
		this.rate = rate;
		this.timeout = timeout;
		this.timer = new Timer();
	}

	@Override
	protected void initialize() {
		Robot.subsystemDrive.reset();
		Robot.subsystemDrive.setMode(SwerveModule.SwerveMode.ModeRate);
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		double gyro = Robot.subsystemGyro.getAngle();
		Robot.subsystemDrive.setTarget(gyro, angle, rate);
	}

	@Override
	protected boolean isFinished() {
		return timer.get() > timeout;
	}

	@Override
	protected void end() {
		timer.stop();
		Robot.subsystemDrive.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
