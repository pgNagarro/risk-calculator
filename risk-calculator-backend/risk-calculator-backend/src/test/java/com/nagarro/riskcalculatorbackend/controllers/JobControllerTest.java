package com.nagarro.riskcalculatorbackend.controllers;

import static org.mockito.Mockito.when;

import com.nagarro.riskcalculatorbackend.services.JobService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {JobController.class})
@ExtendWith(SpringExtension.class)
class JobControllerTest {
    @Autowired
    private JobController jobController;

    @MockBean
    private JobService jobService;

    /**
     * Method under test: {@link JobController#displayJob()}
     */
    @Test
    void testDisplayJob() throws Exception {
        when(jobService.getAllJobData()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/job-status");
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link JobController#displayJob()}
     */
    @Test
    void testDisplayJob2() throws Exception {
        when(jobService.getAllJobData()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/job-status");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(jobController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

