package com.nagarro.riskcalculatorbackend.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.riskcalculatorbackend.dtos.DimensionWeightDto;
import com.nagarro.riskcalculatorbackend.models.DimensionWeight;

/**
 * Interface for Dimension weight Service
 * @author parasgautam
 *
 */
public interface DimensionWeightService {
	
	List<DimensionWeight> getAllDimensionWeight();
	
	DimensionWeightDto saveDimensionWeight(DimensionWeightDto dimensionWeightDto) throws IOException;
	
	DimensionWeight getDimensionWeightById(String dimension) throws IOException;

	void deleteDimensionWeight(DimensionWeight dimensionWeight);

}
