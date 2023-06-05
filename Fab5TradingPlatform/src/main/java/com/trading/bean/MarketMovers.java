package com.trading.bean;

import java.util.List;

public class MarketMovers {

	private List<MarketQuote> topFive;
	
	private List<MarketQuote> bottomFive;
	
	public List<MarketQuote> getTopFive() {
		return topFive;
	}

	public void setTopFive(List<MarketQuote> topFive) {
		this.topFive = topFive;
	}

	public List<MarketQuote> getBottomFive() {
		return bottomFive;
	}

	public void setBottomFive(List<MarketQuote> bottomFive) {
		this.bottomFive = bottomFive;
	}
	
	
}
