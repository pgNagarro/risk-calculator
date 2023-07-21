package com.nagarro.riskcalculatorbackend.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nagarro.riskcalculatorbackend.models.CompanyDimension;
import com.nagarro.riskcalculatorbackend.repositories.CompanyDimensionRepository;
import com.nagarro.riskcalculatorbackend.services.CompanyDimensionService;

/**
 * Service Implementation Class for Company Dimension Service
 * @author parasgautam
 *
 */
@Service
public class CompanyDimensionServiceImpl implements CompanyDimensionService{
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyDimensionServiceImpl.class);
	
	@Autowired
	private CompanyDimensionRepository companyDimensionRepository;

	/**
	 *  Method for getting all company dimension data
	 */
	@Override
	public List<CompanyDimension> getAllCompanyDimension() {
		
		logger.info("start : getAllCompanyDimension");
		
		return companyDimensionRepository.findAll();
		
	}

	@Override
	public void saveCompanyDimension(CompanyDimension companyDimension) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CompanyDimension getCompanyDimensionByCompanyName(String companyName) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompanyDimension updateCompanyDimension(CompanyDimension companyDimension) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCompanyDimension(CompanyDimension companyDimension) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Method for checking if company dimension data is present or not 
	 */
	@Override
	public boolean checkDataIfPresent(CompanyDimension companyDimension) {
		// TODO Auto-generated method stub
		return false;
	}


}
