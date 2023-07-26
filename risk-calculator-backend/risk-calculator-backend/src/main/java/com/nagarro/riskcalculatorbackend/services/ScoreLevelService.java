package com.nagarro.riskcalculatorbackend.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.riskcalculatorbackend.models.ScoreLevel;

/**
 * Interface for Score Level Service
 * @author parasgautam
 *
 */
public interface ScoreLevelService {
	
	List<ScoreLevel> getAllRiskScoreLevel();

	ScoreLevel saveScoreLevel(ScoreLevel scoreLevel);

	ScoreLevel getScoreLevelByScore(String score) throws IOException;
	
	void deleteScoreLevel(ScoreLevel scoreLevel);

}