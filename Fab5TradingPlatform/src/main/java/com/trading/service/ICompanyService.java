package com.trading.service;

import java.util.HashMap;

import com.trading.bean.Equity;

public interface ICompanyService {
	
	public void loadCompanyData(HashMap<String, Equity> equities);

}
