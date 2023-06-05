package com.trading.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	private Integer id;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	List<Order> orders = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Position> positions = new ArrayList<>();

	public User() {
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public void addBuyOrder(Order order) {
		orders.add(order);
		Position position = new Position(order.getCompany(), order.getApiUniqueName(), order.getQuantity(),
				order.getPrice(), order.getUser());
		this.addPosition(position);
		order.setUser(this);
		position.setUser(this);
	}
	public void addSellOrder(Order order) {
		orders.add(order);
		order.setUser(this);
	}
	
	
	public void addPosition(Position position) {
		positions.add(position);
		position.setUser(this);
	}

	public void deletePosition(Position position) {
		this.positions.remove(position);
		position.setUser(null);
	}

	public void updateSharesQuantity(int updatedQuantity, Position position) {
		int index = this.positions.indexOf(position);
		position.setQuantity(updatedQuantity);
		this.positions.set(index, position);
	}

}
