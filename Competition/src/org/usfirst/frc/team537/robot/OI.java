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

		if (RobotMap.Subsystems.RAMPS) {
			this.joystickSecondary.getJoystickButton("RampDeployRight").whileHeld(new CommandRampsRelease(RampSide.SideRight));
			this.joystickSecondary.getJoystickButton("RampDownRight").whileHeld(new CommandRampsLift(RampSide.SideRight, -0.7));
			this.joystickSecondary.getJoystickButton("RampUpRight").whileHeld(new CommandRampsLift(RampSide.SideRight, 1.0));
	
			this.joystickSecondary.getJoystickButton("RampDeployLeft").whileHeld(new CommandRampsRelease(RampSide.SideLeft));
			this.joystickSecondary.getJoystickButton("RampDownLeft").whileHeld(new CommandRampsLift(RampSide.SideLeft, -0.7));
			this.joystickSecondary.getJoystickButton("RampUpLeft").whileHeld(new CommandRampsLift(RampSide.SideLeft, 1.0));
		}

		if (RobotMap.Subsystems.LIFT) {
			this.joystickSecondary.getJoystickButton("CubeUp").whileHeld(new CommandLiftSpeed(1.0));
			this.joystickSecondary.getJoystickButton("CubeDown").whileHeld(new CommandLiftSpeed(-0.6));
		}

		if (RobotMap.Subsystems.COLLECT) {
			this.joystickSecondary.getJoystickButton("CubeIn").whileHeld(new CommandCollectSpeed(-0.7));
			this.joystickSecondary.getJoystickButton("CubeOut").whileHeld(new CommandCollectSpeed(0.4));
		}
	}
}
