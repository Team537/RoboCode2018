package org.usfirst.frc.team537.robot;

import org.usfirst.frc.team537.robot.commands.*;
import org.usfirst.frc.team537.robot.joysticks.IJoystick;
import org.usfirst.frc.team537.robot.joysticks.JoystickF310;

public class OI {
	public IJoystick joystickPrimary;

	public OI() {
		this.joystickPrimary = new JoystickF310(RobotMap.Driver.PRIMARY_PORT);
		this.joystickPrimary.getJoystickButton("Pivot0").whenPressed(new CommandDriveRotate(0.0f));
		this.joystickPrimary.getJoystickButton("Pivot90").whenPressed(new CommandDriveRotate(90.0f));
		this.joystickPrimary.getJoystickButton("Pivot180").whenPressed(new CommandDriveRotate(180.0f));
		this.joystickPrimary.getJoystickButton("Pivot270").whenPressed(new CommandDriveRotate(270.0f));
	}
}
