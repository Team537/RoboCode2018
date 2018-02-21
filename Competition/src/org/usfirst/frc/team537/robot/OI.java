package org.usfirst.frc.team537.robot;

import org.usfirst.frc.team537.robot.commands.*;
import org.usfirst.frc.team537.robot.joysticks.*;
import org.usfirst.frc.team537.robot.subsystems.SubsystemRamps.RampSide;

public class OI {
	public IJoystick joystickPrimary;
	public IJoystick joystickSecondary;

	public OI() {
		// Joystick Primary
		this.joystickPrimary = new JoystickExtreme(RobotMap.Driver.PRIMARY_PORT);

		this.joystickPrimary.getJoystickButton("DriveLock").whileHeld(new CommandDriveLock());
		this.joystickPrimary.getJoystickButton("Pivot").whileHeld(new CommandDrivePivot());

		this.joystickPrimary.getJoystickButton("GyroReset").whileHeld(new CommandGyroReset());

		// Joystick Secondary
		this.joystickSecondary = new JoystickBox(RobotMap.Driver.SECONDARY_PORT);

		this.joystickSecondary.getJoystickButton("RampDeployRight").whileHeld(new CommandRampsRelease(RampSide.SideRight));
		this.joystickSecondary.getJoystickButton("RampDownRight").whileHeld(new CommandRampsLift(RampSide.SideRight, -0.7));
		this.joystickSecondary.getJoystickButton("RampUpRight").whileHeld(new CommandRampsLift(RampSide.SideRight, 1.0));

		this.joystickSecondary.getJoystickButton("RampDeployLeft").whileHeld(new CommandRampsRelease(RampSide.SideLeft));
		this.joystickSecondary.getJoystickButton("RampDownLeft").whileHeld(new CommandRampsLift(RampSide.SideLeft, -0.7));
		this.joystickSecondary.getJoystickButton("RampUpLeft").whileHeld(new CommandRampsLift(RampSide.SideLeft, 1.0));
		
		this.joystickSecondary.getJoystickButton("CubeUp").whenPressed(new CommandLiftSpeed(0.4));
		this.joystickSecondary.getJoystickButton("CubeDown").whenPressed(new CommandLiftSpeed(-0.4));

		this.joystickSecondary.getJoystickButton("CubeIn").whenPressed(new CommandCollectSpeed(0.3));
		this.joystickSecondary.getJoystickButton("CubeOut").whenPressed(new CommandCollectSpeed(-0.3));
	}
}
