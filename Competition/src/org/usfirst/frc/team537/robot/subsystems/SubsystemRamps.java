package org.usfirst.frc.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.CommandRampsDefault;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SubsystemRamps extends Subsystem {
	public static enum RampSide {
		SideLeft, SideRight
	}
	
	private Relay deployLeft = new Relay(RobotMap.Solenoid.RAMP_DEPLOY_LEFT);
	private Relay deployRight = new Relay(RobotMap.Solenoid.RAMP_DEPLOY_RIGHT);
//	private TalonSRX rampLiftLeft = new TalonSRX(RobotMap.CAN.RAMP_LIFT_LEFT);
	private TalonSRX rampLiftRight = new TalonSRX(RobotMap.CAN.RAMP_LIFT_RIGHT);
	
	public SubsystemRamps() {
		setName("Ramp");
		deployLeft.set(Value.kOff);
		deployRight.set(Value.kOff);

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
		setDefaultCommand(new CommandRampsDefault());
	}

	public void dashboard() {
	}
	
	public void release(RampSide side) {
		switch (side) {
		case SideLeft:
			deployLeft.set(Value.kForward);
			break;
		case SideRight:
			deployRight.set(Value.kForward);
			break;
		}
	}

	public void setLift(RampSide side, double speed) {
		switch (side) {
		case SideLeft:
//			rampLiftLeft.set(ControlMode.PercentOutput, speed);
			break;
		case SideRight:
			rampLiftRight.set(ControlMode.PercentOutput, speed);
			break;
		}
	}

	public void reset() {
		stop();
	}
	
	public void stop() {
//		rampLiftLeft.set(ControlMode.PercentOutput, 0.0);
		rampLiftRight.set(ControlMode.PercentOutput, 0.0);
		deployLeft.set(Value.kOff);
		deployRight.set(Value.kOff);
	}
}
