package com.nagarro.riskcalculatorbackend.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nagarro.riskcalculatorbackend.enums.JobStatus;
import com.nagarro.riskcalculatorbackend.models.Job;
import com.nagarro.riskcalculatorbackend.repositories.JobRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JobServiceImpl.class})
@ExtendWith(SpringExtension.class)
class JobServiceImplTest {
    @MockBean
    private JobRepository jobRepository;

    @Autowired
    private JobServiceImpl jobServiceImpl;

    /**
     * Method under test: {@link JobServiceImpl#addJob(Job)}
     */
    @Test
    void testAddJob() {
        Job job = new Job();
        job.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        job.setDesc("The characteristics of someone or something");
        job.setId(1L);
        job.setJobStatus(JobStatus.SUCCESSFULL);
        when(jobRepository.save(Mockito.<Job>any())).thenReturn(job);

        Job job2 = new Job();
        Date date = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        job2.setDate(date);
        job2.setDesc("The characteristics of someone or something");
        job2.setId(1L);
        job2.setJobStatus(JobStatus.SUCCESSFULL);
        jobServiceImpl.addJob(job2);
        verify(jobRepository).save(Mockito.<Job>any());
        assertSame(date, job2.getDate());
        assertEquals(JobStatus.SUCCESSFULL, job2.getJobStatus());
        assertEquals(1L, job2.getId().longValue());
        assertEquals("The characteristics of someone or something", job2.getDesc());
        assertTrue(jobServiceImpl.getAllJobData().isEmpty());
    }

    /**
     * Method under test: {@link JobServiceImpl#getAllJobData()}
     */
    @Test
    void testGetAllJobData() {
        ArrayList<Job> jobList = new ArrayList<>();
        when(jobRepository.findAll()).thenReturn(jobList);
        List<Job> actualAllJobData = jobServiceImpl.getAllJobData();
        assertSame(jobList, actualAllJobData);
        assertTrue(actualAllJobData.isEmpty());
        verify(jobRepository).findAll();
    }
}

