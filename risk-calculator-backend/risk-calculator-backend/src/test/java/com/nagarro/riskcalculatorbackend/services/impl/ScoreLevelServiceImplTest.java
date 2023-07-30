package com.nagarro.riskcalculatorbackend.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nagarro.riskcalculatorbackend.dtos.ScoreLevelDto;
import com.nagarro.riskcalculatorbackend.models.ScoreLevel;
import com.nagarro.riskcalculatorbackend.repositories.ScoreLevelRepository;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ScoreLevelServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ScoreLevelServiceImplTest {
    @MockBean
    private ScoreLevelRepository scoreLevelRepository;

    @Autowired
    private ScoreLevelServiceImpl scoreLevelServiceImpl;

    /**
     * Method under test: {@link ScoreLevelServiceImpl#getAllRiskScoreLevel()}
     */
    @Test
    void testGetAllRiskScoreLevel() {
        // Arrange
        ArrayList<ScoreLevel> scoreLevelList = new ArrayList<>();
        when(scoreLevelRepository.findAll()).thenReturn(scoreLevelList);

        // Act
        List<ScoreLevel> actualAllRiskScoreLevel = scoreLevelServiceImpl.getAllRiskScoreLevel();

        // Assert
        assertSame(scoreLevelList, actualAllRiskScoreLevel);
        assertTrue(actualAllRiskScoreLevel.isEmpty());
        verify(scoreLevelRepository).findAll();
    }

    /**
     * Method under test: {@link ScoreLevelServiceImpl#saveScoreLevel(ScoreLevelDto)}
     */
    @Test
    void testSaveScoreLevel() {
        // Arrange
        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel("Level");
        scoreLevel.setScore("Score");
        when(scoreLevelRepository.save(Mockito.<ScoreLevel>any())).thenReturn(scoreLevel);
        ScoreLevelDto scoreLevelDto = new ScoreLevelDto("Score", "Level");

        // Act and Assert
        assertSame(scoreLevelDto, scoreLevelServiceImpl.saveScoreLevel(scoreLevelDto));
        verify(scoreLevelRepository).save(Mockito.<ScoreLevel>any());
    }


    /**
     * Method under test: {@link ScoreLevelServiceImpl#getScoreLevelByScore(String)}
     */
    @Test
    void testGetScoreLevelByScore() throws IOException {
        // Arrange
        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel("Level");
        scoreLevel.setScore("Score");
        when(scoreLevelRepository.findByScore(Mockito.<String>any())).thenReturn(scoreLevel);

        // Act
        ScoreLevelDto actualScoreLevelByScore = scoreLevelServiceImpl.getScoreLevelByScore("Score");

        // Assert
        assertEquals("Level", actualScoreLevelByScore.getLevel());
        assertEquals("Score", actualScoreLevelByScore.getScore());
        verify(scoreLevelRepository).findByScore(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ScoreLevelServiceImpl#updateScoreLevel(ScoreLevelDto)}
     */
    @Test
    void testUpdateScoreLevel() {
        // Arrange
        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel("Level");
        scoreLevel.setScore("Score");

        ScoreLevel scoreLevel2 = new ScoreLevel();
        scoreLevel2.setLevel("Level");
        scoreLevel2.setScore("Score");
        when(scoreLevelRepository.save(Mockito.<ScoreLevel>any())).thenReturn(scoreLevel2);
        when(scoreLevelRepository.findByScore(Mockito.<String>any())).thenReturn(scoreLevel);

        // Act
        ScoreLevelDto actualUpdateScoreLevelResult = scoreLevelServiceImpl
                .updateScoreLevel(new ScoreLevelDto("Score", "Level"));

        // Assert
        assertEquals("Level", actualUpdateScoreLevelResult.getLevel());
        assertEquals("Score", actualUpdateScoreLevelResult.getScore());
        verify(scoreLevelRepository).findByScore(Mockito.<String>any());
        verify(scoreLevelRepository).save(Mockito.<ScoreLevel>any());
    }

    
    /**
     * Method under test: {@link ScoreLevelServiceImpl#deleteScoreLevel(ScoreLevelDto)}
     */
    @Test
    void testDeleteScoreLevel() {
        // Arrange
        doNothing().when(scoreLevelRepository).deleteById(Mockito.<String>any());
        ScoreLevelDto scoreLevelDto = new ScoreLevelDto("Score", "Level");

        // Act
        scoreLevelServiceImpl.deleteScoreLevel(scoreLevelDto);

        // Assert that nothing has changed
        verify(scoreLevelRepository).deleteById(Mockito.<String>any());
        assertEquals("Level", scoreLevelDto.getLevel());
        assertEquals("Score", scoreLevelDto.getScore());
        assertTrue(scoreLevelServiceImpl.getAllRiskScoreLevel().isEmpty());
    }

}

