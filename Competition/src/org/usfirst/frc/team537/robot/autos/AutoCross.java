package org.usfirst.frc.team537.robot.autos;

import org.usfirst.frc.team537.robot.commands.CommandDriveDistance;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCross extends CommandGroup {
	public AutoCross(int location) {
		switch(location) {
			case 1: // Left.
				addSequential(new CommandDriveDistance(0.0f, 5.0f)); // Forward 5m.
				break;
			case 2: // Centre.
				addSequential(new CommandDriveDistance(0.0f, 2.0f)); // Forward 2m.
				addSequential(new CommandDriveDistance(3.0f, 0.0f)); // Right 3m.
				addSequential(new CommandDriveDistance(0.0f, 3.0f)); // Forward 3m.
				break;
			case 3: // Right.
				addSequential(new CommandDriveDistance(0.0f, 5.0f)); // Forward 5m.
				break;
			default:
				DriverStation.reportError("Invalid Cross Auto location: " + location, false);
				break;
		}
	}
}
