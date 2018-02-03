package org.usfirst.frc.team537.robot;

public class RobotMap {
	public static class Driver {
		public static final int PRIMARY_PORT = 0;
	}

	public static class Robot {
		public static final double WIDTH = 62.0;
		public static final double LENGTH = 53.0;
		public static final double RATIO = Math.sqrt((LENGTH * LENGTH) + (WIDTH * WIDTH));

		public static final float DRIVE_SPEED = 1.0f;
	}

	public static class PWM {
	}

	public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 10;
	
	public static class CAN {
		public static final int DRIVE_BACK_RIGHT_DRIVE = 1;
		public static final int DRIVE_BACK_RIGHT_ANGLE = 2;

		public static final int DRIVE_FRONT_RIGHT_DRIVE = 3;
		public static final int DRIVE_FRONT_RIGHT_ANGLE = 4;

		public static final int DRIVE_FRONT_LEFT_DRIVE = 5;
		public static final int DRIVE_FRONT_LEFT_ANGLE = 6;
		
		public static final int DRIVE_BACK_LEFT_DRIVE = 7;
		public static final int DRIVE_BACK_LEFT_ANGLE = 8;
	}

	public static class Digital {
		public static final double DRIVE_M_TO_ENCODER = -13460.19; // Ticks/Metre
	}

	public static class Analog {
	}

	public static class Solenoid {
	}
}
