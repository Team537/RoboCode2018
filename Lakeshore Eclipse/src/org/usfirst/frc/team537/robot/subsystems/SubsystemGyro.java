package org.usfirst.frc.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team537.robot.commands.CommandGyroReset;
import org.usfirst.frc.team537.robot.helpers.Maths;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SubsystemGyro extends Subsystem implements PIDSource {
	private AHRS ahrs;
	
	public SubsystemGyro() {
		setName("Gyro");
		
		try {
			ahrs = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException e) {
			DriverStation.reportError("Error instantiating navX MXP: " + e.getMessage(), true);
		}
		
		Timer timerDashboard = new Timer();
		timerDashboard.schedule(new TimerTask() {
			@Override
			public void run() {
	            SmartDashboard.putNumber("NavX Angle", Maths.roundToPlace(getAngle(), 3));
			}
		}, 0, 100);
		
		reset();
	}

	@Override
	protected void initDefaultCommand() {
		SmartDashboard.putData("Gyro Reset", new CommandGyroReset(0.0));
	}

	public double getAngle() {
		return Maths.normalizeAngle(ahrs.getAngle());
	}
	
	public void setAngle(double angle) {
		ahrs.setAngleAdjustment(angle);
	}

	@Override
	public PIDSourceType getPIDSourceType() {
    	return ahrs.getPIDSourceType();
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		ahrs.setPIDSourceType(pidSource);
	}

	@Override
	public double pidGet() {
		return getAngle();
	}
	
	public void reset() {
		ahrs.reset();
	}
}
