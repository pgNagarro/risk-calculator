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

import com.nagarro.riskcalculatorbackend.models.Job;
import com.nagarro.riskcalculatorbackend.services.JobService;

/**
 * Controller class for Job table 
 * 
 * @author parasgautam
 * 
 */
@RestController
@CrossOrigin(origins="*")
public class JobController {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);
	
	@Autowired
	private JobService jobService;
	
	/**
	 * API Method for getting Job data
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/job-status")
	public ResponseEntity<List<Job>> displayJob() throws IOException {
		
		LOGGER.info("Request received for fetching result");
		
		List<Job> jobList = jobService.getAllJobData();
		
		LOGGER.info("Request completed for fetching result");
		
		return ResponseEntity.ok(jobList);
		
	}
}
