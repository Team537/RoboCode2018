package org.usfirst.frc.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.CommandRampLeftDefault;
import org.usfirst.frc.team537.robot.commands.CommandRampRightDefault;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SubsystemRamp extends Subsystem {
	private boolean isLeft;
	private Relay deploy;
	private TalonSRX rampLift;
	private boolean deployed;
	
	public SubsystemRamp(boolean isLeft) {
		setName("Ramp" + (isLeft ? "Left" : "Right"));
		
		this.isLeft = isLeft;
		this.deployed = false;
		
		if (isLeft) {
			deploy = new Relay(RobotMap.Solenoid.RAMP_DEPLOY_LEFT);
			rampLift = new TalonSRX(RobotMap.CAN.RAMP_LIFT_LEFT);
		} else {
			deploy = new Relay(RobotMap.Solenoid.RAMP_DEPLOY_RIGHT);
			rampLift = new TalonSRX(RobotMap.CAN.RAMP_LIFT_RIGHT);
		}
		
		deploy.set(Value.kOff);

		Timer timerDashboard = new Timer();
		timerDashboard.schedule(new TimerTask() {
			@Override
			public void run() {
				dashboard();
			}
		}, 0, 100);
	}

	@Override
	protected void initDefaultCommand() {
		if (isLeft) {
			setDefaultCommand(new CommandRampLeftDefault());
		} else {
			setDefaultCommand(new CommandRampRightDefault());
		}
	}

	public void dashboard() {
	}
	
	public void setToggled(boolean toggled) {
	//	deployed = !deployed;
		deploy.set(toggled ? Value.kForward : Value.kOff);
	}

	public void setLift(double speed) {
		if (deploy.get() == Value.kForward) {
			rampLift.set(ControlMode.PercentOutput, speed);
		} else {
			rampLift.set(ControlMode.PercentOutput, 0.0);
		}
	}

	public void reset() {
		stop();
	}
	
	public void stop() {
		rampLift.set(ControlMode.PercentOutput, 0.0);
		deploy.set(Value.kOff);
	}
}
