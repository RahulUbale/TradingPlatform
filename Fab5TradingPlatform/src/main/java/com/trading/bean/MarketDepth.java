package com.trading.bean;

import java.util.List;

public class MarketDepth {

    private List<Depth> buy;
    
    private List<Depth> sell;
    
    public MarketDepth() {
		// TODO Auto-generated constructor stub
	}

	public MarketDepth(List<Depth> buy, List<Depth> sell) {
		super();
		this.buy = buy;
		this.sell = sell;
	}

	public List<Depth> getBuy() {
		return buy;
	}

	public void setBuy(List<Depth> buy) {
		this.buy = buy;
	}

	public List<Depth> getSell() {
		return sell;
	}

	public void setSell(List<Depth> sell) {
		this.sell = sell;
	}
    
    
	
}
