package org.usfirst.frc.team537.robot;

public class RobotMap {
	/**
	 * Which PID slot to pull gains from.  Starting 2018, you can choose 
	 * from 0,1,2 or 3.  Only the first two (0,1) are visible in web-based configuration.
	 */
	public static final int kSlotIdx = 0;
	
	/* Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops.  
	 * For now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/*
	 * set to zero to skip waiting for confirmation, set to nonzero to wait
	 * and report to DS if action fails.
	 */
	public static final int kTimeoutMs = 10;
	
	public static class Driver {
		public static final int PRIMARY_PORT = 0;
	}

	public static class JoystickKeys {
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

	public static class JoystickAxes {
		public static final int STICK_X = 0;
		public static final int STICK_Y = 1;
		public static final int STICK_Z = 2;
		public static final int SLIDER = 3;
	}

	public static class Robot {
		public static final double WIDTH = 26.0;
		public static final double LENGTH = 26.0;
		public static final double RATIO = Math.sqrt((LENGTH * LENGTH) + (WIDTH * WIDTH));

	//	public static final float DRIVE_SPEED_X = 1.0f;
	//	public static final float DRIVE_SPEED_Y = 1.0f;
	//	public static final float DRIVE_SPEED_Z = 1.0f;
	}

	public static class PWM {
	}

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
	}

	public static class Analog {
	}

	public static class Solenoid {
	}
}
