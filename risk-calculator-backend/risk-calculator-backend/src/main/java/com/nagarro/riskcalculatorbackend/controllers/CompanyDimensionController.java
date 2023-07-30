package com.nagarro.riskcalculatorbackend.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.riskcalculatorbackend.dtos.CompanyDimensionDto;
import com.nagarro.riskcalculatorbackend.models.CompanyDimension;
import com.nagarro.riskcalculatorbackend.services.CompanyDimensionService;




/**
 * Controller class for Company Dimension to handle operations
 * on company dimension data
 * 
 * @author parasgautam
 * 
 */
@RestController
@CrossOrigin(origins = "*")
public class CompanyDimensionController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyDimensionController.class);

    @Autowired
    private CompanyDimensionService companyDimensionService;

    /**
     * Method to get all data from the company dimension table.
     *
     * @return ResponseEntity with a list of CompanyDimension objects
     */
    @GetMapping("/company-dimension")
    public ResponseEntity<List<CompanyDimension>> getCompanyDimension() {

        logger.info("Request received for fetching company dimensions");

        List<CompanyDimension> companyDimensionList = companyDimensionService.getAllCompanyDimension();

        logger.info("Request completed for fetching all the company dimensions");

        return ResponseEntity.ok(companyDimensionList);
    }

    /**
     * Method for adding company dimension data.
     *
     * @param companyDimensionDto The CompanyDimensionDto object to be added
     * @return ResponseEntity with the newly added CompanyDimensionDto object or
     *         HttpStatus.NOT_ACCEPTABLE if there was an error adding the data
     * @throws IOException
     */
    @PostMapping("/add-company-dimension")
    public ResponseEntity<CompanyDimensionDto> saveCompanyDimension(@RequestBody CompanyDimensionDto companyDimensionDto) throws IOException {

        logger.info("Request received for adding company dimension");

        try {
            companyDimensionService.saveCompanyDimension(companyDimensionDto);

            logger.info("Request completed for adding company dimension");

            return ResponseEntity.ok(companyDimensionDto);
        } catch (IOException e) {
            logger.error("Error while saving company dimension data: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * Method for getting single company dimension data by company name.
     *
     * @param companyName The company name for which to fetch the CompanyDimensionDto
     * @return ResponseEntity with the CompanyDimensionDto object for the given company name
     */
    @GetMapping("/company-dimension/{companyName}")
    public ResponseEntity<CompanyDimensionDto> getCompanyDimensionByCompanyName(@PathVariable String companyName) {

        logger.info("Request received for getting single company dimension");

        CompanyDimensionDto companyDimensionDto;

        try {

            companyDimensionDto = companyDimensionService.getCompanyDimensionByCompanyName(companyName);

            logger.info("Request completed for getting single company dimension");

            return ResponseEntity.ok(companyDimensionDto);

        } catch (IOException e) {

            logger.error("Error while fetching company dimension data: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }

    /**
     * Method for updating company dimension data.
     *
     * @param companyName       The company name to be updated
     * @param dimensionDetails The updated CompanyDimensionDto object
     * @return ResponseEntity with the updated CompanyDimensionDto object or
     *         HttpStatus.NOT_ACCEPTABLE if there was an error updating the data
     */
    @PutMapping("/company-dimension/{companyName}")
    public ResponseEntity<CompanyDimensionDto> updateCompanyDimension(@PathVariable String companyName,
            @RequestBody CompanyDimensionDto dimensionDetails) {

        logger.info("Request received for updating company dimension data");

        CompanyDimensionDto updatedDimension = companyDimensionService.updateCompanyDimension(dimensionDetails);

		logger.info("Request completed for updating company dimension data");

		return ResponseEntity.ok(updatedDimension);
    }

    /**
     * Method for deleting company dimension data.
     *
     * @param companyName The company name for which to delete the CompanyDimensionDto
     * @return ResponseEntity with a Map indicating whether the deletion was successful or not
     */
    @DeleteMapping("/company-dimension/{companyName}")
    public ResponseEntity<Map<String, Boolean>> deleteDimension(@PathVariable String companyName) {

        logger.info("Request received for deleting company dimension");

        try {
            CompanyDimensionDto companyDimensionDto = companyDimensionService.getCompanyDimensionByCompanyName(companyName);

            companyDimensionService.deleteCompanyDimension(companyDimensionDto);

            Map<String, Boolean> response = new HashMap<>();
            response.put("Deleted", Boolean.TRUE);

            logger.info("Request completed for deleting company dimension data");

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            logger.error("Error while deleting company dimension data: " + e.getMessage());
            e.printStackTrace();

            Map<String, Boolean> response = new HashMap<>();
            response.put("Unable to Delete", Boolean.FALSE);

            return ResponseEntity.ok(response);
        }
    }
}
