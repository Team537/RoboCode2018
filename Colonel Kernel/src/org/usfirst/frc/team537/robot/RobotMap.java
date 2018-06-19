package org.usfirst.frc.team537.robot;

public class RobotMap {
	public static class Driver {
		public static final int PRIMARY_PORT = 0;
	}
	
	public static class Subsystems {
	}

	public static class Robot {
		public static final double DRIVE_SPEED_MIN = 0.03;
		public static final double DRIVE_SPEED = 0.8;

		public static final double SHOOT_SPEED = 0.6;

		public static final double FEED_SPEED = 0.9;

		public static final double LED_BRIGHTNESS = 0.4;
	}

	public static class PIDs {
	}
	
	public static class PWM {
	}

	public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 10;
	
	public static class CAN {
		public static final int DRIVE_LEFT_MASTER = 2;
		public static final int DRIVE_LEFT_NORMAL = 1;
		public static final int DRIVE_LEFT_MINI = 3;
		public static final int DRIVE_RIGHT_MASTER = 9;
		public static final int DRIVE_RIGHT_NORMAL = 4;
		public static final int DRIVE_RIGHT_MINI = 6;

		public static final int SHOOTER_MASTER = 7;
		public static final int SHOOTER_NORMAL = 8;

		public static final int FEEDER = 10;
	}

	public static class Digital {
	}

	public static class Analog {
	}

	public static class Solenoid {
	}
}
