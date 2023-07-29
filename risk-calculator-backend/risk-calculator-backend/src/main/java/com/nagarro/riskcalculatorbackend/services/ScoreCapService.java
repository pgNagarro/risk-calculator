package com.nagarro.riskcalculatorbackend.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.riskcalculatorbackend.dtos.ScoreCapDto;
import com.nagarro.riskcalculatorbackend.models.ScoreCap;

/**
 * Interface for Score Cap Service
 * This service provides operations related to ScoreCap entities.
 * All methods in this interface should be implemented by the concrete class.
 * Implementing classes should handle database operations and business logic.
 * 
 *
 * 
 * @author parasgautam
 */
public interface ScoreCapService {
	
    /**
     * Retrieves a list of all Score Caps.
     * @return List of ScoreCap entities representing score caps.
     */
    List<ScoreCap> getAllScoreCap();
    
    /**
     * Saves a new ScoreCap entity in the system.
     * @param scoreCapDto The ScoreCapDto object containing details of the new entity.
     * @return ScoreCapDto object representing the saved ScoreCap entity.
     */
    ScoreCapDto saveScoreCap(ScoreCapDto scoreCapDto);
    
    /**
     * Retrieves a ScoreCapDto object based on the given condition.
     * @param condition The condition for which the ScoreCapDto needs to be retrieved.
     * @return ScoreCapDto object representing the matched ScoreCap entity.
     * @throws IOException if an I/O error occurs during the process.
     */
    ScoreCapDto getScoreCapByCondition(String condition) throws IOException;
    
    /**
     * Updates an existing ScoreCap entity in the system.
     * @param scoreCapDto The ScoreCapDto object containing updated details.
     * @return ScoreCapDto object representing the updated ScoreCap entity.
     */
    ScoreCapDto updateScoreCap(ScoreCapDto scoreCapDto);
    
    /**
     * Deletes a ScoreCap entity from the system.
     * @param scoreCapDto The ScoreCapDto object representing the entity to be deleted.
     */
    void deleteScoreCap(ScoreCapDto scoreCapDto);

}
