package org.usfirst.frc.team537.robot;

import org.usfirst.frc.team537.robot.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
	public static SubsystemFeeder subsystemFeeder;
	public static SubsystemShooter subsystemShooter;
	public static SubsystemDrive subsystemDrive;

	public static OI oi;

	@Override
	public void robotInit() {
		// Subsystems.
		subsystemFeeder = new SubsystemFeeder();
		subsystemShooter = new SubsystemShooter();
		subsystemDrive = new SubsystemDrive();

		// OI.
		oi = new OI();
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void robotPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
}
