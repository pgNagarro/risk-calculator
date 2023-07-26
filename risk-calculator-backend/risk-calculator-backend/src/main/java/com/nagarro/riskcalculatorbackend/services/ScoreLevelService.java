package com.nagarro.riskcalculatorbackend.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.riskcalculatorbackend.dtos.ScoreLevelDto;
import com.nagarro.riskcalculatorbackend.models.ScoreLevel;

/**
 * Interface for Score Level Service
 * @author parasgautam
 *
 */
public interface ScoreLevelService {
	
	List<ScoreLevel> getAllRiskScoreLevel();

	ScoreLevelDto saveScoreLevel(ScoreLevelDto scoreLevelDto);

	ScoreLevelDto getScoreLevelByScore(String score) throws IOException;
	
	ScoreLevelDto updateScoreLevel(ScoreLevelDto scoreLevelDto);
	
	void deleteScoreLevel(ScoreLevelDto scoreLevelDto);

}
