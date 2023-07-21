package com.nagarro.riskcalculatorbackend.dtos;

import java.util.List;

import com.nagarro.riskcalculatorbackend.models.Dimension;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for company dimension
 * @author parasgautam
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDimensionDto {
	
	private String companyName;

	private List<Dimension> dimensions;
	
}
