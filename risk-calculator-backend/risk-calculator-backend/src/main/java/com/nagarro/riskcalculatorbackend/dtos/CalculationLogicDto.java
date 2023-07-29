package com.nagarro.riskcalculatorbackend.dtos;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Risk Calculation Logic Dto
 * @author parasgautam
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculationLogicDto {
	
	@Id
	@NotBlank(message="Element name cannot be blank")
	private String elementName;

	private String formula;

}

