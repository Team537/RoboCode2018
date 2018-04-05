package org.usfirst.frc.team537.robot.autos;

import org.usfirst.frc.team537.robot.commands.*;
import org.usfirst.frc.team537.robot.helpers.Colour;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSwitch extends CommandGroup {
	public AutoSwitch(int location, boolean isSwitchLeft, double delay, boolean reversed) {
		addSequential(new CommandGyroReset(reversed ? 180.0 : 0.0));
		addSequential(new CommandNothing(delay));
		
		switch(location) {
			case 1: // Left.
				DriverStation.reportWarning("AutoSwitch left!", false);
				addSequential(new CommandDriveRate(0.0, 0.4, 2.0)); // Forward 30cm.
				addSequential(new CommandDriveRate(90.0, 0.6, 2.8)); // Left 2m.
				break;
			case 2: // Centre.
				DriverStation.reportWarning("AutoSwitch centre!", false);
				addSequential(new CommandDriveRate(0.0, 0.4, 2.0)); // Forward 30cm.
				break;
			case 3: // Right.
				DriverStation.reportWarning("AutoSwitch right!", false);
				addSequential(new CommandDriveRate(0.0, 0.4, 2.0)); // Forward 30cm.
				addSequential(new CommandDriveRate(270.0, 0.6, 2.8)); // Right 2m.
				break;
			default:
				DriverStation.reportError("Invalid Cross Auto location: " + location, false);
				break;
		}

		addParallel(new CommandLiftDeploy());
	//	addSequential(new CommandDriveRate(0.0, 0.8, 0.5)); // Forward 10cm.
	//	addParallel(new CommandCollectSpeed(-0.7), 1.8);
		addSequential(new CommandDriveRotate(180.0));
		
		if (isSwitchLeft) { // Switch Left.
			addSequential(new CommandDriveRate(270.0, 0.6, 1.33)); // Left 2m.
		//	addParallel(new CommandLiftSpeed(0.9), 2.0f);
			addSequential(new CommandDriveRate(0.0, 0.73, 1.5)); // Forward 90cm.
		//	addSequential(new CommandCollectSpeed(1.0), 1.5);
		} else { // Switch Right.
			addSequential(new CommandDriveRate(90.0, 0.6, 1.33)); // Right 2m.
		//	addParallel(new CommandLiftSpeed(0.9), 2.0f);
			addSequential(new CommandDriveRate(0.0, 0.73, 1.5)); // Forward 90cm.
		//	addSequential(new CommandCollectSpeed(1.0), 1.5);
		}
		
	//	addSequential(new CommandLedsColour(new Colour("#0000ff"), 10.0));
	}
}
