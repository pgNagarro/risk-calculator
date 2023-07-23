package com.nagarro.riskcalculatorbackend.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nagarro.riskcalculatorbackend.dtos.CompanyDimensionDto;
import com.nagarro.riskcalculatorbackend.models.CompanyDimension;
import com.nagarro.riskcalculatorbackend.models.Dimension;
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
	public void saveCompanyDimension(CompanyDimensionDto companyDimensionDto) throws IOException {
		
		logger.info("start : saveCompanyDimension");
		
		CompanyDimension companyDimension = convertDtoToEntity(companyDimensionDto);
		
		
		List<CompanyDimension> companyDimensions = companyDimensionRepository.findAll();
		
		try {	
		// Condition to check if data is already present or not
		if(!checkDataIfPresent(companyDimension)) {
			
			// find the risk score which is present and then add dimension
			for(CompanyDimension singleCompanyDimension:companyDimensions) {
				
				if(singleCompanyDimension.getCompanyName().equals(companyDimension.getCompanyName())) { 			// check in list of company is present
					
					int flag=-1;
					for(int i=0;i<singleCompanyDimension.getDimensions().size();i++) {  							//check dimension if present or not
						
						if(singleCompanyDimension.getDimensions().get(i).getDimensionName().equals(companyDimension.getDimensions().get(0).getDimensionName())) {
							
							flag = 0;
							throw new IOException("Dimension already present. Enter new dimension or update data");
							
						}
					}
					
					String dname = companyDimension.getDimensions().get(0).getDimensionName();
					int dvalue = companyDimension.getDimensions().get(0).getDimensionValue();
				
					if(flag==-1) {
						
						Dimension dimension =new Dimension(dname,dvalue);
						singleCompanyDimension.getDimensions().add(dimension);
						
						companyDimensionRepository.save(singleCompanyDimension);
						
						addDimensiontoRestCompanies(singleCompanyDimension.getCompanyName(), dimension);
						
						break;
						
					}
				}
			}
		}
		else {
			if(companyDimensions.isEmpty()) {
				
				
				CompanyDimension newCompanyDimension = new CompanyDimension();
				newCompanyDimension.setCompanyName(companyDimension.getCompanyName());
				
				List<Dimension> dimensions = new ArrayList<>();
				
				dimensions.add(companyDimension.getDimensions().get(0));
				
				newCompanyDimension.setDimensions(dimensions);
				
				companyDimensionRepository.save(newCompanyDimension);
			}
			else {
				
				//compare dimension with present dimensions
				CompanyDimension newCompanyDimension = new CompanyDimension();
				newCompanyDimension.setCompanyName(companyDimension.getCompanyName());
				
				List<Dimension> dimensions = new ArrayList<>();
				
				int flag=-1;
				
				for(Dimension dimension:companyDimensions.get(0).getDimensions()) {
					
					if(companyDimension.getDimensions().get(0).getDimensionName().equals(dimension.getDimensionName())) {
						
						dimensions.add(new Dimension(dimension.getDimensionName(),companyDimension.getDimensions().get(0).getDimensionValue()));
						flag=0;
						
					}
					else {
						
						dimensions.add(new Dimension(dimension.getDimensionName(),0));
						
					}	
				}
				if(flag==-1) {
					
					String dname = companyDimension.getDimensions().get(0).getDimensionName();
					int dvalue = companyDimension.getDimensions().get(0).getDimensionValue();
					
					for(CompanyDimension singleCompanyDimension:companyDimensions) {
						
						if(!(singleCompanyDimension.getCompanyName().equals(companyDimension.getCompanyName()))) { // check in list of company is present
							
							Dimension dimension =new Dimension(dname,0);
							singleCompanyDimension.getDimensions().add(dimension);
							
							companyDimensionRepository.save(singleCompanyDimension);	
							
						}
					}
					
					dimensions.add(new Dimension(dname,dvalue));
				}
				
				newCompanyDimension.setDimensions(dimensions);
				companyDimensionRepository.save(newCompanyDimension);		
				
			}
		}
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	private CompanyDimension convertDtoToEntity(CompanyDimensionDto companyDimensionDto) {
		CompanyDimension companyDimension = new CompanyDimension();
		companyDimension.setCompanyName(companyDimensionDto.getCompanyName());
		List<Dimension> list = new ArrayList<>();
		list.add(companyDimensionDto.getDimensions().get(0));
		companyDimension.setDimensions(list);
		return companyDimension;
	}

	@Override
	public CompanyDimensionDto getCompanyDimensionByCompanyName(String companyName) throws IOException {
		
		logger.info("start : getCompanyDimensionByCompanyName");
		
		List<CompanyDimension> companyDimensions = companyDimensionRepository.findByCompanyName(companyName);
		
		if(companyDimensions.isEmpty()) {
			throw new IOException("Company data not present");
		}
		
		CompanyDimension companyDimension = companyDimensions.get(0);
		
		return convertEntityToDto(companyDimension);
		
	}
	
	private CompanyDimensionDto convertEntityToDto(CompanyDimension companyDimension) {
		return new CompanyDimensionDto(companyDimension.getCompanyName(),companyDimension.getDimensions());
		
	}

	@Override
	public CompanyDimensionDto updateCompanyDimension(CompanyDimensionDto companyDimensionDto) {
		
		logger.info("start : updateCompanyDimension");
		
		
		List<CompanyDimension> companyDimensions = companyDimensionRepository.findByCompanyName(companyDimensionDto.getCompanyName());
		
		CompanyDimension companyDimension = companyDimensions.get(0);
		
		companyDimension.setCompanyName(companyDimensionDto.getCompanyName());
		companyDimension.setDimensions(companyDimensionDto.getDimensions());
		System.out.println("hwqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"+companyDimensionDto.getDimensions());
		companyDimensionRepository.save(companyDimension);
		
		return companyDimensionDto;
	}

	@Override
	public void deleteCompanyDimension(CompanyDimensionDto companyDimensionDto) {
		
		logger.info("start : deleteCompanyDimension");
		
		List<CompanyDimension> companyDimensions = companyDimensionRepository.findByCompanyName(companyDimensionDto.getCompanyName());
		
		CompanyDimension companyDimension = companyDimensions.get(0);
		
		companyDimensionRepository.deleteById(companyDimension.getId());
		
		//resultRepository.deleteById(companyRiskScore.getCompanyName());
		
	}

	/**
	 * Method for checking if company dimension data is present or not 
	 */
	@Override
	public boolean checkDataIfPresent(CompanyDimension companyDimension) {
		
		logger.info("start : checkDataIfPresent");
		List<CompanyDimension> companyDimensions = companyDimensionRepository.findByCompanyName(companyDimension.getCompanyName());
		return companyDimensions.isEmpty();
		
	}
	
void addDimensiontoRestCompanies(String companyName,Dimension dimension) {
		
		List<CompanyDimension> riskScoreList = companyDimensionRepository.findAll();
		
		for( CompanyDimension companyDimension : riskScoreList ) {
			
			if(!(companyDimension.getCompanyName().equals(companyName))) {
				
				Dimension newdimension =new Dimension(dimension.getDimensionName(),0);
				companyDimension.getDimensions().add(newdimension);
				companyDimensionRepository.save(companyDimension);
				
			}
			
		}
	}


}
