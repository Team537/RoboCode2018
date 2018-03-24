package org.usfirst.frc.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.CommandRampDefault;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SubsystemRamp extends Subsystem {
	public static enum RampSide {
		SideLeft("Left", RobotMap.Solenoid.RAMP_DEPLOY_LEFT, RobotMap.CAN.RAMP_LIFT_LEFT), 
		SideRight("Right", RobotMap.Solenoid.RAMP_DEPLOY_RIGHT, RobotMap.CAN.RAMP_LIFT_RIGHT);

		public final String name;
		public final Relay deploy;
		public final TalonSRX rampLift;
		public SubsystemRamp subsystem;
		
		RampSide(String name, int relay, int talon) {
			this.name = name;
			this.deploy = new Relay(relay);
			this.rampLift = new TalonSRX(talon);
			this.subsystem = null;
		}
	}
	
	private final RampSide side;
	private boolean deployed;
	
	public SubsystemRamp(RampSide side) {
		setName("Ramp" + side.name);
		side.subsystem = this;
		this.side = side;
		this.deployed = false;
		
		this.side.deploy.set(Value.kOff);

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
		setDefaultCommand(new CommandRampDefault(side));
	}

	public void dashboard() {
	}
	
	public boolean isDeployed() {
		return deployed;
	}
	
	public void setDeployed(boolean toggle) {
		deployed = toggle;
		side.deploy.set(deployed ? Value.kForward : Value.kOff);
	}
	
	public void toggleDeployed() {
		setDeployed(!isDeployed());
	}

	public void setSpeed(double speed) {
		if (side.deploy.get() != Value.kForward) {
			side.rampLift.set(ControlMode.PercentOutput, 0.0);
			return;
		}
		
		side.rampLift.set(ControlMode.PercentOutput, speed);
	}

	public void reset() {
		side.deploy.set(Value.kOff);
		stop();
	}
	
	public void stop() {
		side.rampLift.set(ControlMode.PercentOutput, 0.0);
	}
}
