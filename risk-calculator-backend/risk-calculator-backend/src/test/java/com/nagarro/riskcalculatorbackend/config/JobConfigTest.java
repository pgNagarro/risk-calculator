package com.nagarro.riskcalculatorbackend.config;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nagarro.riskcalculatorbackend.enums.JobStatus;
import com.nagarro.riskcalculatorbackend.models.Job;
import com.nagarro.riskcalculatorbackend.repositories.JobRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JobConfig.class})
@ExtendWith(SpringExtension.class)
class JobConfigTest {
    @Autowired
    private JobConfig jobConfig;

    @MockBean
    private JobRepository jobRepository;

    /**
     * Method under test: {@link JobConfig#createAndSaveJob(Date, JobStatus, String)}
     */
    @Test
    void testCreateAndSaveJob() {
        Job job = new Job();
        job.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        job.setDesc("The characteristics of someone or something");
        job.setId(1L);
        job.setJobStatus(JobStatus.SUCCESSFULL);
        when(jobRepository.save(Mockito.<Job>any())).thenReturn(job);
        jobConfig.createAndSaveJob(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                JobStatus.SUCCESSFULL, "The characteristics of someone or something");
        verify(jobRepository).save(Mockito.<Job>any());
    }

    /**
     * Method under test: {@link JobConfig#createAndSaveJob(Date, JobStatus, String)}
     */
    @Test
    void testCreateAndSaveJob2() {
        Job job = new Job();
        job.setDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        job.setDesc("The characteristics of someone or something");
        job.setId(1L);
        job.setJobStatus(JobStatus.SUCCESSFULL);
        when(jobRepository.save(Mockito.<Job>any())).thenReturn(job);
        jobConfig.createAndSaveJob(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()),
                JobStatus.FAILED, "The characteristics of someone or something");
        verify(jobRepository).save(Mockito.<Job>any());
    }
}

