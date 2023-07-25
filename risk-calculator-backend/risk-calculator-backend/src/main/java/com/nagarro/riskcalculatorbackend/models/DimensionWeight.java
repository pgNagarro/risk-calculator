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
 * Entity class for Dimension weights
 * @author parasgautam
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="dimension_weight")
public class DimensionWeight {

	@Id
	@Column(name="dimension")
	@NotBlank(message="dimension cannot be blank")
	private String dimension;
	
	@Column(name="weight")
	private int weight;

}
