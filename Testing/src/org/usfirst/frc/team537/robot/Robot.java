package org.usfirst.frc.team537.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot {
	private Joystick joystick = new Joystick(0);

	private TalonSRX talon1 = new TalonSRX(1);
	private TalonSRX talon2 = new TalonSRX(2);
	
	@Override
	public void robotInit() {
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopPeriodic() {
		talon1.set(ControlMode.PercentOutput, joystick.getRawAxis(1));
		talon2.set(ControlMode.PercentOutput, joystick.getRawAxis(2));
	}

	@Override
	public void testPeriodic() {
	}
}
