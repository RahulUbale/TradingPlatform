package com.trading.bean;

import java.util.List;

public class HomeBean {

	private List<MarketQuote> marketChangeData;
	
	public HomeBean() {
		// TODO Auto-generated constructor stub
	}

	public List<MarketQuote> getMarketChangeData() {
		return marketChangeData;
	}

	public void setMarketChangeData(List<MarketQuote> marketChangeData) {
		this.marketChangeData = marketChangeData;
	}

	@Override
	public String toString() {
		return "HomeBean [marketChangeData=" + marketChangeData + "]";
	}
	
	
}
