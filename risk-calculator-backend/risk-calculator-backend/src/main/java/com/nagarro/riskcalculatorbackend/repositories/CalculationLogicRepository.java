package com.nagarro.riskcalculatorbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.riskcalculatorbackend.models.CalculationLogic;

/**
 * This interface represents the repository for Calculation Logic entities.
 * It extends JpaRepository to inherit basic CRUD (Create, Read, Update, Delete) operations.
 * It provides methods to interact with the database and manage CalculationLogic entities.
 * 
 * The CalculationLogic entities store the logic associated with different elements used in risk calculation.
 * Each element has a unique name, and this repository provides methods to find CalculationLogic entities
 * based on their element names.
 * 
 * @author parasgautam
 *
 */
public interface CalculationLogicRepository extends JpaRepository<CalculationLogic, String> {

    /**
     * Retrieves the CalculationLogic entity from the database based on the provided element name.
     * 
     * @param name The name of the element for which the CalculationLogic entity needs to be retrieved.
     * @return The CalculationLogic entity associated with the provided element name, or null if not found.
     */
    public CalculationLogic findByElementName(String name);
}
