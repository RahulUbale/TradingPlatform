package com.trading.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "positions")
public class Position {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100)
	private String company;
	
	@Column(length = 100,name="api_unique_name")
	private String apiUniqueName;
	
	private int quantity;
	

	private double price;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	public Position() {
		// TODO Auto-generated constructor stub
	}
	

	public Position(String company, String apiUniqueName, int quantity, double price, User user) {
		super();

		this.company = company;
		this.apiUniqueName = apiUniqueName;
		this.quantity = quantity;
		this.price = price;
		this.user = user;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getApiUniqueName() {
		return apiUniqueName;
	}

	public void setApiUniqueName(String apiUniqueName) {
		this.apiUniqueName = apiUniqueName;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Position [id=" + id + ", company=" + company + ", apiUniqueName=" + apiUniqueName + ", quantity="
				+ quantity + ", price=" + price + ", user=" + user + "]";
	}

}
