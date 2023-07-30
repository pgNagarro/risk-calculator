package com.nagarro.riskcalculatorbackend.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nagarro.riskcalculatorbackend.dtos.ScoreCapDto;
import com.nagarro.riskcalculatorbackend.models.ScoreCap;
import com.nagarro.riskcalculatorbackend.repositories.ScoreCapRepository;

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

@ContextConfiguration(classes = {ScoreCapServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ScoreCapServiceImplTest {
    @MockBean
    private ScoreCapRepository scoreCapRepository;

    @Autowired
    private ScoreCapServiceImpl scoreCapServiceImpl;

    /**
     * Method under test: {@link ScoreCapServiceImpl#getAllScoreCap()}
     */
    @Test
    void testGetAllScoreCap() {
        // Arrange
        ArrayList<ScoreCap> scoreCapList = new ArrayList<>();
        when(scoreCapRepository.findAll()).thenReturn(scoreCapList);

        // Act
        List<ScoreCap> actualAllScoreCap = scoreCapServiceImpl.getAllScoreCap();

        // Assert
        assertSame(scoreCapList, actualAllScoreCap);
        assertTrue(actualAllScoreCap.isEmpty());
        verify(scoreCapRepository).findAll();
    }

    /**
     * Method under test: {@link ScoreCapServiceImpl#saveScoreCap(ScoreCapDto)}
     */
    @Test
    void testSaveScoreCap() {
        // Arrange
        ScoreCap scoreCap = new ScoreCap();
        scoreCap.setCondition("Condition");
        scoreCap.setTotalRiskCappedScore(3);
        when(scoreCapRepository.save(Mockito.<ScoreCap>any())).thenReturn(scoreCap);
        ScoreCapDto scoreCapDto = new ScoreCapDto("Condition", 3);

        // Act and Assert
        assertSame(scoreCapDto, scoreCapServiceImpl.saveScoreCap(scoreCapDto));
        verify(scoreCapRepository).save(Mockito.<ScoreCap>any());
    }

  

    /**
     * Method under test: {@link ScoreCapServiceImpl#getScoreCapByCondition(String)}
     */
    @Test
    void testGetScoreCapByCondition() throws IOException {
        // Arrange
        ScoreCap scoreCap = new ScoreCap();
        scoreCap.setCondition("Condition");
        scoreCap.setTotalRiskCappedScore(3);
        when(scoreCapRepository.findByCondition(Mockito.<String>any())).thenReturn(scoreCap);

        // Act
        ScoreCapDto actualScoreCapByCondition = scoreCapServiceImpl.getScoreCapByCondition("Condition");

        // Assert
        assertEquals("Condition", actualScoreCapByCondition.getCondition());
        assertEquals(3, actualScoreCapByCondition.getTotalRiskCappedScore());
        verify(scoreCapRepository).findByCondition(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ScoreCapServiceImpl#getScoreCap(String)}
     */
    @Test
    void testGetScoreCap() {
        // Arrange
        ScoreCap scoreCap = new ScoreCap();
        scoreCap.setCondition("Condition");
        scoreCap.setTotalRiskCappedScore(3);
        when(scoreCapRepository.findByCondition(Mockito.<String>any())).thenReturn(scoreCap);

        // Act and Assert
        assertSame(scoreCap, scoreCapServiceImpl.getScoreCap("Condition"));
        verify(scoreCapRepository).findByCondition(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ScoreCapServiceImpl#updateScoreCap(ScoreCapDto)}
     */
    @Test
    void testUpdateScoreCap() {
        // Arrange
        ScoreCap scoreCap = new ScoreCap();
        scoreCap.setCondition("Condition");
        scoreCap.setTotalRiskCappedScore(3);

        ScoreCap scoreCap2 = new ScoreCap();
        scoreCap2.setCondition("Condition");
        scoreCap2.setTotalRiskCappedScore(3);
        when(scoreCapRepository.save(Mockito.<ScoreCap>any())).thenReturn(scoreCap2);
        when(scoreCapRepository.findByCondition(Mockito.<String>any())).thenReturn(scoreCap);

        // Act
        ScoreCapDto actualUpdateScoreCapResult = scoreCapServiceImpl.updateScoreCap(new ScoreCapDto("Condition", 3));

        // Assert
        assertEquals("Condition", actualUpdateScoreCapResult.getCondition());
        assertEquals(3, actualUpdateScoreCapResult.getTotalRiskCappedScore());
        verify(scoreCapRepository).findByCondition(Mockito.<String>any());
        verify(scoreCapRepository).save(Mockito.<ScoreCap>any());
    }

   

    /**
     * Method under test: {@link ScoreCapServiceImpl#deleteScoreCap(ScoreCapDto)}
     */
    @Test
    void testDeleteScoreCap() {
        // Arrange
        doNothing().when(scoreCapRepository).deleteById(Mockito.<String>any());
        ScoreCapDto scoreCapDto = new ScoreCapDto("Condition", 3);

        // Act
        scoreCapServiceImpl.deleteScoreCap(scoreCapDto);

        // Assert that nothing has changed
        verify(scoreCapRepository).deleteById(Mockito.<String>any());
        assertEquals("Condition", scoreCapDto.getCondition());
        assertEquals(3, scoreCapDto.getTotalRiskCappedScore());
        assertTrue(scoreCapServiceImpl.getAllScoreCap().isEmpty());
    }

}

