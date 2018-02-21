package org.usfirst.frc.team537.robot;

import org.usfirst.frc.team537.robot.choosers.*;
import org.usfirst.frc.team537.robot.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

// Robot name: Dumbo/Wumbo
public class Robot extends IterativeRobot {
	public static SubsystemCamera subsystemCamera;
	public static SubsystemGyro subsystemGyro;
	public static SubsystemCollect subsystemCollect;
	public static SubsystemDrive subsystemDrive;
	public static SubsystemRamps subsystemRamps;
	public static SubsystemLift subsystemLift;

	public static OI oi;

	private ChooserAuto autoChooser;
	private Command autoCommand;

	@Override
	public void robotInit() {
		// Subsystems.
	//	subsystemCamera = new SubsystemCamera();
		subsystemGyro = new SubsystemGyro();
		subsystemCollect = new SubsystemCollect();
		subsystemDrive = new SubsystemDrive();
		subsystemRamps = new SubsystemRamps();
		subsystemLift = new SubsystemLift();

		// OI.
		oi = new OI();

		// Autonomous chooser to display on the dashboard.
		autoChooser = new ChooserAuto();
	}

	@Override
	public void disabledInit() {
		if (autoCommand != null) {
			autoCommand.cancel();
		}
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
		autoCommand = autoChooser.getSelected();

		if (autoCommand != null) {
			autoCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autoCommand != null) {
			autoCommand.cancel();
			autoCommand = null;
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
}
