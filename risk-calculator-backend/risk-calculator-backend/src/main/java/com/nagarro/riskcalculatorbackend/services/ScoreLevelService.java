package com.nagarro.riskcalculatorbackend.services;

import java.io.IOException;
import java.util.List;



import com.nagarro.riskcalculatorbackend.dtos.ScoreLevelDto;
import com.nagarro.riskcalculatorbackend.models.ScoreLevel;

/**
 * Interface for Score Level Service
 * This service provides operations related to ScoreLevel entities.
 * All methods in this interface should be implemented by the concrete class.
 * Implementing classes should handle database operations and business logic.
 * 
 * 
 * @author parasgautam
 */
public interface ScoreLevelService {
	
    /**
     * Retrieves a list of all Risk Score Levels.
     * @return List of ScoreLevel entities representing risk score levels.
     */
    List<ScoreLevel> getAllRiskScoreLevel();
    
    /**
     * Saves a new ScoreLevel entity in the system.
     * @param scoreLevelDto The ScoreLevelDto object containing details of the new entity.
     * @return ScoreLevelDto object representing the saved ScoreLevel entity.
     */
    ScoreLevelDto saveScoreLevel(ScoreLevelDto scoreLevelDto);
    
    /**
     * Retrieves a ScoreLevelDto object based on the given risk score.
     * @param score The risk score for which the ScoreLevelDto needs to be retrieved.
     * @return ScoreLevelDto object representing the matched ScoreLevel entity.
     * @throws IOException if an I/O error occurs during the process.
     */
    ScoreLevelDto getScoreLevelByScore(String score) throws IOException;
    
    /**
     * Updates an existing ScoreLevel entity in the system.
     * @param scoreLevelDto The ScoreLevelDto object containing updated details.
     * @return ScoreLevelDto object representing the updated ScoreLevel entity.
     */
    ScoreLevelDto updateScoreLevel(ScoreLevelDto scoreLevelDto);
    
    /**
     * Deletes a ScoreLevel entity from the system.
     * @param scoreLevelDto The ScoreLevelDto object representing the entity to be deleted.
     */
    void deleteScoreLevel(ScoreLevelDto scoreLevelDto);

}

