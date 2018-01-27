package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;
import org.usfirst.frc.team537.robot.subsystems.SwerveModule;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandDriveRotate extends Command {
	private PIDController controller;
	private double angle;
	
	public CommandDriveRotate(double angle) {
		requires(Robot.subsystemDrive);
		this.controller = new PIDController(1.0f, 0.0f, 0.0f, Robot.subsystemGyro, Robot.subsystemDrive);
		this.controller.setInputRange(0.0f, 360.0f);
		this.controller.setOutputRange(-0.2f, 0.2f);
		this.controller.setPercentTolerance(0.05f);
		this.controller.setContinuous();
		this.controller.enable();
		this.angle = angle;
	}

	@Override
	protected void initialize() {
		Robot.subsystemDrive.reset();
		Robot.subsystemDrive.setMode(SwerveModule.SwerveMode.ModeSpeed);
		controller.setSetpoint(angle);
	}

	@Override
	protected void execute() {
		SmartDashboard.getNumber("Rotate Error", controller.getError());
		Robot.subsystemDrive.setTarget(0.0f, angle, controller.get());
	}

	@Override
	protected boolean isFinished() {
		return controller.onTarget();
	}

	@Override
	protected void end() {
		controller.disable();
		Robot.subsystemDrive.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
