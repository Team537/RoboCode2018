package org.usfirst.frc.team537.robot;

import org.usfirst.frc.team537.robot.choosers.ChooserAuto;
import org.usfirst.frc.team537.robot.subsystems.SubsystemCamera;
import org.usfirst.frc.team537.robot.subsystems.SubsystemDrive;
import org.usfirst.frc.team537.robot.subsystems.SubsystemGyro;
import org.usfirst.frc.team537.robot.subsystems.SubsystemLeds;
import org.usfirst.frc.team537.robot.subsystems.SubsystemLift;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
	public static SubsystemCamera subsystemCamera;
	public static SubsystemGyro subsystemGyro;
	public static SubsystemLeds subsystemLeds;
	public static SubsystemDrive subsystemDrive;
	public static SubsystemLift subsystemLift;

	public static OI oi;

	private ChooserAuto autoChooser;
	private Command autoCommand;

	@Override
	public void robotInit() {
		// Subsystems.
		subsystemCamera = new SubsystemCamera();
		subsystemGyro = new SubsystemGyro();
		subsystemLeds = new SubsystemLeds();
		subsystemDrive = new SubsystemDrive();
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
