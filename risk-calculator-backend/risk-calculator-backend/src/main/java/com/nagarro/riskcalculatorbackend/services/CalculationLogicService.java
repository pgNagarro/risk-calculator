package com.nagarro.riskcalculatorbackend.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.riskcalculatorbackend.dtos.CalculationLogicDto;
import com.nagarro.riskcalculatorbackend.models.CalculationLogic;

/**
 * Interface for Risk Calculation Logic Service
 * This service provides operations related to CalculationLogic entities.
 * All methods in this interface should be implemented by the concrete class.
 * Implementing classes should handle database operations and business logic.
 * 
 * 
 * @author parasgautam
 */
public interface CalculationLogicService {

    /**
     * Retrieves a list of all Calculation Logic entries.
     * @return List of CalculationLogic entities representing the calculation logic.
     */
    List<CalculationLogic> getAllCalculationLogic();

    /**
     * Retrieves a CalculationLogicDto object based on the given name.
     * @param name The name of the calculation logic to be retrieved.
     * @return CalculationLogicDto object representing the matched CalculationLogic entity.
     * @throws IOException if an I/O error occurs during the process.
     */
    CalculationLogicDto getCalculationLogicByName(String name) throws IOException;

    /**
     * Saves a new CalculationLogic entity in the system.
     * @param calculationLogicDto The CalculationLogicDto object containing details of the new entity.
     * @return CalculationLogicDto object representing the saved CalculationLogic entity.
     */
    CalculationLogicDto saveCalculationLogic(CalculationLogicDto calculationLogicDto);

    /**
     * Updates an existing CalculationLogic entity in the system.
     * @param calculationLogicDto The CalculationLogicDto object containing updated details.
     * @return CalculationLogicDto object representing the updated CalculationLogic entity.
     */
    CalculationLogicDto updateCalculationLogic(CalculationLogicDto calculationLogicDto);

    /**
     * Deletes a CalculationLogic entity from the system.
     * @param calculationLogicDto The CalculationLogicDto object representing the entity to be deleted.
     */
    void deleteCalculationLogic(CalculationLogicDto calculationLogicDto);
}
