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
 * Entity class for  Score Level
 * @author parasgautam
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="risk_score_level")
public class ScoreLevel {


	@Id
	@Column(name="score")
	@NotBlank(message="score cannot be blank")
	private String score;
	
	@Column(name="level")
	private String level;

}
