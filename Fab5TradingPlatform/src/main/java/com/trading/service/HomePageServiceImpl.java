package com.trading.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trading.api.KiteConnectAPI;
import com.trading.bean.HomePageBean;
import com.trading.bean.MarketQuote;
import com.trading.bean.MoversAndNews;
import com.trading.dao.INewsRepository;

@Service
@Transactional
public class HomePageServiceImpl implements IHomePageService {

	@Autowired
	INewsRepository dao;
	
	public HomePageServiceImpl() {

	}

	@Override
	public HomePageBean getHomePageData() {
		System.out.println("In Homepage Data");
		List<MarketQuote> marketChange = KiteConnectAPI.getExchangeData();
		HomePageBean homePageBean = new HomePageBean();
		homePageBean.setMarketChangeData(marketChange);
		
		homePageBean.setNewslist(dao.getAllNews());
		
		homePageBean.setMovers(KiteConnectAPI.getMarketMovers());
		
		return homePageBean;
	}

	@Override
	public MoversAndNews getMoversAndNews() {
		System.out.println("In Movers and News");
		MoversAndNews homeBean = new MoversAndNews();
			
		homeBean.setNewslist(dao.getAllNews());
		
		homeBean.setMovers(KiteConnectAPI.getMarketMovers());
		
		return homeBean;
	}

}
