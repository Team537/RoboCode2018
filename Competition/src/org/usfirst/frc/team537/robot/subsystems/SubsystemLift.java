package org.usfirst.frc.team537.robot.subsystems;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.helpers.PID;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SubsystemLift extends Subsystem {
	private static PID pidLift = new PID(1.0, 0.0, 0.0);
	
	//private TalonSRX talonLift;
	
	public SubsystemLift() {
		setName("Lift");
	}

	@Override
	protected void initDefaultCommand() {
	
	//	talonLift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
	//	talonLift.config_kP(RobotMap.kPIDLoopIdx, pidLift.getP(), RobotMap.kTimeoutMs);
	//	talonLift.config_kI(RobotMap.kPIDLoopIdx, pidLift.getI(), RobotMap.kTimeoutMs); 
	//	talonLift.config_kD(RobotMap.kPIDLoopIdx, pidLift.getD(), RobotMap.kTimeoutMs);
	//	talonLift.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
	}
}
