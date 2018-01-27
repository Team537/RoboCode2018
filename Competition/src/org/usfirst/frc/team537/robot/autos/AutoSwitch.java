package org.usfirst.frc.team537.robot.autos;

import org.usfirst.frc.team537.robot.commands.CommandDriveDistance;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSwitch extends CommandGroup {
	public AutoSwitch(int location, boolean isScaleLeft) {
		switch(location) {
			case 1: // Left.
				addSequential(new CommandDriveDistance(0.0f, 0.3f)); // Forward 30cm.
				addSequential(new CommandDriveDistance(-2.0f, 0.0f)); // Left 2m.
				break;
			case 2: // Centre.
				addSequential(new CommandDriveDistance(0.0f, 0.3f)); // Forward 30cm.
				break;
			case 3: // Right.
				addSequential(new CommandDriveDistance(0.0f, 0.3f)); // Forward 30cm.
				addSequential(new CommandDriveDistance(2.0f, 0.0f)); // Right 2m.
				break;
			default:
				DriverStation.reportError("Invalid Cross Auto location: " + location, false);
				break;
		}
		
		if (isScaleLeft) { // Switch Left.
			addSequential(new CommandDriveDistance(2.0f, 0.0f)); // Right 2m.
			addSequential(new CommandDriveDistance(0.0f, 0.9f)); // Forward 90cm.
			// Drop cube.
		} else { // Switch Right.
			addSequential(new CommandDriveDistance(-2.0f, 0.0f)); // Left 2m.
			addSequential(new CommandDriveDistance(0.0f, 0.9f)); // Forward 90cm.
			// Drop cube.
		}
	}
}
