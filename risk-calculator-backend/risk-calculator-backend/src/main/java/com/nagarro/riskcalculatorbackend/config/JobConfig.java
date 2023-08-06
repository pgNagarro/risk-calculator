package com.nagarro.riskcalculatorbackend.config;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.riskcalculatorbackend.enums.JobStatus;
import com.nagarro.riskcalculatorbackend.models.Job;
import com.nagarro.riskcalculatorbackend.repositories.JobRepository;

@Component
public class JobConfig  {

	 private static final Logger LOGGER = LoggerFactory.getLogger(JobConfig.class);
	 
    @Autowired
    private JobRepository jobRepository;

    /**
     * Creates and saves a new Job object to the database.
     *
     * @param date        The date for the job.
     * @param status      The status of the job (JobStatus enum).
     * @param description The description of the job.
     */
    public void createAndSaveJob(LocalDateTime date, JobStatus status,String description) {
        Job job = new Job();
        job.setDesc(description);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = date.format(myFormatObj);
        job.setDate(formattedDate);
        job.setJobStatus(status);
        jobRepository.save(job);
        
        LOGGER.info("Job created and saved to the database. Description: {}, Date: {}, Status: {}", description, date, status);
    }
}
