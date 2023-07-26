package com.nagarro.riskcalculatorbackend.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	/**
	 * Method for getting single score level data by score
	 * @param score
	 * @return
	 */
	@GetMapping("/score-level/{score}")
	public ResponseEntity<ScoreLevelDto> getRiskScoreLevelByScore(@PathVariable String score){
		
		logger.info("Request received for getting single risk score level data");
		
		ScoreLevelDto scoreLevelDto;
		
		try {
			
			scoreLevelDto = scoreLevelService.getScoreLevelByScore(score);
			
			logger.info("Request completed for getting single risk score level data");
			
			return ResponseEntity.ok(scoreLevelDto);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return ResponseEntity.ok(null);
			
		}		
	}
	
	/**
	 * Method for updating score level data
	 * @param score
	 * @param riskScoreLevelDetails
	 * @return
	 */
	@PutMapping("/score-level/{score}")
	public ResponseEntity<ScoreLevelDto> updateRiskScoreLevel(@PathVariable String score, @RequestBody ScoreLevelDto riskScoreLevelDetails){
		
		logger.info("Request received for updating risk score level");
		
		ScoreLevelDto scoreLevelDto = scoreLevelService.updateScoreLevel(riskScoreLevelDetails);
		
		logger.info("Request completed for updating risk score level");
		
		return ResponseEntity.ok(scoreLevelDto);
		
	}
	
	
	/**
	 * Method for deleting score level data
	 * @param score
	 * @return
	 */
	@DeleteMapping("/score-level/{score}")
	public ResponseEntity<Map<String, Boolean>> deleteRiskScoreLevel(@PathVariable String score){
		
		logger.info("Request received for deleting risk score level");
		
		ScoreLevelDto scoreLevelDto;
		
		try {
			scoreLevelDto = scoreLevelService.getScoreLevelByScore(score);
			
			scoreLevelService.deleteScoreLevel(scoreLevelDto);
			
			Map<String,Boolean> response = new HashMap<>();
			response.put("Deleted",Boolean.TRUE);
			
			logger.info("Request completed for deleting risk score level");
			
			return ResponseEntity.ok(response);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			Map<String,Boolean> response = new HashMap<>();
			response.put("Unable to Delete",Boolean.FALSE);
			return ResponseEntity.ok(null);
			
		}
		
		
	}
	
	
	
	
	
	

}
