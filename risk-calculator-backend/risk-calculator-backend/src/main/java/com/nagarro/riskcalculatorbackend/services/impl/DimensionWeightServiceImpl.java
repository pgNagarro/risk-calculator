package com.nagarro.riskcalculatorbackend.services.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.riskcalculatorbackend.dtos.DimensionWeightDto;
import com.nagarro.riskcalculatorbackend.models.DimensionWeight;
import com.nagarro.riskcalculatorbackend.repositories.DimensionWeightRepository;
import com.nagarro.riskcalculatorbackend.services.DimensionWeightService;


/**
 * Service Implementation Class for Dimension Weight Service
 * @author parasgautam
 *
 */
@Service
public class DimensionWeightServiceImpl implements DimensionWeightService{
	
	private static final Logger logger = LoggerFactory.getLogger(DimensionWeightServiceImpl.class);

	@Autowired
	private DimensionWeightRepository dimensionWeightRepository;

	@Override
	public List<DimensionWeight> getAllDimensionWeight() {
		
		logger.info("start : getAllDimensionWeight");
		
		return dimensionWeightRepository.findAll();
		
	}

	@Override
	public DimensionWeightDto saveDimensionWeight(DimensionWeightDto dimensionWeightDto) throws IOException {
		
		logger.info("start : saveDimensionWeight"); 
				
		try {
	        // Begin a transaction here if necessary
	        dimensionWeightRepository.deleteAll();
	        
	        for(int i=0;i<dimensionWeightDto.getDimensionWeights().size();i++) {
	        	dimensionWeightRepository.save(dimensionWeightDto.getDimensionWeights().get(i));
	        }

	        // Commit the transaction if applicable
	    } catch (Exception e) {
	        // Rollback the transaction if applicable
	        logger.error("Error while saving DimensionWeight: " + e.getMessage());
	        throw new IOException("Failed to save DimensionWeight. Please check logs for details.");
	    }
		
		return dimensionWeightDto;
		
	}

}
