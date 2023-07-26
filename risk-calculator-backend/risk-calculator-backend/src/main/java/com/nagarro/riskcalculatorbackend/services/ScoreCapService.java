package com.nagarro.riskcalculatorbackend.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.riskcalculatorbackend.dtos.ScoreCapDto;
import com.nagarro.riskcalculatorbackend.models.ScoreCap;

/**
 * Interface for Score Cap Service
 * @author parasgautam
 *
 */
public interface ScoreCapService {
	
	List<ScoreCap> getAllScoreCap();
		
	ScoreCapDto saveScoreCap(ScoreCapDto scoreCapDto);

	ScoreCapDto getScoreCapByCondition(String condition) throws IOException;
	
	ScoreCapDto updateScoreCap(ScoreCapDto scoreCapDto);

	void deleteScoreCap(ScoreCapDto scoreCapDto);

}