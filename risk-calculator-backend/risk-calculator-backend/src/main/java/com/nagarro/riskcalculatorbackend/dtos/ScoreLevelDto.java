package com.nagarro.riskcalculatorbackend.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Score Level Dto
 * @author parasgautam
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreLevelDto {

	private String score;

	private String level;
	
}

