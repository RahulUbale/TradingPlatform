package com.trading.bean;

public class Ohlc {
	private double high;
	private double low;
	private double close;
	private double open;
    
    public Ohlc() {
		// TODO Auto-generated constructor stub
	}

	public Ohlc(double high, double low, double close, double open) {
		super();
		this.high = high;
		this.low = low;
		this.close = close;
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	@Override
	public String toString() {
		return "Ohlc [high=" + high + ", low=" + low + ", close=" + close + ", open=" + open + "]";
	}
    
	    
	    
}
