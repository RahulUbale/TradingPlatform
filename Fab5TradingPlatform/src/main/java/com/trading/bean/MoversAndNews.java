package com.trading.bean;

import java.util.List;

import com.trading.pojos.News;

public class MoversAndNews {
	
	private List<News> newslist;
	
	private MarketMovers movers;
	
	public MoversAndNews() {
		// TODO Auto-generated constructor stub
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
