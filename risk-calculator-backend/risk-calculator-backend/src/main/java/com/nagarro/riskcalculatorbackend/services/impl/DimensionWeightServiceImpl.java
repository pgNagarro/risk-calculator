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
		
		DimensionWeight dimensionWeight = convertDtoToEntity(dimensionWeightDto);
	
		dimensionWeightRepository.save(dimensionWeight);
		
		return dimensionWeightDto;
		
	}
	
	

	@Override
	public DimensionWeight getDimensionWeightById(String dimension) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDimensionWeight(DimensionWeight dimensionWeight) {
		// TODO Auto-generated method stub
		
	}

	private DimensionWeight convertDtoToEntity(DimensionWeightDto dimensionWeightDto) {
		return new DimensionWeight(dimensionWeightDto.getDimension(),dimensionWeightDto.getWeight());
	}
	

}
