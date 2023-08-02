package com.nagarro.riskcalculatorbackend.services.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.riskcalculatorbackend.dtos.ScoreCapDto;
import com.nagarro.riskcalculatorbackend.models.ScoreCap;
import com.nagarro.riskcalculatorbackend.repositories.ScoreCapRepository;
import com.nagarro.riskcalculatorbackend.services.ScoreCapService;

import lombok.AllArgsConstructor;

/**
 * Service Implementation Class for Score Cap Service
 * This class provides implementation for managing ScoreCap objects.
 * It interacts with the database through ScoreCapRepository to perform CRUD operations.
 */
@Service
@AllArgsConstructor
public class ScoreCapServiceImpl implements ScoreCapService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreCapServiceImpl.class);

    @Autowired
    private ScoreCapRepository scoreCapRepository;

    /**
     * Method to get all score cap data.
     * @return A list of ScoreCap objects representing all the score caps in the database.
     */
    @Override
    public List<ScoreCap> getAllScoreCap() {
        LOGGER.info("Fetching all ScoreCap data");
        return scoreCapRepository.findAll();
    }

    /**
     * Method to save score cap data.
     * @param scoreCapDto The ScoreCapDto object to be saved.
     * @return The saved ScoreCapDto object.
     */
    @Override
    public ScoreCapDto saveScoreCap(ScoreCapDto scoreCapDto) {
        LOGGER.info("Saving ScoreCap data");
        ScoreCap scoreCap = convertDtoToEntity(scoreCapDto);
        scoreCapRepository.save(scoreCap);
        return scoreCapDto;
    }

    /**
     * Method to get score cap data by condition.
     * @param condition The condition to be used for retrieving the score cap data.
     * @return The ScoreCapDto object representing the score cap data.
     * @throws IOException If an I/O error occurs during the operation.
     */
    @Override
    public ScoreCapDto getScoreCapByCondition(String condition) throws IOException {
        LOGGER.info("Fetching ScoreCap data for condition: {}", condition);
        ScoreCap scoreCaps = scoreCapRepository.findByCondition(condition);
        return convertEntityToDto(scoreCaps);
    }
    
    /**
     * Method to get score cap data by condition.
     * @param condition The condition to be used for retrieving the score cap data.
     * @return The ScoreCap object representing the score cap data.
     * @throws IOException If an I/O error occurs during the operation.
     */
    public ScoreCap getScoreCap(String condition) {
    	return scoreCapRepository.findByCondition(condition);
    }
    

    /**
     * Method to update score cap data.
     * @param scoreCapDto The ScoreCapDto object containing the updated data.
     * @return The updated ScoreCapDto object.
     */
    @Override
    public ScoreCapDto updateScoreCap(ScoreCapDto scoreCapDto) {
        LOGGER.info("Updating ScoreCap data for condition: {}", scoreCapDto.getCondition());
        ScoreCap scoreCap = scoreCapRepository.findByCondition(scoreCapDto.getCondition());
        scoreCap.setTotalRiskCappedScore(scoreCapDto.getTotalRiskCappedScore());
        scoreCapRepository.save(scoreCap);
        return convertEntityToDto(scoreCap);
    }

    /**
     * Method to delete score cap data.
     * @param scoreCapDto The ScoreCapDto object representing the data to be deleted.
     */
    @Override
    public void deleteScoreCap(ScoreCapDto scoreCapDto) {
        LOGGER.info("Deleting ScoreCap data for condition: {}", scoreCapDto.getCondition());
        scoreCapRepository.deleteById(scoreCapDto.getCondition());
    }

    // Helper method to convert ScoreCapDto to ScoreCap entity
    private ScoreCap convertDtoToEntity(ScoreCapDto scoreCapDto) {
        return new ScoreCap(scoreCapDto.getCondition(), scoreCapDto.getTotalRiskCappedScore());
    }

    // Helper method to convert  ScoreCap entity to ScoreCapDto 
    private ScoreCapDto convertEntityToDto(ScoreCap scoreCaps) {
        return new ScoreCapDto(scoreCaps.getCondition(), scoreCaps.getTotalRiskCappedScore());
    }
}
