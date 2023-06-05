package com.trading.service;

import static java.util.Objects.isNull;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trading.api.UserMicroserviceAPI;
import com.trading.bean.UserProxy;
import com.trading.dao.IUserRepository;
import com.trading.pojos.Order;
import com.trading.pojos.Position;
import com.trading.pojos.User;
@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepository repo;

	public UserServiceImpl() {
		System.out.println("In default constructor : " + getClass().getName());
	}

	@Override
	public User saveUser(UserProxy u) {
		UserMicroserviceAPI api = new UserMicroserviceAPI();
		u = api.addUser(u);
		if(u!=null) {
			int id = u.getId();
			User user = new User();
			user.setId(id);
			repo.saveUser(user);
			return user;
		}else
			return null;
		
	}

	@Override
	public User validateUser(String email) {
		return repo.validateUser(email);
	}

	@Override
	public User getUserById(int userId) {
		return repo.getUserById(userId);
	}
	
	@Override
	public User getUserByToken(String gToken) {
		UserMicroserviceAPI api = new UserMicroserviceAPI();
		UserProxy u = api.authorizeUser(gToken);
		
		if(isNull(u))
			return null;
		User user = null;
		try {
			user = repo.getUserById(u.getId());
		}catch (Exception e) {
			User newUser = new  User();
			newUser.setId(u.getId());
			repo.saveUser(newUser);
			return newUser;
		}
		
		if(isNull(user)) {
			User newUser = new  User();
			newUser.setId(u.getId());
			repo.saveUser(newUser);
			return newUser;
		}
		
		return user;
	}

	@Override
	public String buy(User user) {

		return repo.buy(user);
	}

	@Override
	public String sell(User user, int quantity, String apiUniqueName, Order order) {
		System.out.println("in sellSerice");
		String message = "";

		List<Position> positions = user.getPositions();
		int total = 0;
		for (Position p : positions) {
			if (p.getApiUniqueName().equals(apiUniqueName)) {
				total += p.getQuantity();

			}
		}
		if (total < quantity) {
			return null;
		}

		int count = quantity;
		int size = positions.size();
		for (int i=0;i<size;i++) {
			Position p = positions.get(i);
			if (p.getApiUniqueName().equals(apiUniqueName)) {
				
				int qty = p.getQuantity();

				int empty = count - qty;
				if (count > qty || empty == 0) {
					System.out.println("in deletePsition");
					user.deletePosition(p);
					size--;
					i--;
				}
				count = count - qty;
				if (count == 0) {
					message = "Shares Sold";
					user.addSellOrder(order);
					repo.updateUser(user);

					return message;
				}
				if (count < 0) {
					System.out.println("in updatequantityMethod");
					qty = count * -1;
					user.updateSharesQuantity(qty, p);
					user.addSellOrder(order);
					repo.updateUser(user);

					return message;
				}
			}
		}
		return message;
	}

	@Override
	public List<Order> getOrdersBuyId(int userId) {
		User user = this.getUserById(userId);
		List<Order> orders = user.getOrders();
		return orders;
	}

}
