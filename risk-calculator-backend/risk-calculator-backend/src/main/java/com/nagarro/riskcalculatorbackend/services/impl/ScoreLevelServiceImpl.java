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
 * Service Implementation Class for Score Level Service
 * @author parasgautam
 *
 */
@Service
public class ScoreLevelServiceImpl implements ScoreLevelService{

	private static final Logger logger = LoggerFactory.getLogger(ScoreLevelServiceImpl.class);
	
	
	@Autowired
	private ScoreLevelRepository scoreLevelRepository;
	
	/**
	 * Method to get risk score level data 
	 */
	@Override
	public List<ScoreLevel> getAllRiskScoreLevel() {
		
		logger.info("start : getAllRiskScoreLevel");
		
		return scoreLevelRepository.findAll();
	}

	@Override
	public ScoreLevelDto saveScoreLevel(ScoreLevelDto scoreLevelDto) {
		// TODO Auto-generated method stub
		ScoreLevel scoreLevel = convertDtoToEntity(scoreLevelDto);
				
		scoreLevelRepository.save(scoreLevel);
		
		return scoreLevelDto;
	}
	
	

	@Override
	public ScoreLevelDto getScoreLevelByScore(String score) throws IOException {
		
		logger.info("start : getScoreLevelByScore");
		
		ScoreLevel riskScoreLevels = scoreLevelRepository.findByScore(score);
		
		if(riskScoreLevels==null) {
			throw new IOException("Risk Score Level not found");
		}
		 
		
		return convertEntityToDto(riskScoreLevels);
	}
	
	
	@Override
	public ScoreLevelDto updateScoreLevel(ScoreLevelDto scoreLevelDto) {
		
		logger.info("start : updateScoreLevel");
		
		ScoreLevel newScoreLevel = scoreLevelRepository.findByScore(scoreLevelDto.getScore());
		
		newScoreLevel.setLevel(scoreLevelDto.getLevel());
		
		scoreLevelRepository.save(newScoreLevel);
		
		return convertEntityToDto(newScoreLevel);
	}

	@Override
	public void deleteScoreLevel(ScoreLevelDto scoreLevelDto) {
		
		logger.info("start : deleteRiskScoreLevel");
		
		scoreLevelRepository.deleteById(scoreLevelDto.getScore());	

	}

	private ScoreLevel convertDtoToEntity(ScoreLevelDto scoreLevelDto) {
		return new ScoreLevel(scoreLevelDto.getScore(),scoreLevelDto.getLevel());
	}
	
	private ScoreLevelDto convertEntityToDto(ScoreLevel scoreLevel) {
		return new ScoreLevelDto(scoreLevel.getScore(),scoreLevel.getLevel());
	}
	

}
