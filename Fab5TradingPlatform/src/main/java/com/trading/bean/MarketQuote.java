package com.trading.bean;

public class MarketQuote implements Comparable<MarketQuote> {

	private String ticker;
	private String company;
	private double price;
	private double change;
	private double changePercentage;
	
	public MarketQuote(){
		// TODO Auto-generated constructor stub
	}

	public MarketQuote(String ticker, String company, double price, double change, double changePercentage) {
		super();
		this.ticker = ticker;
		this.company = company;
		this.price = price;
		this.change = change;
		this.changePercentage = changePercentage;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
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

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public double getChangePercentage() {
		return changePercentage;
	}

	public void setChangePercentage(double changePercentage) {
		this.changePercentage = changePercentage;
	}

	@Override
	public int compareTo(MarketQuote o) {
		
		if(this.changePercentage >= o.changePercentage)
			return 1;
		else
			return -1;
	}

	@Override
	public String toString() {
		return "MarketQuote [ticker=" + ticker + ", company=" + company + ", price=" + price + ", change=" + change
				+ ", changePercentage=" + changePercentage + "]";
	}
	
	
	
}
