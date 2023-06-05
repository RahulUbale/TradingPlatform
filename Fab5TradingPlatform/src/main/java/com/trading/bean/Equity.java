package com.trading.bean;

public class Equity {

	private long instrumentToken;
	private long exchangeToken;
	private String ticker;
	private String name;
	private String exchange;
	private String instrumentType;
	private String apiUniqueName;
	
	public Equity() {
		// TODO Auto-generated constructor stub
	}

	

	public Equity(long instrumentToken, long exchangeToken, String ticker, String name, String exchange,
			String instrumentType, String apiUniqueName) {
		super();
		this.instrumentToken = instrumentToken;
		this.exchangeToken = exchangeToken;
		this.ticker = ticker;
		this.name = name;
		this.exchange = exchange;
		this.instrumentType = instrumentType;
		this.apiUniqueName = apiUniqueName;
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

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}


	public String getInstrumentType() {
		return instrumentType;
	}


	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}



	public String getApiUniqueName() {
		return apiUniqueName;
	}



	public void setApiUniqueName(String apiUniqueName) {
		this.apiUniqueName = apiUniqueName;
	}



	@Override
	public String toString() {
		return "Equity [instrumentToken=" + instrumentToken + ", exchangeToken=" + exchangeToken + ", ticker=" + ticker
				+ ", name=" + name + ", exchange=" + exchange + ", instrumentType=" + instrumentType
				+ ", apiUniqueName=" + apiUniqueName + "]";
	}
	
	

}
