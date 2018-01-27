package org.usfirst.frc.team537.robot.autonomous;

import org.usfirst.frc.team537.robot.commands.AgitatorAgitate;
import org.usfirst.frc.team537.robot.commands.CollectorIntake;
import org.usfirst.frc.team537.robot.commands.DriveRate;
import org.usfirst.frc.team537.robot.commands.FeederFeed;
import org.usfirst.frc.team537.robot.commands.ShooterShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NewRedBoiler extends CommandGroup {
	public NewRedBoiler() {
		addSequential(new DriveRate(300.0, 300.0, 2.5)); // Forward 12ft
		addSequential(new CollectorIntake(true), 2); // Collect 2 sec
		addSequential(new DriveRate(-100.0, -100.0, 0.62)); // Back 0.5ft
		addSequential(new DriveRate(120.0, -120.0, 0.8)); // Left 90deg
		addParallel(new ShooterShoot(), 10);// Powers up flywheel
		addSequential(new DriveRate(500.0, 500.0, 2.0)); // Forward 10ft
		addParallel(new AgitatorAgitate(false), 10);// Activates agitator
		addSequential(new FeederFeed()); // shoot

	}
}