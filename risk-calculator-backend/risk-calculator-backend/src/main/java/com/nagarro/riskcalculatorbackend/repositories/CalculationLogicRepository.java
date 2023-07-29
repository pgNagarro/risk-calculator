package com.nagarro.riskcalculatorbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.riskcalculatorbackend.models.CalculationLogic;


public interface CalculationLogicRepository extends JpaRepository<CalculationLogic, String>{

	public CalculationLogic findByElementName(String name);

}
