package org.usfirst.frc.team537.robot.commands;

import org.usfirst.frc.team537.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class CommandLiftDeploy extends Command {
	private Timer timer;
	private int state;
	
	public CommandLiftDeploy() {
		requires(Robot.subsystemLift);
		this.timer = new Timer();
		this.state = 0;
	}

	@Override
	protected void initialize() {
		Robot.subsystemLift.reset();
		timer.reset();
		timer.start();
		state = 0;
	}

	@Override
	protected void execute() {
		switch (state) {
		case 0:
			if (timer.get() > 1.1) {
				timer.reset();
				timer.start();
				state = 1;
			}
			
			Robot.subsystemLift.setSpeed(-1.0);
			break;
		case 1:
			if (timer.get() > 0.9) {
				state = 2;
			}
			
			Robot.subsystemLift.setSpeed(0.8);
			break;
		case 2:
			Robot.subsystemLift.setSpeed(0.0);
			break;
		}
	}
	
	@Override
	protected boolean isFinished() {
		return state == 2;
	}

	@Override
	protected void end() {
		Robot.subsystemLift.stop();
		timer.stop();
		state = 0;
	}

	@Override
	protected void interrupted() {
		end();
	}
}
