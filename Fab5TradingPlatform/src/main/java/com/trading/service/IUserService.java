package com.trading.service;

import java.util.List;

import com.trading.bean.UserProxy;
import com.trading.pojos.Order;
import com.trading.pojos.User;

public interface IUserService {
	
	public User saveUser(UserProxy u);
	
	public User validateUser(String email);
	
	public User getUserById(int userId);
	
	public User getUserByToken(String gToken);
	
	public String buy(User user);
	
	public String sell(User user,int quantity,String apiUniqueName,Order order);
	
	public List<Order> getOrdersBuyId(int userId);
	
}
