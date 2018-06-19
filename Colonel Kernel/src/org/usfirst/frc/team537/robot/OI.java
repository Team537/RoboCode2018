package org.usfirst.frc.team537.robot;

import org.usfirst.frc.team537.robot.commands.*;
import org.usfirst.frc.team537.robot.joysticks.*;

public class OI {
	public IJoystick joystickPrimary;

	public OI() {
		// Joystick Primary
		this.joystickPrimary = new JoystickF310(RobotMap.Driver.PRIMARY_PORT);

		this.joystickPrimary.getJoystickButton("Feed").whileHeld(new CommandFeederFeed());
		this.joystickPrimary.getJoystickButton("Shoot").whileHeld(new CommandShooterShoot());
	}
}
