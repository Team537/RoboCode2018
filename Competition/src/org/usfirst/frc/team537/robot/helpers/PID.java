package org.usfirst.frc.team537.robot.helpers;

public class PID {
	private double p, i, d;
	
	public PID(double p, double i, double d) {
		this.p = p;
		this.i = i;
		this.d = d;
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
}
