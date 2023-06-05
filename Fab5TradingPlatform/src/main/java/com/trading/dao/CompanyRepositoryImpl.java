package com.trading.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trading.pojos.Company;

@Repository
public class CompanyRepositoryImpl implements ICompanyRepository {

	@Autowired
	private EntityManager mgr;
	
	@Override
	public void saveCompany(Company c) {
		mgr.persist(c);
	}

	@Override
	public void deleteAllCompanies() {
		String jpql = "delete from Company c";
		mgr.createQuery(jpql).executeUpdate();
	}

	@Override
	public List<Company> getAllCompanies() {
		String jpql = "Select c from Company c";
		List<Company> companies = mgr.createQuery(jpql, Company.class).getResultList();
		return companies;
	}
	
	

	@Override
	public List<Company> getSearchedCompanies(String name) {
		String jpql = "Select c from Company c where c.company like:pattern";
		String pattern = name+"%";
		List<Company> companies = mgr.createQuery(jpql, Company.class).setParameter("pattern", pattern).getResultList();
		return companies;
	}

	@Override
	public String getCompanyNameById(int id) {
		String jpql = "Select c from Company c where c.id=:id";
		Company company = mgr.createQuery(jpql, Company.class).setParameter("id", id).getSingleResult();
		String apiUniqueName = company.getApiUniqueName();
		return apiUniqueName;
	}


}
