package org.usfirst.frc.team537.robot;

public class RobotMap {
	public static class Driver {
		public static final int PRIMARY_PORT = 0;
		public static final int SECONDARY_PORT = 1;
	}

	public static class Robot {
		public static final double WIDTH = 26.0;
		public static final double LENGTH = 20.7;
		public static final double RATIO = Math.sqrt((LENGTH * LENGTH) + (WIDTH * WIDTH));

		public static final double DRIVE_SPEED = 1.0;
	}

	public static class PWM {
	}

	public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 10;
	
	public static class CAN {
		public static final int DRIVE_BACK_RIGHT_DRIVE = 8;
		public static final int DRIVE_BACK_RIGHT_ANGLE = 7;

		public static final int DRIVE_FRONT_RIGHT_DRIVE = 1;
		public static final int DRIVE_FRONT_RIGHT_ANGLE = 2;

		public static final int DRIVE_FRONT_LEFT_DRIVE = 4;
		public static final int DRIVE_FRONT_LEFT_ANGLE = 3;
		
		public static final int DRIVE_BACK_LEFT_DRIVE = 5;
		public static final int DRIVE_BACK_LEFT_ANGLE = 6;

		public static final int LIFT = 9;

		public static final int COLLECT_LEFT = 10;
		public static final int COLLECT_RIGHT = 11;

		public static final int RAMP_LIFT_LEFT = 12;
		public static final int RAMP_LIFT_RIGHT = 13;
	}

	public static class Digital {
		public static final double DRIVE_M_TO_ENCODER = 12838.916; // Ticks/Metre
	}

	public static class Analog {
	}

	public static class Solenoid {
		public static final int RAMP_DEPLOY_LEFT = 2;
		public static final int RAMP_DEPLOY_RIGHT = 1;
	}
}
