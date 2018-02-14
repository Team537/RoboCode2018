package org.usfirst.frc.team537.robot;

import org.usfirst.frc.team537.robot.commands.*;
import org.usfirst.frc.team537.robot.joysticks.IJoystick;
import org.usfirst.frc.team537.robot.joysticks.JoystickExtreme;

public class OI {
	public IJoystick joystickPrimary;

	public OI() {
		this.joystickPrimary = new JoystickExtreme(RobotMap.Driver.PRIMARY_PORT);
		this.joystickPrimary.getJoystickButton("DriveLock").whileHeld(new CommandDriveLock());
		this.joystickPrimary.getJoystickButton("Pivot").whileHeld(new CommandDrivePivot());

		this.joystickPrimary.getJoystickButton("Reset1").whenPressed(new CommandDriveReset());
		this.joystickPrimary.getJoystickButton("Reset2").whenPressed(new CommandGyroReset());
	}
}
