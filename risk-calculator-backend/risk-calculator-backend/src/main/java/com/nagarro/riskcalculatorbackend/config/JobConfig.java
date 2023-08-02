package com.nagarro.riskcalculatorbackend.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.riskcalculatorbackend.enums.JobStatus;
import com.nagarro.riskcalculatorbackend.models.Job;
import com.nagarro.riskcalculatorbackend.repositories.JobRepository;

@Component
public class JobConfig  {

    @Autowired
    private JobRepository jobRepository;

    public void createAndSaveJob(Date date, JobStatus status,String description) {
        Job job = new Job();
        job.setDesc(description);
        job.setDate(date);
        job.setJobStatus(status);
        jobRepository.save(job);
    }
}
