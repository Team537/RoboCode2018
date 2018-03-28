package org.usfirst.frc.team537.robot;

import org.usfirst.frc.team537.robot.helpers.*;

public class RobotMap {
	public static class Driver {
		public static final int PRIMARY_PORT = 0;
		public static final int SECONDARY_PORT = 1;
	}
	
	public static class Subsystems {
		public static final boolean CAMERA = true;
		public static final boolean COLLECT = false; // true
		public static final boolean LEDS = false; // true
		public static final boolean LIFT = false; // true
		public static final boolean RAMPS_LEFT = false; // true
		public static final boolean RAMPS_RIGHT = false; // true
	}

	public static class Robot {
		public static final double WIDTH = 20.7; // 26.0
		public static final double LENGTH = 20.7;
		public static final double RATIO = Math.sqrt((LENGTH * LENGTH) + (WIDTH * WIDTH));

		public static final double DRIVE_SPEED = 1.0;
		public static final boolean DRIVE_ENABLED_FRONT_LEFT = true;
		public static final boolean DRIVE_ENABLED_FRONT_RIGHT = true;
		public static final boolean DRIVE_ENABLED_BACK_LEFT = true;
		public static final boolean DRIVE_ENABLED_BACK_RIGHT = true;
		
		public static final double LED_BRIGHTNESS = 0.4;
		
		public static final boolean TESTING_MODE = true; // false
	}

	public static class PIDs { // Prototype Robot.
		public static final PID DRIVE_ANGLE_FRONT_LEFT = new PID(5.2, 0.0, 5.8);
		public static final PID DRIVE_ANGLE_FRONT_RIGHT = new PID(2.6, 0.0, 4.9);
		public static final PID DRIVE_ANGLE_BACK_LEFT =  new PID(5.2, 0.0, 5.8);
		public static final PID DRIVE_ANGLE_BACK_RIGHT = new PID(5.2, 0.0, 5.8);
		public static final PID DRIVE_ROTATE = new PID(0.01, 0.0, 0.002);
		public static final PID DRIVE_MODE_SPEED = new PID(0.0, 0.0, 0.0);
		public static final PID DRIVE_MODE_RATE = new PID(0.05, 0.0, 0.025, 0.3);
		public static final PID DRIVE_MODE_DISTANCE = new PID(0.22, 0.0, 0.0);
	}
	
	/*public static class PIDs { // Actual Robot.
		public static final PID DRIVE_ANGLE_FRONT_LEFT = new PID(4.9, 0.0, 4.0); // new PID(5.2, 0.0, 9.8)
		public static final PID DRIVE_ANGLE_FRONT_RIGHT = new PID(3.8, 0.0, 4.0); // new PID(2.6, 0.0, 4.9)
		public static final PID DRIVE_ANGLE_BACK_LEFT = new PID(5.4, 0.0, 4.3); // new PID(5.2, 0.0, 9.8)
		public static final PID DRIVE_ANGLE_BACK_RIGHT = new PID(5.4, 0.0, 4.3); // new PID(5.2, 0.0, 9.8)
		public static final PID DRIVE_ROTATE = new PID(0.01, 0.0, 0.002);
		public static final PID DRIVE_MODE_SPEED = new PID(0.0, 0.0, 0.0);
		public static final PID DRIVE_MODE_RATE = new PID(0.05, 0.0, 0.025, 0.3);
		public static final PID DRIVE_MODE_DISTANCE = new PID(0.22, 0.0, 0.0);
	}*/
	
	public static class PWM {
	}

	public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 10;
	
	public static class CAN {
		public static final int DRIVE_FRONT_LEFT_DRIVE = 4;
		public static final int DRIVE_FRONT_LEFT_ANGLE = 3;

		public static final int DRIVE_FRONT_RIGHT_DRIVE = 1;
		public static final int DRIVE_FRONT_RIGHT_ANGLE = 2;

		public static final int DRIVE_BACK_LEFT_DRIVE = 5;
		public static final int DRIVE_BACK_LEFT_ANGLE = 6;

		public static final int DRIVE_BACK_RIGHT_DRIVE = 8;
		public static final int DRIVE_BACK_RIGHT_ANGLE = 7;

		public static final int LIFT = 13;

		public static final int COLLECT_LEFT = 11;
		public static final int COLLECT_RIGHT = 12;

		public static final int RAMP_LIFT_LEFT = 14;
		public static final int RAMP_LIFT_RIGHT = 10;
	}

	public static class Digital {
		public static final double DRIVE_M_TO_ENCODER = 1984.4878; // Ticks/Metre
	}

	public static class Analog {
	}

	public static class Solenoid {
		public static final int RAMP_DEPLOY_LEFT = 2;
		public static final int RAMP_DEPLOY_RIGHT = 1;
	}
}
