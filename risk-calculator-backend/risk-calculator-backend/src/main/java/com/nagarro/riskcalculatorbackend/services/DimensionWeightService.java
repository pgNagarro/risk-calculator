package com.nagarro.riskcalculatorbackend.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.riskcalculatorbackend.dtos.DimensionWeightDto;
import com.nagarro.riskcalculatorbackend.models.DimensionWeight;

/**
 * Interface for Dimension Weight Service
 * This service provides operations related to DimensionWeight entities.
 * All methods in this interface should be implemented by the concrete class.
 * Implementing classes should handle database operations and business logic.
 * 
 * 
 * 
 * @author parasgautam
 */
public interface DimensionWeightService {
	
    /**
     * Retrieves a list of all Dimension Weights.
     * @return List of DimensionWeight entities representing dimension weights.
     */
    List<DimensionWeight> getAllDimensionWeight();
    
    /**
     * Saves a new DimensionWeight entity in the system.
     * @param dimensionWeightDto The DimensionWeightDto object containing details of the new entity.
     * @return DimensionWeightDto object representing the saved DimensionWeight entity.
     * @throws IOException if an I/O error occurs during the process.
     */
    DimensionWeightDto saveDimensionWeight(DimensionWeightDto dimensionWeightDto) throws IOException;

}
