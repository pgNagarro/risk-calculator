package com.nagarro.riskcalculatorbackend.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.riskcalculatorbackend.dtos.CompanyDimensionDto;
import com.nagarro.riskcalculatorbackend.models.CompanyDimension;

/**
 * Interface for Company Dimension Service
 * @author parasgautam
 *
 */
public interface CompanyDimensionService {

	List<CompanyDimension> getAllCompanyDimension();
	
	void saveCompanyDimension(CompanyDimensionDto companyDimensionDto) throws IOException;
	
	CompanyDimensionDto getCompanyDimensionByCompanyName(String companyName) throws IOException;
	
	CompanyDimensionDto updateCompanyDimension(CompanyDimensionDto companyDimensionDto);
	
	void deleteCompanyDimension(CompanyDimensionDto companyDimensionDto);
	
	boolean checkDataIfPresent(CompanyDimension companyDimension);
}
