package com.nagarro.riskcalculatorbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.riskcalculatorbackend.models.DimensionWeight;


/**
 * Interface for Dimension weight repo
 * @author parasgautam
 *
 */
public interface DimensionWeightRepository  extends JpaRepository<DimensionWeight,String>{
	

	public DimensionWeight findByDimension(String name);
		

}
