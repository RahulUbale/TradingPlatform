package com.trading.bean;

public class Depth {

    private int quantity;
    private double price;
    private int orders;
	
    public Depth() {
		// TODO Auto-generated constructor stub
	}

	public Depth(int quantity, double price, int orders) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.orders = orders;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}
    
    
}
