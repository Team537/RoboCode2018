package org.usfirst.frc.team537.robot.subsystems;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SubsystemCamera extends Subsystem {
	private UsbCamera usbCamera;
	
	
	public SubsystemCamera() {
		setName("Camera");
		usbCamera = CameraServer.getInstance().startAutomaticCapture(0);

		// mjpegServer = new MjpegServer("server_cam0", 1181);
		// mjpegServer.setSource(usbCamera);
		usbCamera.setResolution(320, 240);		// 320,240 
		usbCamera.setFPS(30);
	}

	@Override
	protected void initDefaultCommand() {
	}
}
