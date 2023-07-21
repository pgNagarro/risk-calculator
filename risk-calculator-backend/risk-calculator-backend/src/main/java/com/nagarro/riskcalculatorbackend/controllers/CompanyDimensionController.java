package com.nagarro.riskcalculatorbackend.controllers;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.calculator.models.CompanyRiskScore;
import com.nagarro.riskcalculatorbackend.dtos.CompanyDimensionDto;
import com.nagarro.riskcalculatorbackend.models.CompanyDimension;
import com.nagarro.riskcalculatorbackend.services.CompanyDimensionService;




/**
 * Controller class for Company Dimension to handle operations
 * on company dimension data
 * 
 * @author parasgautam
 * 
 */
@RestController
@CrossOrigin(origins="*")
public class CompanyDimensionController {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyDimensionController.class);
	
	@Autowired
	private CompanyDimensionService companyDimensionService;
	
	
	/**
	 * Method to get all data from company dimension table
	 * @return List<CompanyDimension>
	 */
	@GetMapping("/company-dimension")
	public ResponseEntity<List<CompanyDimension>> getCompanyDimension() {
		
		logger.info("Request received for fetching company dimensions");
		
		List<CompanyDimension> companyDimensionList = companyDimensionService.getAllCompanyDimension();
		
		logger.info("Request completed for fetching company dimensions");
		
		return ResponseEntity.ok(companyDimensionList);
		
	}
	
	/**
	 * Method for adding company dimension data
	 * @param companyDimension
	 * @return ResponseEntity<CompanyDimension>
	 * @throws IOException 
	 */
	@PostMapping("/addCompanyDimension")	
	public ResponseEntity<CompanyDimension> saveCompanyDimension(@RequestBody CompanyDimension companyDimension) throws IOException {
		
		logger.info("Request received for adding company dimension");
		
		if(!companyDimensionService.checkDataIfPresent(companyDimension)) {
			logger.info("Company name already present");
		}
		
		 companyDimensionService.saveCompanyDimension(companyRiskScore);
		 
		 logger.info("Request completed for adding company dimension");
		 
		 return ResponseEntity.ok(companyRiskScore);
	}

}
