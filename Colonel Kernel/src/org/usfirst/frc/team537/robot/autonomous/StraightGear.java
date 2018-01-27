package org.usfirst.frc.team537.robot.autonomous;

import org.usfirst.frc.team537.robot.commands.Delay;
import org.usfirst.frc.team537.robot.commands.DriveRate;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StraightGear extends CommandGroup {
	public StraightGear() {
		addSequential(new DriveRate(100, 100, 4.4));
		addSequential(new Delay(1.0f));
		addSequential(new DriveRate(-80, -80, 1.6));
	}
}
