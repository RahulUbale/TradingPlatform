package com.trading.service;

import com.trading.bean.HomePageBean;
import com.trading.bean.MoversAndNews;

public interface IHomePageService {
	
	public HomePageBean getHomePageData();
	
	public MoversAndNews getMoversAndNews();

}
