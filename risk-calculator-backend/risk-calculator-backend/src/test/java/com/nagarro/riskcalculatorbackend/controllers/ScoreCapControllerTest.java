package com.nagarro.riskcalculatorbackend.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.riskcalculatorbackend.dtos.ScoreCapDto;
import com.nagarro.riskcalculatorbackend.services.ScoreCapService;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ScoreCapController.class})
@ExtendWith(SpringExtension.class)
class ScoreCapControllerTest {
    @Autowired
    private ScoreCapController scoreCapController;

    @MockBean
    private ScoreCapService scoreCapService;

    /**
     * Method under test: {@link ScoreCapController#saveScoreCap(ScoreCapDto)}
     */
    @Test
    void testSaveScoreCap() throws Exception {
        // Arrange
        when(scoreCapService.saveScoreCap(Mockito.<ScoreCapDto>any())).thenReturn(new ScoreCapDto("Condition", 3));

        ScoreCapDto scoreCapDto = new ScoreCapDto();
        scoreCapDto.setCondition("Condition");
        scoreCapDto.setTotalRiskCappedScore(3);
        String content = (new ObjectMapper()).writeValueAsString(scoreCapDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add-score-cap")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"condition\":\"Condition\",\"totalRiskCappedScore\":3}"));
    }

    /**
     * Method under test: {@link ScoreCapController#updateScoreCap(String, ScoreCapDto)}
     */
    @Test
    void testUpdateScoreCap() throws Exception {
        // Arrange
        when(scoreCapService.updateScoreCap(Mockito.<ScoreCapDto>any())).thenReturn(new ScoreCapDto("Condition", 3));

        ScoreCapDto scoreCapDto = new ScoreCapDto();
        scoreCapDto.setCondition("Condition");
        scoreCapDto.setTotalRiskCappedScore(3);
        String content = (new ObjectMapper()).writeValueAsString(scoreCapDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/score-cap/{condition}", "Condition")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"condition\":\"Condition\",\"totalRiskCappedScore\":3}"));
    }

    /**
     * Method under test: {@link ScoreCapController#deleteScoreCap(String)}
     */
    @Test
    void testDeleteScoreCap() throws Exception {
        // Arrange
        when(scoreCapService.getScoreCapByCondition(Mockito.<String>any())).thenReturn(new ScoreCapDto("Condition", 3));
        doNothing().when(scoreCapService).deleteScoreCap(Mockito.<ScoreCapDto>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/score-cap/{condition}", "Condition");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Deleted\":true}"));
    }

    /**
     * Method under test: {@link ScoreCapController#deleteScoreCap(String)}
     */
    @Test
    void testDeleteScoreCap2() throws Exception {
        // Arrange
        when(scoreCapService.getScoreCapByCondition(Mockito.<String>any())).thenThrow(new IOException("?"));
        doNothing().when(scoreCapService).deleteScoreCap(Mockito.<ScoreCapDto>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/score-cap/{condition}",
                "Condition");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Unable to Delete\":false}"));
    }

    /**
     * Method under test: {@link ScoreCapController#getScoreCap()}
     */
    @Test
    void testGetScoreCap() throws Exception {
        // Arrange
        when(scoreCapService.getAllScoreCap()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all-score-cap");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ScoreCapController#getScoreCap()}
     */
    @Test
    void testGetScoreCap2() throws Exception {
        // Arrange
        when(scoreCapService.getAllScoreCap()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all-score-cap");
        requestBuilder.characterEncoding("Encoding");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ScoreCapController#getScoreCap(String)}
     */
    @Test
    void testGetScoreCap3() throws Exception {
        // Arrange
        when(scoreCapService.getScoreCapByCondition(Mockito.<String>any())).thenReturn(new ScoreCapDto("Condition", 3));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/score-cap/{condition}", "Condition");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"condition\":\"Condition\",\"totalRiskCappedScore\":3}"));
    }

    /**
     * Method under test: {@link ScoreCapController#getScoreCap(String)}
     */
    @Test
    void testGetScoreCap4() throws Exception {
        // Arrange
        when(scoreCapService.getScoreCapByCondition(Mockito.<String>any())).thenThrow(new IOException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/score-cap/{condition}", "Condition");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreCapController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

