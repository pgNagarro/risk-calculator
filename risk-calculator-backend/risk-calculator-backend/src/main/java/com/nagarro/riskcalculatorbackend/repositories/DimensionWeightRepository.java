package com.nagarro.riskcalculatorbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.riskcalculatorbackend.models.DimensionWeight;

/**
 * This interface represents the repository for Dimension Weights.
 * It extends JpaRepository to inherit basic CRUD (Create, Read, Update, Delete) operations.
 * It provides methods to interact with the database and manage DimensionWeight entities.
 * 
 * @author parasgautam
 *
 */
public interface DimensionWeightRepository extends JpaRepository<DimensionWeight, String> {

    /**
     * Retrieves the DimensionWeight entity from the database based on the provided dimension name.
     * 
     * @param name The name of the dimension for which the DimensionWeight entity needs to be retrieved.
     * @return The DimensionWeight entity associated with the provided dimension name, or null if not found.
     */
    public DimensionWeight findByDimension(String name);
}
