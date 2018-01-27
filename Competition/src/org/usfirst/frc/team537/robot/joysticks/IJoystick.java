package org.usfirst.frc.team537.robot.joysticks;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public abstract class IJoystick extends Joystick {
	public static class ValueUsage {
		private int value;
		private boolean inversed;
		
		public ValueUsage(int value, boolean inversed) {
			this.value = value;
			this.inversed = inversed;
		}	
	}
	
	private Map<String, ValueUsage> mappedUsages;
	
	public IJoystick(int port) {
		super(port);
		mappedUsages = new HashMap<>();
	}

	public abstract String getJoystickType();
	
	protected void add(String shortName, ValueUsage valueUsage) {
		mappedUsages.put(shortName, valueUsage);
	}

	public ValueUsage get(String shortName) {
		if (!mappedUsages.containsKey(shortName)) {
			ValueUsage valueUsage = new ValueUsage(-1, false);
			DriverStation.reportError("IJoystick implementation missing usage for: " + mappedUsages, false);
			add(shortName, valueUsage);
			return valueUsage;
		}
		
		return mappedUsages.get(shortName);
	}
	
	public double getRawAxis(String shortName) {
		ValueUsage valueUsage = get(shortName);
		return (valueUsage.inversed ? -1.0 : 1.0) * getRawAxis(valueUsage.value);
	}
	
	public boolean getRawButton(String shortName) {
		ValueUsage valueUsage = get(shortName);
		return valueUsage.inversed ? !getRawButton(valueUsage.value) : getRawButton(valueUsage.value);
	}
	
	public JoystickButton getJoystickButton(String shortName) {
		ValueUsage valueUsage = get(shortName);
		return new JoystickButton(this, valueUsage.value);
	}
}
