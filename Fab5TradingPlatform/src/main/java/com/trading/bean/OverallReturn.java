package com.trading.bean;

public class OverallReturn {

	private double inInr;
	private double inPercent;

	public OverallReturn() {
		super();
	}

	public OverallReturn(double inInr, double inPercent) {
		super();
		this.inInr = inInr;
		this.inPercent = inPercent;
	}

	public double getInInr() {
		return inInr;
	}

	public void setInInr(double inInr) {
		this.inInr = inInr;
	}

	public double getInPercent() {
		return inPercent;
	}

	public void setInPercent(double inPercent) {
		this.inPercent = inPercent;
	}

}
