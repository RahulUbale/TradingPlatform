package com.trading.bean;

import java.util.Date;

public class OrderQuote {
	private double volumeTradedToday;
	private double lastTradedQuantity;
	private Date lastTradedTime;
	private double change;
	private double oi;
	private double sellQuantity;
	private double lastPrice;
	private double buyQuantity;
	private Ohlc ohlc;
	private long instrumentToken;
	private Date timestamp;
	private double averagePrice;
	private double oiDayHigh;
	private double oiDayLow;
	private MarketDepth depth;
	private double lowerCircuitLimit;
	private double upperCircuitLimit;
	private Equity company;
	
	public OrderQuote() {
		
	}

	public double getVolumeTradedToday() {
		return volumeTradedToday;
	}

	public void setVolumeTradedToday(double volumeTradedToday) {
		this.volumeTradedToday = volumeTradedToday;
	}

	public double getLastTradedQuantity() {
		return lastTradedQuantity;
	}

	public void setLastTradedQuantity(double lastTradedQuantity) {
		this.lastTradedQuantity = lastTradedQuantity;
	}

	public Date getLastTradedTime() {
		return lastTradedTime;
	}

	public void setLastTradedTime(Date lastTradedTime) {
		this.lastTradedTime = lastTradedTime;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public double getOi() {
		return oi;
	}

	public void setOi(double oi) {
		this.oi = oi;
	}

	public double getSellQuantity() {
		return sellQuantity;
	}

	public void setSellQuantity(double sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public double getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(double buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public Ohlc getOhlc() {
		return ohlc;
	}

	public void setOhlc(Ohlc ohlc) {
		this.ohlc = ohlc;
	}

	public long getInstrumentToken() {
		return instrumentToken;
	}

	public void setInstrumentToken(long instrumentToken) {
		this.instrumentToken = instrumentToken;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public double getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(double averagePrice) {
		this.averagePrice = averagePrice;
	}

	public double getOiDayHigh() {
		return oiDayHigh;
	}

	public void setOiDayHigh(double oiDayHigh) {
		this.oiDayHigh = oiDayHigh;
	}

	public double getOiDayLow() {
		return oiDayLow;
	}

	public void setOiDayLow(double oiDayLow) {
		this.oiDayLow = oiDayLow;
	}

	public MarketDepth getDepth() {
		return depth;
	}

	public void setDepth(MarketDepth depth) {
		this.depth = depth;
	}

	public double getLowerCircuitLimit() {
		return lowerCircuitLimit;
	}

	public void setLowerCircuitLimit(double lowerCircuitLimit) {
		this.lowerCircuitLimit = lowerCircuitLimit;
	}

	public double getUpperCircuitLimit() {
		return upperCircuitLimit;
	}

	public void setUpperCircuitLimit(double upperCircuitLimit) {
		this.upperCircuitLimit = upperCircuitLimit;
	}

	
	
	public Equity getCompany() {
		return company;
	}

	public void setCompany(Equity company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "OrderQuote [volumeTradedToday=" + volumeTradedToday + ", lastTradedQuantity=" + lastTradedQuantity
				+ ", lastTradedTime=" + lastTradedTime + ", change=" + change + ", oi=" + oi + ", sellQuantity="
				+ sellQuantity + ", lastPrice=" + lastPrice + ", buyQuantity=" + buyQuantity + ", ohlc=" + ohlc
				+ ", instrumentToken=" + instrumentToken + ", timestamp=" + timestamp + ", averagePrice=" + averagePrice
				+ ", oiDayHigh=" + oiDayHigh + ", oiDayLow=" + oiDayLow + ", depth=" + depth + ", lowerCircuitLimit="
				+ lowerCircuitLimit + ", upperCircuitLimit=" + upperCircuitLimit + ", company=" + company + "]";
	}


	
}
