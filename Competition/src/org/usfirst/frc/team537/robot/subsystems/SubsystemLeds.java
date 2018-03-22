package org.usfirst.frc.team537.robot.subsystems;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.CommandLedsDefault;
import org.usfirst.frc.team537.robot.helpers.Colour;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.LEDChannel;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SubsystemLeds extends Subsystem {
	private CANifier canifier = new CANifier(0);
	
	public SubsystemLeds() {
		setName("Leds");
		reset();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CommandLedsDefault());
	}

	public void setColour(Colour colour) {
		canifier.setLEDOutput(RobotMap.Robot.LED_BRIGHTNESS * colour.getR(), LEDChannel.LEDChannelA);
		canifier.setLEDOutput(RobotMap.Robot.LED_BRIGHTNESS * colour.getG(), LEDChannel.LEDChannelB);
		canifier.setLEDOutput(RobotMap.Robot.LED_BRIGHTNESS * colour.getB(), LEDChannel.LEDChannelC);
	}
	
	public void reset() {
		setColour(new Colour("#ff0000"));
	}
}
