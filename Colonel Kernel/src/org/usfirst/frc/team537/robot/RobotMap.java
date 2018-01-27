package org.usfirst.frc.team537.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static enum ControlType {
		F310, EXTREME
	}

	/**
	 * A class that holds static values for driver Controls.
	 */
	public static class Driver {
		public static final int BOX_PORT = 0;
		public static final int PRIMARY_PORT = 1;
		public static final int SECONDARY_PORT = 2;

		public static final double SENSITIVITY = 0.5;
		public static final ControlType CONTROL = ControlType.F310;
		public static final boolean ARCADE_DRIVE = false;
	}

	/**
	 * A class that holds static values for extra robot constants.
	 */
	public static class Robot {
		public static final double DRIVE_SPEED_MIN = 0.03;
		public static final double DRIVE_SPEED = 1.0;
		public static final double AGITATOR_SPEED = 1.0;
		public static final double COLLECT_SPEED = 1.0;
		public static final double FEEDER_SPEED = 0.8;
		public static final double SHOOTER_MAX_ERROR = 40.0;
		public static final double SHOOTER_SPEED = 3000.0;
		public static final double SHOOTER_SPEED_CLIMB = 5370.0;
	}

	/**
	 * A class that holds static values for the robots LED strips.
	 */
	public static class LEDs {
		public static final double CHARGER_R = 0.90196078431;
		public static final double CHARGER_G = 0.08235294117;
		public static final double CHARGER_B = 0.08235294117;
	}

	/**
	 * A class that holds static values for joystick keys (Logitech X3D Pro).
	 */
	public static class KeysF310 {
		public static final int A_BUTTON = 1;
		public static final int THUMB_TRIGGER = 2;
		public static final int STICK_3 = 3;
		public static final int STICK_4 = 4;
		public static final int STICK_5 = 5;
		public static final int STICK_6 = 6;
		public static final int BASE_7 = 7;
		public static final int BASE_8 = 8;
		public static final int BASE_9 = 9;
		public static final int BASE_10 = 10;
		public static final int BASE_11 = 11;
		public static final int BASE_12 = 12;
	}

	/**
	 * A class that holds static values for joystick axes (Logitech X3D Pro).
	 */
	public static class AxesF310 {
		public static final int STICK_LEFT_X = 0;
		public static final int STICK_LEFT_Y = 1;
		public static final int STICK_RIGHT_X = 4;
		public static final int STICK_RIGHT_Y = 5;
		public static final int THROTTLE = 3;
	}

	/**
	 * A class that holds static values for joystick keys (Logitech X3D Pro).
	 */
	public static class KeysExtreme {
		public static final int INDEX_TRIGGER = 1;
		public static final int THUMB_TRIGGER = 2;
		public static final int STICK_3 = 3;
		public static final int STICK_4 = 4;
		public static final int STICK_5 = 5;
		public static final int STICK_6 = 6;
		public static final int BASE_7 = 7;
		public static final int BASE_8 = 8;
		public static final int BASE_9 = 9;
		public static final int BASE_10 = 10;
		public static final int BASE_11 = 11;
		public static final int BASE_12 = 12;
	}

	/**
	 * A class that holds static values for joystick axes (Logitech X3D Pro).
	 */
	public static class AxesExtreme {
		public static final int STICK_X = 0;
		public static final int STICK_Y = 1;
		public static final int STICK_Z = 2;
		public static final int SLIDER = 3;
	}

	public static class ControlBox {
		public static final int FIRE_BUTTON = 1;
		public static final int CLIMB_TOGGLE = 2;
		public static final int FLYWHEEL_TOGGLE = 4;
		public static final int COLLECTOR_IN = 5;
		public static final int COLLECTOR_OUT = 6;
		public static final int AGRIGATOR_IN = 8;
		public static final int AGRIGATOR_OUT = 7;
	}

	public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 10;
	
	/**
	 * A class that holds static values for PWM Channels.
	 */
	public static class PWM {
	}

	/*
	 * A class that holds static values for CAN bus Channels.
	 */
	public static class CAN {
		public static final int DRIVE_LEFT_MASTER = 2;
		public static final int DRIVE_LEFT_NORMAL = 1;
		public static final int DRIVE_LEFT_MINI = 3;
		public static final int DRIVE_RIGHT_MASTER = 5;
		public static final int DRIVE_RIGHT_NORMAL = 4;
		public static final int DRIVE_RIGHT_MINI = 6;

		public static final int SHOOTER_MASTER = 7;
		public static final int SHOOTER_SLAVE = 8;

		public static final int COLLECTOR = 9;

		public static final int FEEDER = 10;

		public static final int AGITATOR = 11;
	}

	/**
	 * A class that holds static values for Digital Inputs.
	 */
	public static class Digital {
		public static final double DRIVE_IN_TO_ENCODER = 1.0 / (0.14605 * Math.PI); // Metre => Encoder Ticks: (distance 'm' / (wheel diameter 'm' * PI)).
	}

	/**
	 * A class that holds static values for Analog Inputs.
	 */
	public static class Analog {
		public static final int LIGHT_SPIKE_1 = 0;
		public static final int LIGHT_SPIKE_2 = 1;
	}

	/**
	 * A class that holds static values for Solenoids.
	 */
	public static class Solenoid {
	}
}
