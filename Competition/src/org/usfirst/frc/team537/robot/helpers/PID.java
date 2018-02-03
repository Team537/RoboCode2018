package org.usfirst.frc.team537.robot.helpers;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class PID extends SendableBase implements Sendable {
	private static int instances;
	private double p, i, d, f;

	public PID(double p, double i, double d, double f) {
		this.p = p;
		this.i = i;
		this.d = d;
		this.f = f;
		instances++;
	    setName("PID", instances);
	}

	public PID(double p, double i, double d) {
		this(p, i, d, 0.0);
	}

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

	public double getI() {
		return i;
	}

	public void setI(double i) {
		this.i = i;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public double getF() {
		return f;
	}

	public void setF(double f) {
		this.f = f;
	}

	@Override
	public void initSendable(SendableBuilder builder) {
	    builder.setSmartDashboardType("PIDController");
	    builder.addDoubleProperty("p", this::getP, this::setP);
	    builder.addDoubleProperty("i", this::getI, this::setI);
	    builder.addDoubleProperty("d", this::getD, this::setD);
	    builder.addDoubleProperty("f", this::getF, this::setF);
	//    builder.addDoubleProperty("setpoint", this::getSetpoint, this::setSetpoint);
	//    builder.addBooleanProperty("enabled", this::isEnabled, this::setEnabled);
	}
}
