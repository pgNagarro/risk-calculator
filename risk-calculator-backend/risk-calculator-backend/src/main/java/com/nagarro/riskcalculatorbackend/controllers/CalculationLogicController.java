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

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculationLogicController.class);

    @Autowired
    private CalculationLogicService calculationLogicService;

    /**
     * Method to get all risk calculation logic data.
     *
     * @return ResponseEntity with a list of CalculationLogic objects
     */
    @GetMapping("/all-calculation-logic")
    public ResponseEntity<List<CalculationLogic>> getAllRiskCalcLogic() {

        LOGGER.info("Request received for fetching all risk calculation logic data");

        List<CalculationLogic> calculationLogicList = calculationLogicService.getAllCalculationLogic();

        LOGGER.info("Request completed for fetching all risk calculation logic data");

        return ResponseEntity.ok(calculationLogicList);
    }

    /**
     * Method for saving risk calculation logic data.
     *
     * @param calculationLogicDto The CalculationLogicDto object to be saved
     * @return ResponseEntity with the newly added CalculationLogicDto object or
     *         HttpStatus.NOT_ACCEPTABLE if there was an error adding the data
     */
    @PostMapping("/add-calculation-logic")
    public ResponseEntity<CalculationLogicDto> saveRiskCalcLogic(@RequestBody CalculationLogicDto calculationLogicDto) {

        LOGGER.info("Request received for adding risk calculation logic data");

        CalculationLogicDto newCalculationLogic = calculationLogicService.saveCalculationLogic(calculationLogicDto);

		LOGGER.info("Request completed for adding risk calculation logic data");

		return ResponseEntity.ok(newCalculationLogic);
    }

    /**
     * Method for getting single risk calculation logic data by element name.
     *
     * @param elementName The element name for which to fetch the CalculationLogicDto
     * @return ResponseEntity with the CalculationLogicDto object for the given element name
     */
    @GetMapping("/calculation-logic/{elementName}")
    public ResponseEntity<CalculationLogicDto> getRiskCalcByElementName(@PathVariable String elementName) {

        LOGGER.info("Request received for getting single risk calculation logic data");

        CalculationLogicDto calculationLogicDto;

        try {
            calculationLogicDto = calculationLogicService.getCalculationLogicByName(elementName);

            LOGGER.info("Request completed for getting single risk calculation logic data");

            return ResponseEntity.ok(calculationLogicDto);

        } catch (IOException e) {
            LOGGER.error("Error while fetching risk calculation logic data: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }

    /**
     * Method for updating risk calculation logic data.
     *
     * @param elementName       The element name to be updated
     * @param riskCalcDetails The updated CalculationLogicDto object
     * @return ResponseEntity with the updated CalculationLogicDto object or
     *         HttpStatus.NOT_ACCEPTABLE if there was an error updating the data
     */
    @PutMapping("/calculation-logic/{elementName}")
    public ResponseEntity<CalculationLogicDto> updateRiskCalc(@PathVariable String elementName,
            @RequestBody CalculationLogicDto riskCalcDetails) {

        LOGGER.info("Request received for updating risk calculation logic data");

        CalculationLogicDto calculationLogicDto = calculationLogicService.updateCalculationLogic(riskCalcDetails);

		LOGGER.info("Request completed for updating risk calculation logic data");

		return ResponseEntity.ok(calculationLogicDto);
    }

    /**
     * Method for deleting risk calculation logic data.
     *
     * @param elementName The element name for which to delete the CalculationLogicDto
     * @return ResponseEntity with a Map indicating whether the deletion was successful or not
     */
    @DeleteMapping("/calculation-logic/{elementName}")
    public ResponseEntity<Map<String, Boolean>> deleteRiskCalc(@PathVariable String elementName) {

        LOGGER.info("Request received for deleting risk calculating logic data");

        try {
            CalculationLogicDto calculationLogicDto = calculationLogicService.getCalculationLogicByName(elementName);
            calculationLogicService.deleteCalculationLogic(calculationLogicDto);

            Map<String, Boolean> response = new HashMap<>();
            response.put("Deleted", Boolean.TRUE);

            LOGGER.info("Request completed for deleting risk calculating logic data");

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            LOGGER.error("Error while deleting risk calculating logic data: " + e.getMessage());
            e.printStackTrace();

            Map<String, Boolean> response = new HashMap<>();
            response.put("Unable to Delete", Boolean.FALSE);

            return ResponseEntity.ok(response);
        }
    }
}