package com.trading.bean;

public class QuoteBean {
	private OrderQuote orderquote;
	private String apiUniqueName;
	public QuoteBean() {

	}
	public QuoteBean(OrderQuote quote, String apiUniqueName) {
		super();
		this.orderquote = quote;
		this.apiUniqueName = apiUniqueName;
	}
	public OrderQuote getOrderquote() {
		return orderquote;
	}
	public void setOrderquote(OrderQuote quote) {
		this.orderquote = quote;
	}
	public String getApiUniqueName() {
		return apiUniqueName;
	}
	public void setApiUniqueName(String apiUniqueName) {
		this.apiUniqueName = apiUniqueName;
	}

	
}
