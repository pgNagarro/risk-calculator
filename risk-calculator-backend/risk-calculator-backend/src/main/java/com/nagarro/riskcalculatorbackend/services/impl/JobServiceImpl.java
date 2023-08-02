package com.nagarro.riskcalculatorbackend.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.riskcalculatorbackend.models.Job;
import com.nagarro.riskcalculatorbackend.repositories.JobRepository;
import com.nagarro.riskcalculatorbackend.services.JobService;



/**
 * Service Implementation Class for Job Service
 * @author parasgautam
 *
 */
@Service
public class JobServiceImpl implements JobService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);
	
	@Autowired
	private JobRepository jobRepository;
	
	/**
	 * Method to add job for every time when result is calculated
	 */
	@Override
	public void addJob(Job job) {
		
		LOGGER.info("start: addJob");
		jobRepository.save(job);
		
	}

	/**
	 * Method to get all job data
	 */
	@Override
	public List<Job> getAllJobData() {
		
		LOGGER.info("start: getAllJobData");
		return jobRepository.findAll();
	}
	
	
}
