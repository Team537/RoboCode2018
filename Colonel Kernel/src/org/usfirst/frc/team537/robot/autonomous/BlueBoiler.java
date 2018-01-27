package org.usfirst.frc.team537.robot.autonomous;

import org.usfirst.frc.team537.robot.commands.DriveRate;
import org.usfirst.frc.team537.robot.commands.ShooterAutomatic;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueBoiler extends CommandGroup {
	public BlueBoiler() {
		addSequential(new ShooterAutomatic()); // Shoot
		addSequential(new DriveRate(-120.0, -120.0, 1.0)); // Back away from boiler.
		addSequential(new DriveRate(120.0, -120.0, 1.2)); // Turn around.
		addSequential(new DriveRate(120.0, 120.0, 4)); // Drive to hopper.
		// addSequential(new DriveRate(-120.0, 120.0, 1.1)); // Turn to face hopper.
		// addSequential(new DriveRate(315.0, 315.0, 1.7)); // Drive into hopper.
	}
}
