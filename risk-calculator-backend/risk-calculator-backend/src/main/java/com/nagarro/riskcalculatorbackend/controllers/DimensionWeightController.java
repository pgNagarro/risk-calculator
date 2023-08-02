package com.nagarro.riskcalculatorbackend.controllers;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.nagarro.riskcalculatorbackend.dtos.DimensionWeightDto;
import com.nagarro.riskcalculatorbackend.models.DimensionWeight;
import com.nagarro.riskcalculatorbackend.services.DimensionWeightService;



/**
 * Controller class for dimension weight
 * @author parasgautam
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class DimensionWeightController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DimensionWeightController.class);

    @Autowired
    private DimensionWeightService dimensionWeightService;

    /**
     * Method to get all dimension weight data.
     *
     * @return ResponseEntity with a list of DimensionWeight objects
     */
    @GetMapping("/all-dimension-weight")
    public ResponseEntity<List<DimensionWeight>> getDimensionWeight() {

        LOGGER.info("Request received for fetching all Dimension Weight data");

        List<DimensionWeight> dimensionWeightList = dimensionWeightService.getAllDimensionWeight();

        LOGGER.info("Request completed for fetching all Dimension Weight data");

        return ResponseEntity.ok(dimensionWeightList);
    }

    /**
     * Method for saving dimension weight data.
     *
     * @param dimensionWeightDto The DimensionWeightDto object to be saved
     * @return ResponseEntity with the newly created DimensionWeightDto object or
     *         HttpStatus.NOT_ACCEPTABLE if there was an error saving the data
     */
    @PostMapping("/add-dimension-weight")
    public ResponseEntity<DimensionWeightDto> saveDimensionWeightDto(@RequestBody DimensionWeightDto dimensionWeightDto) {

        LOGGER.info("Request received for Dimension Weight data");

        try {
            DimensionWeightDto newDimensionWeightDto = dimensionWeightService.saveDimensionWeight(dimensionWeightDto);

            LOGGER.info("Request completed for Dimension Weight data");

            return ResponseEntity.ok(newDimensionWeightDto);

        } catch (IOException e) {
            LOGGER.error("Error while saving Dimension Weight data: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
