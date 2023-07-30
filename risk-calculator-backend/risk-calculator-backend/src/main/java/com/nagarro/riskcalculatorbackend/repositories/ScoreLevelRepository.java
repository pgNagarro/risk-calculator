package com.nagarro.riskcalculatorbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.riskcalculatorbackend.models.ScoreLevel;

/**
 * This interface represents the repository for Score Levels.
 * It extends JpaRepository to inherit basic CRUD (Create, Read, Update, Delete) operations.
 * It provides methods to interact with the database and manage ScoreLevel entities.
 * 
 * @author parasgautam
 *
 */
public interface ScoreLevelRepository extends JpaRepository<ScoreLevel, String> {

    /**
     * Retrieves the ScoreLevel entity from the database based on the provided score name.
     * 
     * @param name The name of the score for which the ScoreLevel entity needs to be retrieved.
     * @return The ScoreLevel entity associated with the provided score name, or null if not found.
     */
    public ScoreLevel findByScore(String name);
}
