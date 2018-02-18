package org.usfirst.frc.team537.robot.subsystems;

import org.usfirst.frc.team537.robot.RobotMap;
import org.usfirst.frc.team537.robot.helpers.Maths;
import org.usfirst.frc.team537.robot.helpers.PID;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveModule {
	public static enum SwerveMode {
		ModeSpeed(ControlMode.PercentOutput, new PID(0.0, 0.0, 0.0)), 
		ModeRate(ControlMode.Velocity, new PID(0.05, 0.0, 0.025, 0.3)), 
		ModeDistance(ControlMode.Position, new PID(0.03, 1.0E-5, 0.01));
		
		private ControlMode controlMode;
		private PID pidDrive;
		
		private SwerveMode(ControlMode controlMode, PID pidDrive) {
			this.controlMode = controlMode;
			this.pidDrive = pidDrive;
			SmartDashboard.putData(pidDrive);
		}
		
		public ControlMode getControlMode() {
			return controlMode;
		}

		public PID getPidDrive() {
			return pidDrive;
		}
	}
	
	private String moduleName;
	private TalonSRX talonAngle;
	private TalonSRX talonDrive;
	private double currentAngle;
	private double currentDrive;
	private double setpointAngle;
	private double setpointDrive;
	private SwerveMode swerveMode;

	public SwerveModule(String name, int portAngle, int portDrive, PID pidAngle) {
		moduleName = name;
		talonAngle = new TalonSRX(portAngle);
		talonDrive = new TalonSRX(portDrive);
		currentAngle = 0.0;
		currentDrive = 0.0;
		setpointAngle = 0.0;
		setpointDrive = 0.0;
		swerveMode = SwerveMode.ModeSpeed;

		talonAngle.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
        talonAngle.config_kP(RobotMap.kPIDLoopIdx, pidAngle.getP(), RobotMap.kTimeoutMs);
        talonAngle.config_kI(RobotMap.kPIDLoopIdx, pidAngle.getI(), RobotMap.kTimeoutMs); 
        talonAngle.config_kD(RobotMap.kPIDLoopIdx, pidAngle.getD(), RobotMap.kTimeoutMs);
    //    talonAngle.enableCurrentLimit(true);
    //    talonAngle.configPeakCurrentDuration(14, RobotMap.kTimeoutMs);
    //    talonAngle.configPeakCurrentLimit(28, RobotMap.kTimeoutMs);
		
		talonDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
	//	talonDrive.configClosedloopRamp(0.3, RobotMap.kTimeoutMs);
	//	talonDrive.configOpenloopRamp(0.3, RobotMap.kTimeoutMs);
		
	//	resetAngleReading();
	}

	public void dashboard() {
	//	SmartDashboard.putNumber(moduleName + " Angle", currentAngle);
	//	SmartDashboard.putNumber(moduleName + " Angle Setpoint", setpointAngle);
		
		SmartDashboard.putString(moduleName + " Drive (m)", (currentDrive / RobotMap.Digital.DRIVE_M_TO_ENCODER) + "m");
		
	//	SmartDashboard.putNumber(moduleName + " Drive", currentDrive);
	//	SmartDashboard.putNumber(moduleName + " Drive Setpoint", setpointDrive);
	//	SmartDashboard.putNumber(moduleName + " Drive Velocity", talonDrive.getSelectedSensorVelocity(RobotMap.kPIDLoopIdx));
	}

	public void setTarget(double angle, double drive, boolean driverControl) {
		currentAngle = talonAngle.getSelectedSensorPosition(RobotMap.kPIDLoopIdx);
		currentDrive = talonDrive.getSelectedSensorPosition(RobotMap.kPIDLoopIdx);

		angle = Maths.normalizeAngle(-angle);
		
		if (driverControl && angle >= 180.0) {
			angle -= 180.0;
			drive = -drive;
		}
		
		if (!driverControl || angle != 0.0) {
			angle = 4096.0 * (angle / 360.0) + 1;
			setpointAngle = angle;
			talonAngle.set(ControlMode.Position,  setpointAngle);
		}
		
		setpointDrive = drive;
		talonDrive.set(swerveMode.getControlMode(), setpointDrive);
	}
	
	public SwerveMode getMode() {
		return this.swerveMode;
	}
	
	public void setMode(SwerveMode swerveMode) {
        talonDrive.config_kP(RobotMap.kPIDLoopIdx, swerveMode.getPidDrive().getP(), RobotMap.kTimeoutMs);
        talonDrive.config_kI(RobotMap.kPIDLoopIdx, swerveMode.getPidDrive().getI(), RobotMap.kTimeoutMs); 
        talonDrive.config_kD(RobotMap.kPIDLoopIdx, swerveMode.getPidDrive().getD(), RobotMap.kTimeoutMs);
        talonDrive.config_kF(RobotMap.kPIDLoopIdx, swerveMode.getPidDrive().getF(), RobotMap.kTimeoutMs);
        
		this.swerveMode = swerveMode;
	}
	
	public void resetAngleReading() {
		talonAngle.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
	}
	
	public boolean isAtTarget() {
		switch (swerveMode) {
			case ModeSpeed:
				return true;
			case ModeRate:
				return Maths.nearTarget((double) talonDrive.getSelectedSensorVelocity(RobotMap.kPIDLoopIdx), setpointDrive, 0.9);
			case ModeDistance:
				return Maths.nearTarget((double) talonDrive.getSelectedSensorPosition(RobotMap.kPIDLoopIdx), setpointDrive, 0.07 * RobotMap.Digital.DRIVE_M_TO_ENCODER);
			default:
				return true;
		}
	}
	
	public boolean isAtAngle(double error) {
		return Maths.nearTarget((double) talonAngle.getSelectedSensorPosition(RobotMap.kPIDLoopIdx), setpointAngle, 4096.0 * (error / 360.0));
	}

	public void reset() {
		talonDrive.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
		swerveMode = SwerveMode.ModeSpeed;
	}

	public void stop() {
		talonDrive.set(ControlMode.PercentOutput, 0.0);
		swerveMode = SwerveMode.ModeSpeed;
	}
}
