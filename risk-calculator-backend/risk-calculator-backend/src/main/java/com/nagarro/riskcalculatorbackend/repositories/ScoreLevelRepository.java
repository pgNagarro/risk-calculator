package com.nagarro.riskcalculatorbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.riskcalculatorbackend.models.ScoreLevel;

/**
 * Interface for Score Level repo
 * @author parasgautam
 *
 */
public interface ScoreLevelRepository  extends JpaRepository<ScoreLevel, String>{

	public ScoreLevel findByScore(String name);
}

