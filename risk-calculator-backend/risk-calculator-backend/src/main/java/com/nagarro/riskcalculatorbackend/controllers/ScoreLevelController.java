package com.nagarro.riskcalculatorbackend.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.nagarro.riskcalculatorbackend.dtos.ScoreLevelDto;
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
	@GetMapping("/all-score-level")
	public ResponseEntity<List<ScoreLevel>> getRiskScoreLevel(){
		
		logger.info("Request received for fetching all the risk score level data");
		
		List<ScoreLevel> scoreLevelList = scoreLevelService.getAllRiskScoreLevel();
		
		logger.info("Request completed for fetching all the risk score level data");
		
		return ResponseEntity.ok(scoreLevelList);
		
	}
	
	
	/**
	 * Method to saving score level data
	 * @param riskScoreLevel
	 * @return
	 */
	@PostMapping("/add-score-level")
	public ResponseEntity<ScoreLevelDto> saveRiskScoreLevel(@RequestBody ScoreLevelDto scoreLevelDto) {
		
		logger.info("Request received for adding risk score level data");
		
		ScoreLevelDto newRiskScoreLevelDto = scoreLevelService.saveScoreLevel(scoreLevelDto);
		
		logger.info("Request completed for adding risk score level data");
		
		return ResponseEntity.ok(newRiskScoreLevelDto);
		
	}

}
