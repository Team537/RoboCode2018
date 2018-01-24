package org.usfirst.frc.team537.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	public Joystick joystickPrimary;

	public OI() {
		this.joystickPrimary = new Joystick(RobotMap.Driver.PRIMARY_PORT);
	}
}
