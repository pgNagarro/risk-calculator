package com.nagarro.riskcalculatorbackend.controllers;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.riskcalculatorbackend.models.Result;
import com.nagarro.riskcalculatorbackend.services.ResultService;



/**
 * Controller class for output table which
 * calculates and gets output
 * 
 * @author parasgautam
 * 
 */
@RestController
@CrossOrigin(origins="*")
public class ResultController {

	private static final Logger logger = LoggerFactory.getLogger(ResultController.class);
	
	@Autowired
	private ResultService resultService;
	
	/**
	 * API Method for getting result
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/result")
	public ResponseEntity<List<Result>> displayResult() throws IOException {
		
		logger.info("Request received for fetching result");
		
		resultService.calculateResult();
		
		logger.info("Request completed for fetching result");
		
		List<Result> resultList = resultService.getResult();
		
		return ResponseEntity.ok(resultList);
		
	}
	
}
