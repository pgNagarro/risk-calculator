package com.nagarro.riskcalculatorbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.riskcalculatorbackend.models.Result;


/**
 * Interface for Result repo
 * @author parasgautam
 *
 */
public interface ResultRepository extends JpaRepository<Result,String>{

}
