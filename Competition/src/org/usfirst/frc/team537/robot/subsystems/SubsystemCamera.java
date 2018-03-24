package org.usfirst.frc.team537.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SubsystemCamera extends Subsystem {
	private UsbCamera usbCamera;
//	private MjpegServer mjpegServer;
	
	public SubsystemCamera() {
		setName("Camera");
		usbCamera = CameraServer.getInstance().startAutomaticCapture("cam0", 0);
	//	usbCamera.setResolution(320, 240);
	//	usbCamera.setFPS(30);

	///	mjpegServer = new MjpegServer("server_cam0", 1181);
	//	mjpegServer.setSource(usbCamera);
	}

	@Override
	protected void initDefaultCommand() {
	}
}
