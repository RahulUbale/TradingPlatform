package com.trading.bean;

import java.util.List;

public class Portfolio {
	
	OverallReturn overallReturn;
	PortfolioCost portfolioCost;
	PortfolioValue portfolioValue;
	List<Return> returns;
	public Portfolio() {
		super();
	}
	public Portfolio(OverallReturn overallReturn, PortfolioCost portfolioCost, PortfolioValue portfolioValue,
			List<Return> returns) {
		super();
		this.overallReturn = overallReturn;
		this.portfolioCost = portfolioCost;
		this.portfolioValue = portfolioValue;
		this.returns = returns;
	}
	public OverallReturn getOverallReturn() {
		return overallReturn;
	}
	public void setOverallReturn(OverallReturn overallReturn) {
		this.overallReturn = overallReturn;
	}
	public PortfolioCost getPortfolioCost() {
		return portfolioCost;
	}
	public void setPortfolioCost(PortfolioCost portfolioCost) {
		this.portfolioCost = portfolioCost;
	}
	public PortfolioValue getPortfolioValue() {
		return portfolioValue;
	}
	public void setPortfolioValue(PortfolioValue portfolioValue) {
		this.portfolioValue = portfolioValue;
	}
	public List<Return> getReturns() {
		return returns;
	}
	public void setReturns(List<Return> returns) {
		this.returns = returns;
	}
	
}
