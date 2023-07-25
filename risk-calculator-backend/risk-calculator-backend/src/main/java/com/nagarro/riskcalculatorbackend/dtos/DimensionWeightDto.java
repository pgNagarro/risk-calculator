package com.nagarro.riskcalculatorbackend.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for dimension weight
 * @author parasgautam
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DimensionWeightDto {

	private String dimension;
	
	private int weight;

}
