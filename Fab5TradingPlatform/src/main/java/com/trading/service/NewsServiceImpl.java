package com.trading.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trading.dao.INewsRepository;
import com.trading.pojos.News;

@Service
@Transactional
public class NewsServiceImpl implements INewsService {

	@Autowired
	INewsRepository dao;
	
	@Override
	public void saveNews(List<News> list) {
		dao.deleteAllNews();
		for (News news : list) {
			dao.saveNews(news);
		}
	}

	@Override
	public List<News> getAllNews() {
		return dao.getAllNews();
	}

}
