package org.usfirst.frc.team537.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team537.robot.subsystems.SubsystemDrive;

import com.kauailabs.navx.frc.AHRS;

public class Robot extends IterativeRobot {
	public static AHRS ahrs;

	public static SubsystemDrive subsystemDrive;

	public static OI oi;

	private SendableChooser<Command> autoChooser;
	private Command autoCommand;

	@Override
	public void robotInit() {
		// Interfaces.
		try {
			ahrs = new AHRS(SPI.Port.kMXP);
		} catch (final RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);
		}

		Timer timerDashboard = new Timer();
		timerDashboard.schedule(new TimerTask() {
			@Override
			public void run() {
	            SmartDashboard.putNumber("NavX Angle", Maths.roundToPlace(ahrs.getAngle(), 3));
	            SmartDashboard.putNumber("NavX Angle Pitch", Maths.roundToPlace(ahrs.getPitch(), 3));
	            SmartDashboard.putNumber("NavX Angle Yaw", Maths.roundToPlace(ahrs.getYaw(), 3));
	            SmartDashboard.putNumber("NavX Angle Roll", Maths.roundToPlace(ahrs.getRoll(), 3));
	            SmartDashboard.putNumber("NavX Velocity X", Maths.roundToPlace(ahrs.getVelocityX(), 3));
	            SmartDashboard.putNumber("NavX Velocity Y", Maths.roundToPlace(ahrs.getVelocityY(), 3));
	            SmartDashboard.putNumber("NavX Velocity Z", Maths.roundToPlace(ahrs.getVelocityZ(), 3));
			}
		}, 0, 100);

		// Subsystems.
		subsystemDrive = new SubsystemDrive();

		// OI.
		oi = new OI();

		// Autonomous chooser to display on the dashboard.
		autoChooser = new SendableChooser<>();
		autoChooser.addObject("Nothing", null);
		SmartDashboard.putData("Autonomous", autoChooser);
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
		
		ahrs.reset();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
