package com.trading.dao;

import java.util.List;

import com.trading.pojos.Company;

public interface ICompanyRepository {

	public void saveCompany(Company c);
	
	public void deleteAllCompanies();
	
	public List<Company> getAllCompanies();
	
	public List<Company> getSearchedCompanies(String name);

	public String getCompanyNameById(int id);
	
}
