package org.usfirst.frc.team537.robot.autos;

import org.usfirst.frc.team537.robot.commands.*;
import org.usfirst.frc.team537.robot.helpers.Colour;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCross extends CommandGroup {
	public AutoCross(int location, boolean isSwitchLeft, double delay) {
		addSequential(new CommandNothing(delay));
		
		switch(location) {
			case 1: // Left.
				DriverStation.reportWarning("AutoCross left!", false);
				addSequential(new CommandDriveRate(0.0, 0.9, 3.3)); // Forward 3.556m.
				addSequential(new CommandDriveRotate(0.0));
				break;
			case 2: // Centre.
				DriverStation.reportWarning("AutoCross centre!", false);
				addSequential(new CommandDriveRate(0.0, 0.8, 1.6)); // Forward 1m.
				addSequential(new CommandDriveRate(isSwitchLeft ? 90.0 : 270.0, 0.8, 3.4)); // Right 3m.
				addSequential(new CommandDriveRate(0.0, 0.65, 3.0)); // Forward 3m.
				addSequential(new CommandDriveRotate(0.0));
				break;
			case 3: // Right.
				DriverStation.reportWarning("AutoCross right!", false);
				addSequential(new CommandDriveRate(0.0, 0.9, 3.3)); // Forward 3.556m.
				addSequential(new CommandDriveRotate(0.0));
				break;
			default:
				DriverStation.reportError("Invalid Cross Auto location: " + location, false);
				break;
		}
		
	//	addSequential(new CommandLedsColour(new Colour("#0000ff"), 10.0));
	}
}
