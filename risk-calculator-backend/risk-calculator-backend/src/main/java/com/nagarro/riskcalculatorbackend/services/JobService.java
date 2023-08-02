package com.nagarro.riskcalculatorbackend.services;

import java.util.List;

import com.nagarro.riskcalculatorbackend.models.Job;

/**
 * Interface for Job Service
 * @author parasgautam
 *
 */
public interface JobService {

	void addJob(Job job);
	
	List<Job> getAllJobData();
}

