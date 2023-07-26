package com.nagarro.riskcalculatorbackend.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.riskcalculatorbackend.models.ScoreLevel;
import com.nagarro.riskcalculatorbackend.services.ScoreLevelService;

/**
 * Controller class for Score level
 * 
 * @author parasgautam
 *
 */
@RestController
@CrossOrigin(origins="*")
public class ScoreLevelController {
	
private static final Logger logger = LoggerFactory.getLogger(ScoreLevelController.class);
	
	@Autowired
	private ScoreLevelService scoreLevelService;
	
	/**
	 * Method to get all score level data
	 * @return
	 */
	@GetMapping("/score-level")
	public ResponseEntity<List<ScoreLevel>> getRiskScoreLevel(){
		
		logger.info("Request received for fetching all the risk score level data");
		
		List<ScoreLevel> scoreLevelList = scoreLevelService.getAllRiskScoreLevel();
		
		logger.info("Request completed for fetching all the risk score level data");
		
		return ResponseEntity.ok(scoreLevelList);
		
	}

}
