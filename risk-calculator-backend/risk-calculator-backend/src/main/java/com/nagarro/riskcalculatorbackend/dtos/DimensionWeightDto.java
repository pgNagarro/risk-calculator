package com.nagarro.riskcalculatorbackend.dtos;


import java.util.List;

import com.nagarro.riskcalculatorbackend.models.DimensionWeight;

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

	List<DimensionWeight> dimensionWeights;

}
