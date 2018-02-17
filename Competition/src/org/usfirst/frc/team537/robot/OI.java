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

	//	this.joystickPrimary.getJoystickButton("Reset1").whenPressed(new CommandDriveReset());
	//	this.joystickPrimary.getJoystickButton("Reset2").whenPressed(new CommandGyroReset());

		// Joystick Secondary
		this.joystickSecondary = new JoystickBox(RobotMap.Driver.SECONDARY_PORT);

		this.joystickSecondary.getJoystickButton("RampDeployRight").whenPressed(new CommandRampsRelease(1));
		this.joystickSecondary.getJoystickButton("RampDeployLeft").whenPressed(new CommandRampsRelease(0));

		this.joystickSecondary.getJoystickButton("RampDownRight").whenPressed(new CommandRampsLift(1, -333.0));
		this.joystickSecondary.getJoystickButton("RampUpRight").whenPressed(new CommandRampsLift(1, 333.0));

		this.joystickSecondary.getJoystickButton("RampDownLeft").whenPressed(new CommandRampsLift(0, -333.0));
		this.joystickSecondary.getJoystickButton("RampUpLeft").whenPressed(new CommandRampsLift(0, 333.0));
		

		this.joystickSecondary.getJoystickButton("CubeUp").whenPressed(new CommandLiftVelocity(333.0));
		this.joystickSecondary.getJoystickButton("CubeDown").whenPressed(new CommandLiftVelocity(-333.0));

		this.joystickSecondary.getJoystickButton("CubeIn").whenPressed(new CommandCollectSpeed(1.0));
		this.joystickSecondary.getJoystickButton("CubeOut").whenPressed(new CommandCollectSpeed(-1.0));
	}
}
