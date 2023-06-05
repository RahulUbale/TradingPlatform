package com.trading.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trading.pojos.News;

@Repository
public class NewsRepositoryImpl implements INewsRepository {

	@Autowired
	private EntityManager mgr;
	
	@Override
	public void saveNews(News news) {
		mgr.persist(news);
	}

	@Override
	public List<News> getAllNews() {
		String jpql = "Select n from News n";
		return mgr.createQuery(jpql,News.class).getResultList();
	}
	
	@Override
	public void deleteAllNews() {
		String jpql = "delete from News n";
		mgr.createQuery(jpql).executeUpdate();
	}

}
