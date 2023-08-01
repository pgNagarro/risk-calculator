package com.nagarro.riskcalculatorbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.riskcalculatorbackend.models.Job;

/**
 * Interface for Job repo
 * @author parasgautam
 *
 */
public interface JobRepository extends JpaRepository<Job,Long>{

}
