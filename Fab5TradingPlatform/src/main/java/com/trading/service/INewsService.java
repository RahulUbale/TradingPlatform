package com.trading.service;

import java.util.List;

import com.trading.pojos.News;

public interface INewsService {

	public void saveNews(List<News> list);
	
	public List<News> getAllNews();
}
