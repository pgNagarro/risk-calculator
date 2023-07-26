package com.nagarro.riskcalculatorbackend.services.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.riskcalculatorbackend.dtos.ScoreCapDto;
import com.nagarro.riskcalculatorbackend.models.ScoreCap;
import com.nagarro.riskcalculatorbackend.repositories.ScoreCapRepository;
import com.nagarro.riskcalculatorbackend.services.ScoreCapService;

import lombok.AllArgsConstructor;

/**
 * Service Implementation Class for Score Cap Service
 * @author parasgautam
 *
 */
@Service
@AllArgsConstructor
public class ScoreCapServiceImpl implements ScoreCapService{

	private static final Logger logger = LoggerFactory.getLogger(ScoreCapServiceImpl.class);

	@Autowired
	private ScoreCapRepository scoreCapRepository;
	

	/**
	 * Method to get all score cap data
	 */
	@Override
	public List<ScoreCap> getAllScoreCap() {
		
		logger.info("start : getAllScoreCap");
		return scoreCapRepository.findAll();
	}

	
	/**
	 * Method to save score cap data
	 */
	@Override
	public ScoreCapDto saveScoreCap(ScoreCapDto scoreCapDto) {
		
		logger.info("start : saveScoreCap");
		
		ScoreCap scoreCap = convertDtoToEntity(scoreCapDto);
		
		scoreCapRepository.save(scoreCap);
		
		return scoreCapDto;
		
	}
	

	/**
	 * Method to single get score cap data by condition
	 * @throws IOException 
	 */
	@Override
	public ScoreCapDto getScoreCapByCondition(String condition) throws IOException {
		
		logger.info("start : getScoreCapByCondition");
	
		ScoreCap scoreCaps = scoreCapRepository.findByCondition(condition);
		
		return convertEntityToDto(scoreCaps);
		
	}
	

	@Override
	public ScoreCapDto updateScoreCap(ScoreCapDto scoreCapDto) {
			
		logger.info("start : updateScoreCap");
		
		ScoreCap scoreCap = scoreCapRepository.findByCondition(scoreCapDto.getCondition());
		
		scoreCap.setTotalRiskCappedScore(scoreCapDto.getTotalRiskCappedScore());
		
		scoreCapRepository.save(scoreCap);
		
		return convertEntityToDto(scoreCap);
	
	}
	
	


	/**
	 * Method to delete score cap data
	 */
	@Override
	public void deleteScoreCap(ScoreCapDto scoreCapDto) {
		
		logger.info("start : deleteScoreCap");
		
		scoreCapRepository.deleteById(scoreCapDto.getCondition());	
		
	}
	
	private ScoreCap convertDtoToEntity(ScoreCapDto scoreCapDto) {
		return new ScoreCap(scoreCapDto.getCondition(),scoreCapDto.getTotalRiskCappedScore());
	}


	private ScoreCapDto convertEntityToDto(ScoreCap scoreCaps) {
		// TODO Auto-generated method stub
		return new ScoreCapDto(scoreCaps.getCondition(),scoreCaps.getTotalRiskCappedScore());
	}

}
