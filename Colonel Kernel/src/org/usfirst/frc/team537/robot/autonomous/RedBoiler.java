package org.usfirst.frc.team537.robot.autonomous;

import org.usfirst.frc.team537.robot.commands.DriveRate;
import org.usfirst.frc.team537.robot.commands.ShooterAutomatic;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedBoiler extends CommandGroup {
	public RedBoiler() {
		addSequential(new ShooterAutomatic());
		addSequential(new DriveRate(-120.0, -120.0, 1.0));
		addSequential(new DriveRate(-120.0, 120.0, 1.2));
		addSequential(new DriveRate(120.0, 120.0, 4));
		// addSequential(new DriveRate(120.0, -120.0, 1.1));
		// addSequential(new DriveRate(315.0, 315.0, 1.7));
	}
}
