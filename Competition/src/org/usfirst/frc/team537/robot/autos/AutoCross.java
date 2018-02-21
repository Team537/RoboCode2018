package org.usfirst.frc.team537.robot.autos;

import org.usfirst.frc.team537.robot.commands.CommandDriveDistance;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCross extends CommandGroup {
	public AutoCross(int location) {
		DriverStation.reportError("AutoCross location: " + location, false);
		
		switch(location) {
			case 1: // Left.
				addSequential(new CommandDriveDistance(0.0, 3.556)); // Forward 3.556m.
				break;
			case 2: // Centre.
				addSequential(new CommandDriveDistance(0.0, 1.0)); // Forward 1m.
				addSequential(new CommandDriveDistance(90.0, 3.7)); // Right 3m.
				addSequential(new CommandDriveDistance(0.0, 2.556)); // Forward 3m.
				break;
			case 3: // Right.
				addSequential(new CommandDriveDistance(0.0, 3.556)); // Forward 3.556m.
				break;
			default:
				DriverStation.reportError("Invalid Cross Auto location: " + location, false);
				break;
		}
	}
}
