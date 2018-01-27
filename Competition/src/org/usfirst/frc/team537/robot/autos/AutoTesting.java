package org.usfirst.frc.team537.robot.autos;

import org.usfirst.frc.team537.robot.commands.CommandDriveDistance;
import org.usfirst.frc.team537.robot.commands.CommandDriveRotate;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTesting extends CommandGroup {
	public AutoTesting() {
		addSequential(new CommandDriveRotate(180.0f));
		addSequential(new CommandDriveDistance(90.0f, 0.0f));
	}
}
