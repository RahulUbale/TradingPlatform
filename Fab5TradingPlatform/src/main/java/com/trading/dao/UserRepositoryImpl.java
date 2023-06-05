package com.trading.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trading.pojos.User;

@Repository
public class UserRepositoryImpl implements IUserRepository {

	@Autowired
	private EntityManager mgr;

	@Override
	public String saveUser(User u) {
		String message = "Signup completed successfully";
		mgr.persist(u);
		return message;
	}

	@Override
	public User validateUser(String email) {
		String jpql = "select u from User u where u.email=:em";
		return mgr.createQuery(jpql, User.class).setParameter("em", email).getSingleResult();
	}

	@Override
	public String buy(User user) {
		String message = "Order was successfully added";
		mgr.persist(user);
		return message;
	}

	@Override
	public User getUserById(int userId) {
		String jpql = "select u from User u left join fetch u.orders where u.id=:userId";
		User user = mgr.createQuery(jpql, User.class).setParameter("userId", userId).getSingleResult();
		user.getPositions().size();
		System.out.println(user.getPositions().size());
		return user;
	}

	@Override
	public void updateUser(User user) {
		System.out.println("in sell repo");
		System.out.println("User Id " + user.getId()+" - "+user.getPositions());
		mgr.merge(user);		
	}
	
	
}
