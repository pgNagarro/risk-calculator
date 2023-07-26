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

import com.nagarro.riskcalculatorbackend.dtos.ScoreCapDto;
import com.nagarro.riskcalculatorbackend.models.ScoreCap;
import com.nagarro.riskcalculatorbackend.services.ScoreCapService;


/**
 * Controller class for score cap
 * 
 * @author parasgautam
 *
 */
@RestController
@CrossOrigin(origins="*")
public class ScoreCapController {

	private static final Logger logger = LoggerFactory.getLogger(ScoreCapController.class);
	
	@Autowired
	private ScoreCapService scoreCapService;
	
	/**
	 * Method to get all score cap data
	 * @return
	 */
	@GetMapping("/all-score-cap")
	public ResponseEntity<List<ScoreCap>> getScoreCap(){
		
		logger.info("Request received for fetching all score cap data");
		
		List<ScoreCap> scoreCapList = scoreCapService.getAllScoreCap();
		
		logger.info("Request completed for fetching all score cap data");
		
		return ResponseEntity.ok(scoreCapList);
		
	}
	
	
	@PostMapping("/add-score-cap")
	public ResponseEntity<ScoreCapDto> saveScoreCap(@RequestBody ScoreCapDto scoreCapDto) {
		
		logger.info("Request received for adding score cap data");
		
		ScoreCapDto newScoreCapDto = scoreCapService.saveScoreCap(scoreCapDto);
		
		logger.info("Request completed for adding score cap data");
		
		return ResponseEntity.ok(newScoreCapDto);
		
	}
	
	
	
	@GetMapping("/score-cap/{condition}")
	public ResponseEntity<ScoreCapDto> getSoreCap(@PathVariable String condition){
		
		logger.info("Request received for getting single score cap data");
		
		ScoreCapDto scoreCapDto;
		
		try {
			
			scoreCapDto = scoreCapService.getScoreCapByCondition(condition);
			
			logger.info("Request completed for getting single score cap data");
			
			return ResponseEntity.ok(scoreCapDto);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return ResponseEntity.ok(null);
			
		}	
		
	}
	
	
	@PutMapping("/score-cap/{condition}")
	public ResponseEntity<ScoreCapDto> updateScoreCap(@PathVariable String condition, @RequestBody ScoreCapDto scoreCapDetails){
		
		logger.info("Request received for updating score cap data");
		
		ScoreCapDto scoreCapDto = scoreCapService.updateScoreCap(scoreCapDetails);
		
		logger.info("Request received for updating score cap data");
		
		return ResponseEntity.ok(scoreCapDto);

	}
	
	
	@DeleteMapping("/score-cap/{condition}")
	public ResponseEntity<Map<String, Boolean>> deleteScoreCap(@PathVariable String condition){
		
		logger.info("Request received for deleting score cap data");
		
		ScoreCapDto scoreCapDto;
		
		try {
			
			scoreCapDto = scoreCapService.getScoreCapByCondition(condition);
			
			scoreCapService.deleteScoreCap(scoreCapDto);
			
			Map<String,Boolean> response = new HashMap<>();
			response.put("Deleted",Boolean.TRUE);
			
			logger.info("Request received for deleting score cap data");
			
			return ResponseEntity.ok(response);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			Map<String,Boolean> response = new HashMap<>();
			response.put("Unable to Delete",Boolean.FALSE);
			return ResponseEntity.ok(response);
			
		}
		
		
	}
	
}
