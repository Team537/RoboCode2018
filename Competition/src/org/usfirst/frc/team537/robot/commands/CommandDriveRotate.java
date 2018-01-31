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
		this.controller = new PIDController(0.1, 0.0, 0.0, Robot.subsystemGyro, Robot.subsystemDrive);
		this.controller.setInputRange(0.0, 360.0);
		this.controller.setOutputRange(-0.4, 0.4);
		this.controller.setPercentTolerance(0.01);
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
		Robot.subsystemDrive.setTarget(0.0, controller.get(), 0.0, 0.0);
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
