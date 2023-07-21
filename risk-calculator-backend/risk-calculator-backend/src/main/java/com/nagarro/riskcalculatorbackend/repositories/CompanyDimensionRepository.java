package com.nagarro.riskcalculatorbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.riskcalculatorbackend.models.CompanyDimension;


/**
 * Interface for company dimension repository
 * @author parasgautam
 *
 */
public interface CompanyDimensionRepository extends JpaRepository<CompanyDimension, Integer> {
	
	public List<CompanyDimension> findByCompanyName(String name);
	
	public void deleteByCompanyName(String name);

}
