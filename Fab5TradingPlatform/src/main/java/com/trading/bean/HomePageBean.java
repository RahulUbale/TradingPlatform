package com.trading.bean;

import java.util.List;

import com.trading.pojos.News;

public class HomePageBean {
	
	private List<MarketQuote> marketChangeData;
	
	private List<News> newslist;
	
	private MarketMovers movers;

	
	public HomePageBean() {
		// TODO Auto-generated constructor stub
	}
	
	public List<MarketQuote> getMarketChangeData() {
		return marketChangeData;
	}

	public void setMarketChangeData(List<MarketQuote> marketChangeData) {
		this.marketChangeData = marketChangeData;
	}

	public List<News> getNewslist() {
		return newslist;
	}

	public void setNewslist(List<News> newslist) {
		this.newslist = newslist;
	}

	public MarketMovers getMovers() {
		return movers;
	}

	public void setMovers(MarketMovers movers) {
		this.movers = movers;
	}



}
