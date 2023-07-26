package com.nagarro.riskcalculatorbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.riskcalculatorbackend.models.ScoreCap;


/**
 * Interface for Score Cap repo
 * @author parasgautam
 *
 */
public interface ScoreCapRepository extends JpaRepository<ScoreCap, String>{
	
	public ScoreCap findByCondition(String condition);
	
}
