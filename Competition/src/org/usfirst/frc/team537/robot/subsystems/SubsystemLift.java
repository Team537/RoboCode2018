package org.usfirst.frc.team537.robot.subsystems;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.CommandLiftDefault;
import org.usfirst.frc.team537.robot.helpers.PID;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SubsystemLift extends Subsystem {
	private static PID pidLift = new PID(1.0, 0.0, 0.0);
	
	private TalonSRX talonLift = new TalonSRX(RobotMap.CAN.LIFT);

	private DigitalInput limitTop = new DigitalInput(RobotMap.Analog.SWITCH_LIMIT_UP);
	private DigitalInput limitBottom = new DigitalInput(RobotMap.Analog.SWITCH_LIMIT_BOTTOM);
	
	public SubsystemLift() {
		setName("Lift");
		
		talonLift.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
		talonLift.config_kP(RobotMap.kPIDLoopIdx, pidLift.getP(), RobotMap.kTimeoutMs);
		talonLift.config_kI(RobotMap.kPIDLoopIdx, pidLift.getI(), RobotMap.kTimeoutMs); 
		talonLift.config_kD(RobotMap.kPIDLoopIdx, pidLift.getD(), RobotMap.kTimeoutMs);
		talonLift.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CommandLiftDefault());
	}

	public void setVelocity(double velocity) {
		talonLift.set(ControlMode.Velocity, velocity);

		if (limitTop.isAnalogTrigger() && velocity > 0) {
			stop();
		} else if (limitBottom.isAnalogTrigger() && velocity < 0) {
			stop();
		}
	}
	
	public void setPosition(double position) {
		talonLift.set(ControlMode.Position, position);
		
		if (limitTop.isAnalogTrigger() && position > RobotMap.Robot.LIFT_HEIGHT_CM * RobotMap.Digital.LIFT_CM_TO_ENCODER) {
			stop();
		} else if (limitBottom.isAnalogTrigger() && position > 0) {
			stop();
		}
	}
	
	public void reset() {
		stop();
	}
	
	public void stop() {
		talonLift.set(ControlMode.PercentOutput, 0.0);
	}
}
