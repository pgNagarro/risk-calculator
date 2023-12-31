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


import com.nagarro.riskcalculatorbackend.dtos.ScoreLevelDto;
import com.nagarro.riskcalculatorbackend.models.ScoreLevel;
import com.nagarro.riskcalculatorbackend.services.ScoreLevelService;

/**
 * Controller class for Score level
 * 
 * @author parasgautam
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class ScoreLevelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScoreLevelController.class);

    @Autowired
    private ScoreLevelService scoreLevelService;

    /**
     * Method to get all score level data.
     *
     * @return ResponseEntity with a list of ScoreLevel objects
     */
    @GetMapping("/all-score-level")
    public ResponseEntity<List<ScoreLevel>> getRiskScoreLevel() {

        LOGGER.info("Request received for fetching all the risk score level data");

        List<ScoreLevel> scoreLevelList = scoreLevelService.getAllRiskScoreLevel();

        LOGGER.info("Request completed for fetching all the risk score level data");

        return ResponseEntity.ok(scoreLevelList);
    }

    /**
     * Method to save score level data.
     *
     * @param scoreLevelDto The ScoreLevelDto object to be saved
     * @return ResponseEntity with the newly created ScoreLevelDto object
     */
    @PostMapping("/add-score-level")
    public ResponseEntity<ScoreLevelDto> saveRiskScoreLevel(@RequestBody ScoreLevelDto scoreLevelDto) {

        LOGGER.info("Request received for adding risk score level data");

        ScoreLevelDto newRiskScoreLevelDto = scoreLevelService.saveScoreLevel(scoreLevelDto);

        LOGGER.info("Request completed for adding risk score level data");

        return ResponseEntity.ok(newRiskScoreLevelDto);
    }

    /**
     * Method for getting single score level data by score.
     *
     * @param score The score for which to fetch the ScoreLevelDto
     * @return ResponseEntity with the ScoreLevelDto object for the given score
     */
    @GetMapping("/score-level/{score}")
    public ResponseEntity<ScoreLevelDto> getRiskScoreLevelByScore(@PathVariable String score) {

        LOGGER.info("Request received for getting single risk score level data");

        ScoreLevelDto scoreLevelDto;

        try {
            scoreLevelDto = scoreLevelService.getScoreLevelByScore(score);

            LOGGER.info("Request completed for getting single risk score level data");

            return ResponseEntity.ok(scoreLevelDto);

        } catch (IOException e) {

            LOGGER.error("Error while fetching risk score level data: " + e.getMessage());
            return ResponseEntity.ok(null);
        }
    }

    /**
     * Method for updating score level data.
     *
     * @param score                The score to be updated
     * @param riskScoreLevelDetails The updated ScoreLevelDto object
     * @return ResponseEntity with the updated ScoreLevelDto object
     */
    @PutMapping("/score-level/{score}")
    public ResponseEntity<ScoreLevelDto> updateRiskScoreLevel(@PathVariable String score, @RequestBody ScoreLevelDto riskScoreLevelDetails) {

        LOGGER.info("Request received for updating risk score level");

        ScoreLevelDto scoreLevelDto = scoreLevelService.updateScoreLevel(riskScoreLevelDetails);

        LOGGER.info("Request completed for updating risk score level");

        return ResponseEntity.ok(scoreLevelDto);
    }

    /**
     * Method for deleting score level data.
     *
     * @param score The score for which to delete the ScoreLevelDto
     * @return ResponseEntity with a Map indicating whether the deletion was successful or not
     */
    @DeleteMapping("/score-level/{score}")
    public ResponseEntity<Map<String, Boolean>> deleteRiskScoreLevel(@PathVariable String score) {

        LOGGER.info("Request received for deleting risk score level");

        ScoreLevelDto scoreLevelDto;

        try {
            scoreLevelDto = scoreLevelService.getScoreLevelByScore(score);

            scoreLevelService.deleteScoreLevel(scoreLevelDto);

            Map<String, Boolean> response = new HashMap<>();
            response.put("Deleted", Boolean.TRUE);

            LOGGER.info("Request completed for deleting risk score level");

            return ResponseEntity.ok(response);

        } catch (IOException e) {

            LOGGER.error("Error while deleting risk score level: " + e.getMessage());

            Map<String, Boolean> response = new HashMap<>();
            response.put("Unable to Delete", Boolean.FALSE);

            return ResponseEntity.ok(response);
        }
    }
}