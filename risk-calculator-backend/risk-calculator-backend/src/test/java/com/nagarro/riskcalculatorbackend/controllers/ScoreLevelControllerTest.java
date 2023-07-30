package com.nagarro.riskcalculatorbackend.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.riskcalculatorbackend.dtos.ScoreLevelDto;
import com.nagarro.riskcalculatorbackend.services.ScoreLevelService;

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

@ContextConfiguration(classes = {ScoreLevelController.class})
@ExtendWith(SpringExtension.class)
class ScoreLevelControllerTest {
    @Autowired
    private ScoreLevelController scoreLevelController;

    @MockBean
    private ScoreLevelService scoreLevelService;

    /**
     * Method under test: {@link ScoreLevelController#deleteRiskScoreLevel(String)}
     */
    @Test
    void testDeleteRiskScoreLevel() throws Exception {
        // Arrange
        when(scoreLevelService.getScoreLevelByScore(Mockito.<String>any())).thenReturn(new ScoreLevelDto("Score", "Level"));
        doNothing().when(scoreLevelService).deleteScoreLevel(Mockito.<ScoreLevelDto>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/score-level/{score}", "Score");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Deleted\":true}"));
    }

    /**
     * Method under test: {@link ScoreLevelController#deleteRiskScoreLevel(String)}
     */
    @Test
    void testDeleteRiskScoreLevel2() throws Exception {
        // Arrange
        when(scoreLevelService.getScoreLevelByScore(Mockito.<String>any())).thenThrow(new IOException("?"));
        doNothing().when(scoreLevelService).deleteScoreLevel(Mockito.<ScoreLevelDto>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/score-level/{score}", "Score");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Unable to Delete\":false}"));
    }

    /**
     * Method under test: {@link ScoreLevelController#getRiskScoreLevel()}
     */
    @Test
    void testGetRiskScoreLevel() throws Exception {
        // Arrange
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all-score-level");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ScoreLevelController#getRiskScoreLevel()}
     */
    @Test
    void testGetRiskScoreLevel2() throws Exception {
        // Arrange
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all-score-level");
        requestBuilder.characterEncoding("Encoding");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ScoreLevelController#getRiskScoreLevelByScore(String)}
     */
    @Test
    void testGetRiskScoreLevelByScore() throws Exception {
        // Arrange
        when(scoreLevelService.getScoreLevelByScore(Mockito.<String>any()))
                .thenReturn(new ScoreLevelDto("Score", "Level"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/score-level/{score}", "Score");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"score\":\"Score\",\"level\":\"Level\"}"));
    }

    /**
     * Method under test: {@link ScoreLevelController#getRiskScoreLevelByScore(String)}
     */
    @Test
    void testGetRiskScoreLevelByScore2() throws Exception {
        // Arrange
        when(scoreLevelService.getScoreLevelByScore(Mockito.<String>any())).thenThrow(new IOException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/score-level/{score}", "Score");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ScoreLevelController#saveRiskScoreLevel(ScoreLevelDto)}
     */
    @Test
    void testSaveRiskScoreLevel() throws Exception {
        // Arrange
        when(scoreLevelService.saveScoreLevel(Mockito.<ScoreLevelDto>any()))
                .thenReturn(new ScoreLevelDto("Score", "Level"));

        ScoreLevelDto scoreLevelDto = new ScoreLevelDto();
        scoreLevelDto.setLevel("Level");
        scoreLevelDto.setScore("Score");
        String content = (new ObjectMapper()).writeValueAsString(scoreLevelDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add-score-level")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"score\":\"Score\",\"level\":\"Level\"}"));
    }

    /**
     * Method under test: {@link ScoreLevelController#updateRiskScoreLevel(String, ScoreLevelDto)}
     */
    @Test
    void testUpdateRiskScoreLevel() throws Exception {
        // Arrange
        when(scoreLevelService.updateScoreLevel(Mockito.<ScoreLevelDto>any()))
                .thenReturn(new ScoreLevelDto("Score", "Level"));

        ScoreLevelDto scoreLevelDto = new ScoreLevelDto();
        scoreLevelDto.setLevel("Level");
        scoreLevelDto.setScore("Score");
        String content = (new ObjectMapper()).writeValueAsString(scoreLevelDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/score-level/{score}", "Score")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(scoreLevelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"score\":\"Score\",\"level\":\"Level\"}"));
    }
}

