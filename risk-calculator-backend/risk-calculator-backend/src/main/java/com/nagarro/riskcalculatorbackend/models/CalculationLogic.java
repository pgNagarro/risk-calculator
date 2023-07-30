package com.nagarro.riskcalculatorbackend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for Risk calculation logic
 * @author parasgautam
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="risk_calc_logic")
public class CalculationLogic {

	@Id
	@Column(name="element_name")
	@NotBlank(message="Element name cannot be blank")
	private String elementName;
	
	@Column(name="formula")
	@NotBlank(message="Formula cannot be blank")
	private String formula;

}
