package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.subsystems.SwerveModule;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class CommandDriveRotate extends Command {
	private PIDController controller;
	private double angle;
	private Timer timer;
	
	public CommandDriveRotate(double angle) {
		requires(Robot.subsystemDrive);
		this.controller = new PIDController(0.01, 0.0, 0.002, Robot.subsystemGyro, Robot.subsystemDrive);
		this.controller.setInputRange(0.0, 360.0);
		this.controller.setOutputRange(-0.5, 0.5);
		this.controller.setPercentTolerance(0.07);
		this.controller.setContinuous();
		this.controller.disable();
		this.angle = angle;
		this.timer = new Timer();
	}

	@Override
	protected void initialize() {
		Robot.subsystemDrive.reset();
		Robot.subsystemDrive.setMode(SwerveModule.SwerveMode.ModeSpeed);
		controller.reset();
		controller.setSetpoint(angle);
		controller.enable();
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		Robot.subsystemDrive.setTarget(0.0, controller.get(), 0.0, 0.0);
	}

	@Override
	protected boolean isFinished() {
		return controller.onTarget() || timer.get() > 2.0;
	}

	@Override
	protected void end() {
		timer.stop();
		controller.disable();
		Robot.subsystemDrive.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
