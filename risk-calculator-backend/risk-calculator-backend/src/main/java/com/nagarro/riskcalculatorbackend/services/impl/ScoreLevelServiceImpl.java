package com.nagarro.riskcalculatorbackend.services.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.riskcalculatorbackend.dtos.ScoreLevelDto;
import com.nagarro.riskcalculatorbackend.models.ScoreLevel;
import com.nagarro.riskcalculatorbackend.repositories.ScoreLevelRepository;
import com.nagarro.riskcalculatorbackend.services.ScoreLevelService;


/**
 * Service Implementation Class for Score Level Service.
 * This class provides methods to interact with the ScoreLevel data.
 * Implements the ScoreLevelService interface.
 * 
 * @author parasgautam
 */
@Service
public class ScoreLevelServiceImpl implements ScoreLevelService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ScoreLevelServiceImpl.class);
	
	@Autowired
	private ScoreLevelRepository scoreLevelRepository;
	
	/**
	 * Get all risk score level data from the repository.
	 * 
	 * @return List of ScoreLevel objects representing all risk score levels.
	 */
	@Override
	public List<ScoreLevel> getAllRiskScoreLevel() {
		LOGGER.info("start : getAllRiskScoreLevel");
		return scoreLevelRepository.findAll();
	}

	/**
	 * Save the given ScoreLevelDto data to the repository.
	 * 
	 * @param scoreLevelDto The ScoreLevelDto object to be saved.
	 * @return The saved ScoreLevelDto object.
	 */
	@Override
	public ScoreLevelDto saveScoreLevel(ScoreLevelDto scoreLevelDto) {
		ScoreLevel scoreLevel = convertDtoToEntity(scoreLevelDto);
		scoreLevelRepository.save(scoreLevel);
		return scoreLevelDto;
	}

	/**
	 * Get the ScoreLevelDto data corresponding to the given risk score.
	 * 
	 * @param score The risk score for which ScoreLevelDto data is requested.
	 * @return The ScoreLevelDto object corresponding to the given score.
	 * @throws IOException if the ScoreLevel for the given score is not found.
	 */
	@Override
	public ScoreLevelDto getScoreLevelByScore(String score) throws IOException {
		LOGGER.info("start : getScoreLevelByScore");
		ScoreLevel riskScoreLevels = scoreLevelRepository.findByScore(score);
		return convertEntityToDto(riskScoreLevels);
	}

	/**
	 * Update the ScoreLevel data with the provided ScoreLevelDto data.
	 * 
	 * @param scoreLevelDto The ScoreLevelDto object containing the updated data.
	 * @return The updated ScoreLevelDto object.
	 */
	@Override
	public ScoreLevelDto updateScoreLevel(ScoreLevelDto scoreLevelDto) {
		LOGGER.info("start : updateScoreLevel");
		ScoreLevel newScoreLevel = scoreLevelRepository.findByScore(scoreLevelDto.getScore());
		newScoreLevel.setLevel(scoreLevelDto.getLevel());
		scoreLevelRepository.save(newScoreLevel);
		return convertEntityToDto(newScoreLevel);
	}

	/**
	 * Delete the ScoreLevel data corresponding to the given ScoreLevelDto object.
	 * 
	 * @param scoreLevelDto The ScoreLevelDto object containing the data to be deleted.
	 */
	@Override
	public void deleteScoreLevel(ScoreLevelDto scoreLevelDto) {
		LOGGER.info("start : deleteRiskScoreLevel");
		scoreLevelRepository.deleteById(scoreLevelDto.getScore());	
	}

	// Helper method to convert ScoreLevelDto to ScoreLevel entity
	private ScoreLevel convertDtoToEntity(ScoreLevelDto scoreLevelDto) {
		return new ScoreLevel(scoreLevelDto.getScore(), scoreLevelDto.getLevel());
	}
	
	// Helper method to convert ScoreLevel entity to ScoreLevelDto
	private ScoreLevelDto convertEntityToDto(ScoreLevel scoreLevel) {
		return new ScoreLevelDto(scoreLevel.getScore(), scoreLevel.getLevel());
	}
}
