package org.usfirst.frc.team537.robot.helpers;

public class Colour {
	private double r, g, b;

	public Colour() {
		this.r = 0.0;
		this.g = 0.0;
		this.b = 0.0;
	}

	public Colour(double r, double g, double b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public Colour(String hex) {
		this.r = (double) Integer.valueOf(hex.substring(1, 3), 16) / 255.0;
		this.b = (double) Integer.valueOf(hex.substring(3, 5), 16) / 255.0;
		this.g = (double) Integer.valueOf(hex.substring(5, 7), 16) / 255.0;
	}
	
	public static Colour interpolate(Colour left, Colour right, double blend) {
		double r = ((1.0 - blend) * left.r) + (blend * right.r);
		double g = ((1.0 - blend) * left.g) + (blend * right.g);
		double b = ((1.0 - blend) * left.b) + (blend * right.b);
		return new Colour(r, g, b);
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}
}
