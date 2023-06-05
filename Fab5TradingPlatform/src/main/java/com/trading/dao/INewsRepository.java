package com.trading.dao;

import java.util.List;

import com.trading.pojos.News;

public interface INewsRepository {

	public void saveNews(News news);
	
	public List<News> getAllNews();
	
	public void deleteAllNews();
}
