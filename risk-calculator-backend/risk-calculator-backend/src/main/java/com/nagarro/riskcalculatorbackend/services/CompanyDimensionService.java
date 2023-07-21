package com.nagarro.riskcalculatorbackend.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.riskcalculatorbackend.models.CompanyDimension;

/**
 * Interface for Company Dimension Service
 * @author parasgautam
 *
 */
public interface CompanyDimensionService {

	List<CompanyDimension> getAllCompanyDimension();
	
	void saveCompanyDimension(CompanyDimension companyDimension) throws IOException;
	
	CompanyDimension getCompanyDimensionByCompanyName(String companyName) throws IOException;
	
	CompanyDimension updateCompanyDimension(CompanyDimension companyDimension);
	
	void deleteCompanyDimension(CompanyDimension companyDimension);
	
	boolean checkDataIfPresent(CompanyDimension companyDimension);
}
