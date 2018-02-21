package org.usfirst.frc.team537.robot.autos;

import org.usfirst.frc.team537.robot.commands.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSwitch extends CommandGroup {
	public AutoSwitch(int location, boolean isScaleLeft) {
		switch(location) {
			case 1: // Left.
				addSequential(new CommandDriveDistance(0.0, 0.3)); // Forward 30cm.
				addSequential(new CommandDriveDistance(-2.0, 0.0)); // Left 2m.
				break;
			case 2: // Centre.
				addSequential(new CommandDriveDistance(0.0, 0.3)); // Forward 30cm.
				break;
			case 3: // Right.
				addSequential(new CommandDriveDistance(0.0, 0.3)); // Forward 30cm.
				addSequential(new CommandDriveDistance(2.0, 0.0)); // Right 2m.
				break;
			default:
				DriverStation.reportError("Invalid Cross Auto location: " + location, false);
				break;
		}
		
		addSequential(new CommandCollectSpeed(-1.0), 1.8);
		addParallel(new CommandLiftSpeed(200.0), 2.0f);
		
		if (isScaleLeft) { // Switch Left.
			addSequential(new CommandDriveDistance(2.0, 0.0f)); // Right 2m.
			addSequential(new CommandDriveDistance(0.0, 0.9f)); // Forward 90cm.
			addSequential(new CommandCollectSpeed(1.0), 1.5);
		} else { // Switch Right.
			addSequential(new CommandDriveDistance(-2.0, 0.0)); // Left 2m.
			addSequential(new CommandDriveDistance(0.0, 0.9)); // Forward 90cm.
			addSequential(new CommandCollectSpeed(1.0), 1.5);
		}
	}
}
