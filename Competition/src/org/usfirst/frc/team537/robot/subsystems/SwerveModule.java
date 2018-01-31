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
		ModeRate(ControlMode.Velocity, new PID(0.0, 0.0, 0.0)), 
		ModeDistance(ControlMode.Position, new PID(3.0, 0.0, 0.0));
		
		private ControlMode controlMode;
		private PID pidDrive;
		
		private SwerveMode(ControlMode controlMode, PID pidDrive) {
			this.controlMode = controlMode;
			this.pidDrive = pidDrive;
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

		talonAngle.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
        talonAngle.config_kP(RobotMap.kPIDLoopIdx, pidAngle.getP(), RobotMap.kTimeoutMs);
        talonAngle.config_kI(RobotMap.kPIDLoopIdx, pidAngle.getI(), RobotMap.kTimeoutMs); 
        talonAngle.config_kD(RobotMap.kPIDLoopIdx, pidAngle.getD(), RobotMap.kTimeoutMs);
	//	talonAngle.setSelectedSensorPosition(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
		
		talonDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
	}

	public void dashboard() {
		SmartDashboard.putNumber(moduleName + " Angle", currentAngle);
	//	SmartDashboard.putNumber(moduleName + " Angle Error", setpointAngle - currentAngle);
		
	//	SmartDashboard.putNumber(moduleName + " Drive Position", currentDrive);
	//	SmartDashboard.putNumber(moduleName + " Drive Setpoint", setpointDrive);
	//	SmartDashboard.putNumber(moduleName + " Drive Error", setpointDrive - currentDrive);
	//	SmartDashboard.putNumber(moduleName + " Drive Speed", talonDrive.getSelectedSensorVelocity(RobotMap.kPIDLoopIdx));
	//	SmartDashboard.putNumber(moduleName + " Drive Position", talonDrive.getSelectedSensorPosition(RobotMap.kPIDLoopIdx));
	}

	public void setTarget(double angle, double drive) {
		angle = Maths.normalizeAngle(-angle + 360.0);
		currentAngle = talonAngle.getSelectedSensorPosition(RobotMap.kPIDLoopIdx);
	//	currentAngle = (talonAngle.getPulseWidthPosition() & 0xFFF) / 4096.0f;
		currentDrive = talonDrive.getSelectedSensorPosition(RobotMap.kPIDLoopIdx);

		talonAngle.set(ControlMode.Position, setpointAngle = 4096.0 * (angle / 360.0));
		talonDrive.set(swerveMode.getControlMode(), setpointDrive = drive);
	}
	
	public void setMode(SwerveMode swerveMode) {
		this.swerveMode = swerveMode;
        talonDrive.config_kP(RobotMap.kPIDLoopIdx, swerveMode.getPidDrive().getP(), RobotMap.kTimeoutMs);
        talonDrive.config_kI(RobotMap.kPIDLoopIdx, swerveMode.getPidDrive().getI(), RobotMap.kTimeoutMs); 
        talonDrive.config_kD(RobotMap.kPIDLoopIdx, swerveMode.getPidDrive().getD(), RobotMap.kTimeoutMs);
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

	public boolean isAtAngle() {
		return Maths.nearTarget((double) talonAngle.getSelectedSensorPosition(RobotMap.kPIDLoopIdx), setpointAngle, 18.15f);
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
