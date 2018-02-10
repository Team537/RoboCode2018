package org.usfirst.frc.team537.robot;

import org.usfirst.frc.team537.robot.commands.*;
import org.usfirst.frc.team537.robot.joysticks.IJoystick;
import org.usfirst.frc.team537.robot.joysticks.JoystickExtreme;

public class OI {
	public IJoystick joystickPrimary;

	public OI() {
		this.joystickPrimary = new JoystickExtreme(RobotMap.Driver.PRIMARY_PORT);
		this.joystickPrimary.getJoystickButton("DriveLock").whileHeld(new CommandDriveLock());
		this.joystickPrimary.getJoystickButton("Pivot0").whenPressed(new CommandDriveRotate(0.0f));
		this.joystickPrimary.getJoystickButton("Pivot90").whenPressed(new CommandDriveRotate(90.0f));
		this.joystickPrimary.getJoystickButton("Pivot180").whenPressed(new CommandDriveRotate(180.0f));
		this.joystickPrimary.getJoystickButton("Pivot270").whenPressed(new CommandDriveRotate(270.0f));
		this.joystickPrimary.getJoystickButton("Speed").whenPressed(new CommandDriveSpeed(270.0, 0.23f, 2.0f));
		this.joystickPrimary.getJoystickButton("Rate").whenPressed(new CommandDriveRate(45.0, 333.0f, 3.0f));
		this.joystickPrimary.getJoystickButton("Dist").whenPressed(new CommandDriveDistance(90.0, 2.0));
	}
}
