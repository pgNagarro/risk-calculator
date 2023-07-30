package com.nagarro.riskcalculatorbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.riskcalculatorbackend.models.CompanyDimension;

/**
 * This interface represents the repository for Company Dimensions.
 * It extends JpaRepository to inherit basic CRUD (Create, Read, Update, Delete) operations.
 * It provides methods to interact with the database and manage CompanyDimension entities.
 * 
 * @author parasgautam
 *
 */
public interface CompanyDimensionRepository extends JpaRepository<CompanyDimension, Integer> {

    /**
     * Retrieves a list of CompanyDimension entities from the database based on the provided company name.
     * 
     * @param name The name of the company for which the CompanyDimension entities need to be retrieved.
     * @return A list of CompanyDimension entities associated with the provided company name.
     */
    public List<CompanyDimension> findByCompanyName(String name);
	
    /**
     * Deletes CompanyDimension entities from the database based on the provided company name.
     * 
     * @param name The name of the company for which the CompanyDimension entities need to be deleted.
     */
    public void deleteByCompanyName(String name);

}
