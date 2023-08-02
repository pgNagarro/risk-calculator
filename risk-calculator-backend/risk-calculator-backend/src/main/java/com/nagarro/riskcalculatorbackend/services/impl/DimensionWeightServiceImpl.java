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
 * Service Implementation Class for Dimension Weight Service.
 * This class provides implementations for the methods defined in DimensionWeightService.
 * It interacts with the repository to perform CRUD operations on DimensionWeight entities.
 * The service layer handles business logic and error handling for DimensionWeight operations.
 * 
 * @author parasgautam
 *
 */
@Service
public class DimensionWeightServiceImpl implements DimensionWeightService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DimensionWeightServiceImpl.class);

    @Autowired
    private DimensionWeightRepository dimensionWeightRepository;

    /**
     * Retrieves a list of all DimensionWeight entities from the database.
     * 
     * @return List of DimensionWeight entities.
     */
    @Override
    public List<DimensionWeight> getAllDimensionWeight() {
        LOGGER.info("Getting all DimensionWeights from the database.");
        return dimensionWeightRepository.findAll();
    }

    /**
     * Saves DimensionWeight entities to the database.
     * 
     * @param dimensionWeightDto The DimensionWeightDto containing the list of DimensionWeight entities to be saved.
     * @return The saved DimensionWeightDto.
     * @throws IOException If there is an error during the save operation.
     */
    @Override
    public DimensionWeightDto saveDimensionWeight(DimensionWeightDto dimensionWeightDto) throws IOException {
        LOGGER.info("Saving DimensionWeight entities to the database.");
        try {
            // Begin a transaction here if necessary
            dimensionWeightRepository.deleteAll();

            for (int i = 0; i < dimensionWeightDto.getDimensionWeights().size(); i++) {
                dimensionWeightRepository.save(dimensionWeightDto.getDimensionWeights().get(i));
            }

            // Commit the transaction if applicable
        } catch (Exception e) {
            // Rollback the transaction if applicable
            LOGGER.error("Error while saving DimensionWeight: " + e.getMessage());
            throw new IOException("Failed to save DimensionWeight. Please check logs for details.");
        }

        return dimensionWeightDto;
    }
}
