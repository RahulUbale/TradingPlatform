package com.trading.service;

import java.util.HashMap;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trading.bean.Equity;
import com.trading.dao.ICompanyRepository;
import com.trading.pojos.Company;

@Service
@Transactional
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	ICompanyRepository dao;
	
	@Override
	public void loadCompanyData(HashMap<String, Equity> equities){
		dao.deleteAllCompanies();
		Iterator<String> companies = equities.keySet().iterator();
        while(companies.hasNext()) {
        	String company = companies.next();
        	System.out.println("Company : " + company);
        	Equity eq = equities.get(company);
        	String apiUniqueName = eq.getExchange()+":"+eq.getTicker();
        	Company c = new Company(eq.getName(),eq.getTicker(),eq.getInstrumentToken(),eq.getExchangeToken(),
        					eq.getExchange(),eq.getInstrumentType(),apiUniqueName);
        	dao.saveCompany(c);
        }
	}
}
