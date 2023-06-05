package com.trading.bean;

public class Return {

	private String symbol;
	private String company;
	private double price;
	private double changeInPercent;
	private double change;
	private int shares;
	private double averageCost;
	private double totalCost;
	private double marketValue;
	private double gain;
	private double gainInPercent;
	
	public Return() {
		super();
		
	}
	
	public Return(String symbol, String company, double price, double changeInPercent, double change, int shares,
			double averageCost, double totalCost, double marketValue, double gain, double gainInPercent) {
		super();
		this.symbol = symbol;
		this.company = company;
		this.price = price;
		this.changeInPercent = changeInPercent;
		this.change = change;
		this.shares = shares;
		this.averageCost = averageCost;
		this.totalCost = totalCost;
		this.marketValue = marketValue;
		this.gain = gain;
		this.gainInPercent = gainInPercent;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getChangeInPercent() {
		return changeInPercent;
	}
	public void setChangeInPercent(double changeInPercent) {
		this.changeInPercent = changeInPercent;
	}
	public double getChange() {
		return change;
	}
	public void setChange(double change) {
		this.change = change;
	}
	public int getShares() {
		return shares;
	}
	public void setShares(int shares) {
		this.shares = shares;
	}
	public double getAverageCost() {
		return averageCost;
	}
	public void setAverageCost(double averageCost) {
		this.averageCost = averageCost;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public double getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}
	public double getGain() {
		return gain;
	}
	public void setGain(double gain) {
		this.gain = gain;
	}
	public double getGainInPercent() {
		return gainInPercent;
	}
	public void setGainInPercent(double gainInPercent) {
		this.gainInPercent = gainInPercent;
	}
	
	
	
	
}
