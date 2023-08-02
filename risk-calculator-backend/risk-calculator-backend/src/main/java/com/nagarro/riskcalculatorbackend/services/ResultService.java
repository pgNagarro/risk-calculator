package com.nagarro.riskcalculatorbackend.services;

import java.io.IOException;
import java.util.List;

import com.nagarro.riskcalculatorbackend.models.Result;


/**
 * Interface for Result Service.
 * This interface defines the contract for managing Result objects.
 * It provides methods to retrieve, add, and calculate results.
 * Implementing classes should provide concrete implementations of these methods.
 * 
 * @author parasgautam
 */
public interface ResultService {
	
	/**
	 * Retrieves a list of Result objects.
	 *
	 * @return A list of Result objects.
	 */
	List<Result> getResult();
	
	/**
	 * Adds a new Result object to the data store.
	 *
	 * @param result The Result object to be added.
	 */
	void addResult(Result result);
	
	/**
	 * Calculates the result and performs necessary operations.
	 * This method may involve complex calculations and interactions with external systems.
	 *
	 * @throws IOException If there is an I/O error during the calculation process.
	 */
	void calculateResult() throws IOException;
}