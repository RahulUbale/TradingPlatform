package com.trading.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="companies")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50)
	private String company;
	
	@Column(length = 50)
	private String ticker;
	
	@Column(name = "instrument_token")
	private long instrumentToken;
	
	@Column(name = "exchange_token")
	private long exchangeToken;
	
	@Column(length = 20)
	private String exchange;
	
	@Column(length = 20)
	private String type;
	
	@Column(length = 50)
	private String apiUniqueName;
	
	public Company() {
		
	}



	public Company(String company, String ticker, long instrumentToken, long exchangeToken, String exchange,
			String type, String apiUniqueName) {
		super();
		this.company = company;
		this.ticker = ticker;
		this.instrumentToken = instrumentToken;
		this.exchangeToken = exchangeToken;
		this.exchange = exchange;
		this.type = type;
		this.apiUniqueName = apiUniqueName;
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

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public long getInstrumentToken() {
		return instrumentToken;
	}

	public void setInstrumentToken(long instrumentToken) {
		this.instrumentToken = instrumentToken;
	}

	public long getExchangeToken() {
		return exchangeToken;
	}

	public void setExchangeToken(long exchangeToken) {
		this.exchangeToken = exchangeToken;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getApiUniqueName() {
		return apiUniqueName;
	}

	public void setApiUniqueName(String apiUniqueName) {
		this.apiUniqueName = apiUniqueName;
	}
	
	
	
}
