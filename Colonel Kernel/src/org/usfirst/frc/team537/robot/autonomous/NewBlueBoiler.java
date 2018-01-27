package org.usfirst.frc.team537.robot.autonomous;

import org.usfirst.frc.team537.robot.commands.AgitatorAgitate;
import org.usfirst.frc.team537.robot.commands.CollectorIntake;
import org.usfirst.frc.team537.robot.commands.DriveRate;
import org.usfirst.frc.team537.robot.commands.DriveSpeed;
import org.usfirst.frc.team537.robot.commands.FeederFeed;
import org.usfirst.frc.team537.robot.commands.ShooterShoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class NewBlueBoiler extends CommandGroup {
	public NewBlueBoiler() {
		addSequential(new DriveSpeed(0.33, 0.33, 2.5)); // fwd 12ft
		addSequential(new CollectorIntake(true), 2); // collect 2 sec
		addSequential(new DriveRate(-100.0, -100.0, 0.62)); // back 0.5ft
		addSequential(new DriveRate(-120.0, 120.0, 0.8)); // left 90deg
		addParallel(new ShooterShoot(), 10);// powers up flywheel
		addSequential(new DriveRate(400.0, 400.0, 2.0)); // fwd 10ft
		addParallel(new AgitatorAgitate(false), 10);// activates agitator
		addSequential(new FeederFeed()); // shoot
	}
}
