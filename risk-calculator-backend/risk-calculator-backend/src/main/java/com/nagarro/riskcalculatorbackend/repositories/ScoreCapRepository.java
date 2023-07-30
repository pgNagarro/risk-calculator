package com.nagarro.riskcalculatorbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.riskcalculatorbackend.models.ScoreCap;

/**
 * This interface represents the repository for Score Caps.
 * It extends JpaRepository to inherit basic CRUD (Create, Read, Update, Delete) operations.
 * It provides methods to interact with the database and manage ScoreCap entities.
 * 
 * @author parasgautam
 *
 */
public interface ScoreCapRepository extends JpaRepository<ScoreCap, String> {

    /**
     * Retrieves the ScoreCap entity from the database based on the provided condition.
     * 
     * @param condition The condition for which the ScoreCap entity needs to be retrieved.
     * @return The ScoreCap entity associated with the provided condition, or null if not found.
     */
    public ScoreCap findByCondition(String condition);
}
