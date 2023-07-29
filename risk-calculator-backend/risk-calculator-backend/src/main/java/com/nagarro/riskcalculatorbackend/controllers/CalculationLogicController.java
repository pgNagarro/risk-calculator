package com.nagarro.riskcalculatorbackend.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.riskcalculatorbackend.dtos.CalculationLogicDto;
import com.nagarro.riskcalculatorbackend.models.CalculationLogic;
import com.nagarro.riskcalculatorbackend.services.CalculationLogicService;


/**
 * Controller class for risk calculation logic
 * @author parasgautam
 *
 */
@RestController
@CrossOrigin(origins = "*") 
public class CalculationLogicController {

private static final Logger logger = LoggerFactory.getLogger(CalculationLogicController.class);
	
	@Autowired
	private CalculationLogicService calculationLogicService;
	
	
	/**
	 * Method to get all risk calculation logic data
	 * @return
	 */
	@GetMapping("/all-calculation-logic")
	public ResponseEntity<List<CalculationLogic>> getAllRiskCalcLogic(){
		
		logger.info("Request received for fetching all risk calculation logic data");
		
		List<CalculationLogic> calculationLogicList = calculationLogicService.getAllCalculationLogic();
		
		logger.info("Request completed for fetching all risk calculation logic data");
		
		return ResponseEntity.ok(calculationLogicList);
		
	}
	
	/**
	 * Method for saving risk calculation logic data
	 * @param riskCalc
	 * @return
	 */
	@PostMapping("/add-calculation-logic")
	public ResponseEntity<CalculationLogicDto> saveRiskCalcLogic(@RequestBody CalculationLogicDto calculationLogicDto) {
		
		logger.info("Request received for adding risk calculation logic data");
		
		CalculationLogicDto newCalculationLogic = calculationLogicService.saveCalculationLogic(calculationLogicDto);
		
		logger.info("Request completed for adding risk calculation logic data");
		
		return ResponseEntity.ok(newCalculationLogic);
	}
	
	/**
	 * Method for getting single risk calculation logic data by element name
	 * @param elementName
	 * @return
	 */
	@GetMapping("/calculation-logic/{elementName}")
	public ResponseEntity<CalculationLogicDto> getRiskCalcByElementName(@PathVariable String elementName){
		
		logger.info("Request received for getting single risk calculation logic data");
		
		CalculationLogicDto calculationLogicDto;
		
		try {
			
			calculationLogicDto = calculationLogicService.getCalculationLogicByName(elementName);
			
			logger.info("Request completed for getting single risk calculation logic data");
			
			return ResponseEntity.ok(calculationLogicDto);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return ResponseEntity.ok(null);
			
		}
		
		
	}
	
	/**
	 * Method for updating risk calculation logic data
	 * @param elementName
	 * @param riskCalcDetails
	 * @return
	 */
	@PutMapping("/calculation-logic/{elementName}")
	public ResponseEntity<CalculationLogicDto> updateRiskCalc(@PathVariable String elementName, @RequestBody CalculationLogicDto riskCalcDetails){
		
		logger.info("Request received for updating risk calculation logic data");
		
		CalculationLogicDto calculationLogicDto = calculationLogicService.updateCalculationLogic(riskCalcDetails);
		
		logger.info("Request received for updating risk calculation logic data");
			
		return ResponseEntity.ok(calculationLogicDto);
		

	}
	
	/**
	 * Method for deleting risk calculation logic data
	 * @param elementName
	 * @return
	 */
	@DeleteMapping("/calculation-logic/{elementName}")
	public ResponseEntity<Map<String, Boolean>> deleteRiskCalc(@PathVariable String elementName){
		
		logger.info("Request received for deleting risk calculating logic data");
		
		CalculationLogicDto calculationLogicDto;
		
		try {
			
			calculationLogicDto = calculationLogicService.getCalculationLogicByName(elementName);
			calculationLogicService.deleteCalculationLogic(calculationLogicDto);
			
			Map<String,Boolean> response = new HashMap<>();
			response.put("Deleted",Boolean.TRUE);
			
			logger.info("Request completed for deleting risk calculating logic data");
			
			return ResponseEntity.ok(response);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			Map<String,Boolean> response = new HashMap<>();
			response.put("Unable to Delete",Boolean.FALSE);
			
			return ResponseEntity.ok(null);
			
		}
		
	}

}
