package org.usfirst.frc.team537.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.commands.LightsColour;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lights extends Subsystem {
	private final Relay relay1 = new Relay(RobotMap.Analog.LIGHT_SPIKE_1); // +blue
																			// -common
	private final Relay relay2 = new Relay(RobotMap.Analog.LIGHT_SPIKE_2); // +red
																			// -green

	private Colour currentColour;

	public Lights() {
		this.currentColour = null;

		set(Colour.getDefault());

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
		setDefaultCommand(new LightsColour(null, Colour.getDefault()));
	}

	public void set(Colour colour) {
		this.currentColour = colour;

		if (colour != null) {
			relay1.set(colour.value1);
			relay2.set(colour.value2);
		} else {
			// OFF(Relay.Value.kOn, Relay.Value.kOn),
			relay1.set(Relay.Value.kOn);
			relay2.set(Relay.Value.kOn);
		}
	}

	private void dashboard() {
		SmartDashboard.putString("Lights Colour", (currentColour != null) ? currentColour.name() : "NULL");
		SmartDashboard.putString("Lights Relay 1", relay1.get().name());
		SmartDashboard.putString("Lights Relay 2", relay2.get().name());
	}

	public enum Colour {
		WHITE(Relay.Value.kReverse, Relay.Value.kOff), RED(Relay.Value.kOn, Relay.Value.kReverse), BLUE(
				Relay.Value.kReverse, Relay.Value.kOn), GREEN(Relay.Value.kOn,
						Relay.Value.kForward), YELLOW(Relay.Value.kOn, Relay.Value.kOff), CYAN(Relay.Value.kReverse,
								Relay.Value.kForward), MAGENTA(Relay.Value.kReverse, Relay.Value.kReverse);

		private final Relay.Value value1;
		private final Relay.Value value2;

		Colour(Relay.Value value1, Relay.Value value2) {
			this.value1 = value1;
			this.value2 = value2;
		}

		public Relay.Value getValue1() {
			return value1;
		}

		public Relay.Value getValue2() {
			return value2;
		}

		public static Colour getDefault() {
			Alliance alliance = null;

			if (DriverStation.getInstance() != null) {
				alliance = DriverStation.getInstance().getAlliance();
			}

			switch (alliance) {
			case Red:
				return RED;
			case Blue:
				return BLUE;
			default:
				return WHITE;
			}
		}
	}
}
