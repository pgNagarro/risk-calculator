package com.nagarro.riskcalculatorbackend.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.riskcalculatorbackend.dtos.CompanyDimensionDto;
import com.nagarro.riskcalculatorbackend.models.CompanyDimension;

/**
 * Interface for Company Dimension Service
 * This service provides operations related to CompanyDimension entities.
 * All methods in this interface should be implemented by the concrete class.
 * Implementing classes should handle database operations and business logic.
 * 
 * 
 * @author parasgautam
 */
public interface CompanyDimensionService {

    /**
     * Retrieves a list of all Company Dimensions.
     * @return List of CompanyDimension entities representing company dimensions.
     */
    List<CompanyDimension> getAllCompanyDimension();

    /**
     * Saves a new CompanyDimension entity in the system.
     * @param companyDimensionDto The CompanyDimensionDto object containing details of the new entity.
     * @throws IOException if an I/O error occurs during the process.
     */
    void saveCompanyDimension(CompanyDimensionDto companyDimensionDto) throws IOException;

    /**
     * Retrieves a CompanyDimensionDto object based on the given company name.
     * @param companyName The company name for which the CompanyDimensionDto needs to be retrieved.
     * @return CompanyDimensionDto object representing the matched CompanyDimension entity.
     * @throws IOException if an I/O error occurs during the process.
     */
    CompanyDimensionDto getCompanyDimensionByCompanyName(String companyName) throws IOException;

    /**
     * Updates an existing CompanyDimension entity in the system.
     * @param companyDimensionDto The CompanyDimensionDto object containing updated details.
     * @return CompanyDimensionDto object representing the updated CompanyDimension entity.
     */
    CompanyDimensionDto updateCompanyDimension(CompanyDimensionDto companyDimensionDto);

    /**
     * Deletes a CompanyDimension entity from the system.
     * @param companyDimensionDto The CompanyDimensionDto object representing the entity to be deleted.
     */
    void deleteCompanyDimension(CompanyDimensionDto companyDimensionDto);

    /**
     * Checks if the given CompanyDimension entity data is present in the system.
     * @param companyDimension The CompanyDimension object to be checked for presence.
     * @return true if the data is present, false otherwise.
     */
    boolean checkDataIfPresent(CompanyDimension companyDimension);
}
