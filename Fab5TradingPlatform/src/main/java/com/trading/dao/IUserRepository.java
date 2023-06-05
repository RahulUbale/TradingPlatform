package com.trading.dao;

import com.trading.pojos.User;

public interface IUserRepository {

	public String saveUser(User u);

	public User validateUser(String email);
	
	public User getUserById(int userId);

	public String buy(User user);
	
	public void updateUser(User user);

}
