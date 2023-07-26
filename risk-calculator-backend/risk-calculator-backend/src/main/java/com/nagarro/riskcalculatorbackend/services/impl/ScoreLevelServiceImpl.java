package com.nagarro.riskcalculatorbackend.services.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
	public ScoreLevel saveScoreLevel(ScoreLevel scoreLevel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreLevel getScoreLevelByScore(String score) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteScoreLevel(ScoreLevel scoreLevel) {
		// TODO Auto-generated method stub
		
	}

	

}
