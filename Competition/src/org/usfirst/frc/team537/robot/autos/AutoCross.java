package org.usfirst.frc.team537.robot.autos;

import org.usfirst.frc.team537.robot.commands.*;
import org.usfirst.frc.team537.robot.helpers.Colour;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCross extends CommandGroup {
	public AutoCross(int location) {
		switch(location) {
			case 1: // Left.
				DriverStation.reportError("AutoCross left!", false);
				addSequential(new CommandDriveDistance(0.0, 3.556), 10.0); // Forward 3.556m.
				addSequential(new CommandDriveRotate(90.0));
				break;
			case 2: // Centre.
				DriverStation.reportError("AutoCross centre!", false);
				addSequential(new CommandDriveDistance(0.0, 1.0), 4.0); // Forward 1m.
				addSequential(new CommandDriveDistance(90.0, 3.7), 6.0); // Right 3m.
				addSequential(new CommandDriveDistance(0.0, 2.556), 4.0); // Forward 3m.
				addSequential(new CommandDriveRotate(90.0));
				break;
			case 3: // Right.
				DriverStation.reportError("AutoCross right!", false);
				addSequential(new CommandDriveDistance(0.0, 3.556), 10.0); // Forward 3.556m.
				addSequential(new CommandDriveRotate(90.0));
				break;
			default:
				DriverStation.reportError("Invalid Cross Auto location: " + location, false);
				break;
		}
		
		addSequential(new CommandLedsColour(new Colour("#0000ff")));
	}
}
