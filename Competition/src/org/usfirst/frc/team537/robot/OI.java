package org.usfirst.frc.team537.robot;

import org.usfirst.frc.team537.robot.commands.*;
import org.usfirst.frc.team537.robot.joysticks.*;

public class OI {
	public IJoystick joystickPrimary;
	public IJoystick joystickSecondary;

	public OI() {
		// Joystick Primary
		this.joystickPrimary = new JoystickExtreme(RobotMap.Driver.PRIMARY_PORT);

		this.joystickPrimary.getJoystickButton("DriveLock").whileHeld(new CommandDriveLock());
		this.joystickPrimary.getJoystickButton("Pivot").whileHeld(new CommandDrivePivot());

		this.joystickPrimary.getJoystickButton("GyroReset").whileHeld(new CommandGyroReset(0.0));

		// Joystick Secondary
		this.joystickSecondary = new JoystickBox(RobotMap.Driver.SECONDARY_PORT);
		
		if (RobotMap.Subsystems.RAMPS_LEFT) {
		//	this.joystickSecondary.getJoystickButton("RampDeployLeft").whileHeld(new CommandRampLeftRelease());
		//	this.joystickSecondary.getJoystickButton("RampDownLeft").whileHeld(new CommandRampLeftSpeed(-0.7));
		//	this.joystickSecondary.getJoystickButton("RampUpLeft").whileHeld(new CommandRampLeftSpeed(1.0));
		}

		if (RobotMap.Subsystems.RAMPS_RIGHT) {
		//	this.joystickSecondary.getJoystickButton("RampDeployRight").whileHeld(new CommandRampRightRelease());
		//	this.joystickSecondary.getJoystickButton("RampDownRight").whileHeld(new CommandRampRightSpeed(-0.7));
		//	this.joystickSecondary.getJoystickButton("RampUpRight").whileHeld(new CommandRampRightSpeed(1.0));
		}

		if (RobotMap.Subsystems.LIFT) {
			this.joystickSecondary.getJoystickButton("CubeUp").whileHeld(new CommandLiftSpeed(1.0));
			this.joystickSecondary.getJoystickButton("CubeDown").whileHeld(new CommandLiftSpeed(-0.8));
		}

		if (RobotMap.Subsystems.COLLECT) {
			this.joystickSecondary.getJoystickButton("CubeIn").whileHeld(new CommandCollectSpeed(-0.6));
			this.joystickSecondary.getJoystickButton("CubeOut").whileHeld(new CommandCollectSpeed(0.8));
		}
	}
}
