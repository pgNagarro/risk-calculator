package com.nagarro.riskcalculatorbackend.services.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.riskcalculatorbackend.config.JobConfig;
import com.nagarro.riskcalculatorbackend.dtos.CalculationLogicDto;
import com.nagarro.riskcalculatorbackend.enums.JobStatus;
import com.nagarro.riskcalculatorbackend.models.CalculationLogic;
import com.nagarro.riskcalculatorbackend.repositories.CalculationLogicRepository;
import com.nagarro.riskcalculatorbackend.services.CalculationLogicService;

/**
 * Service Implementation Class for Risk Calculation Service
 * This class implements the CalculationLogicService interface to provide business logic for risk calculations.
 * It handles CRUD operations for CalculationLogic entities.
 * It also provides logging for important methods using SLF4J.
 * This service is annotated with @Service to be recognized as a Spring bean.
 * 
 * @author parasgautam
 */
@Service
public class CalculationLogicServiceImpl implements CalculationLogicService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculationLogicServiceImpl.class);
    
    @Autowired
    private CalculationLogicRepository calculationLogicRepository;
    
    @Autowired
    private JobConfig jobConfig;

    /**
     * Retrieves all the CalculationLogic entities from the database.
     * 
     * @return List of CalculationLogic entities.
     */
    @Override
    public List<CalculationLogic> getAllCalculationLogic() {
        LOGGER.info("start : getAllCalculationLogic");
        return calculationLogicRepository.findAll();
    }

    /**
     * Saves a new CalculationLogic entity to the database.
     * 
     * @param calculationLogicDto The DTO containing information of the CalculationLogic to be saved.
     * @return The saved CalculationLogic DTO.
     */
    @Override
    public CalculationLogicDto saveCalculationLogic(CalculationLogicDto calculationLogicDto) {
        LOGGER.info("start : saveCalculationLogic");
        try{
        	CalculationLogic calculationLogic = convertDtoToEntity(calculationLogicDto);
        	calculationLogicRepository.save(calculationLogic);
        }catch(Exception e) {
        	jobConfig.createAndSaveJob(new Date(),JobStatus.FAILED, e.toString());
        }
        return calculationLogicDto;
    }
    
    /**
     * Retrieves a CalculationLogic entity from the database based on its name.
     * 
     * @param name The name of the CalculationLogic to retrieve.
     * @return The CalculationLogic DTO with the given name.
     * @throws IOException If the CalculationLogic with the given name is not found.
     */
    @Override
    public CalculationLogicDto getCalculationLogicByName(String name) throws IOException {
        LOGGER.info("start : getCalculationLogicByName");
        CalculationLogic singleLogic = calculationLogicRepository.findByElementName(name);

        return convertEntityToDto(singleLogic);
    }
    
    /**
     * Updates an existing CalculationLogic entity in the database.
     * 
     * @param calculationLogicDto The DTO containing updated information of the CalculationLogic.
     * @return The updated CalculationLogic DTO.
     */
    @Override
    public CalculationLogicDto updateCalculationLogic(CalculationLogicDto calculationLogicDto) {
        LOGGER.info("start : updateCalculationLogic");
        CalculationLogic calculationLogic = calculationLogicRepository.findByElementName(calculationLogicDto.getElementName());
        calculationLogic.setFormula(calculationLogicDto.getFormula());
        calculationLogicRepository.save(calculationLogic);
        return convertEntityToDto(calculationLogic);
    }

    /**
     * Deletes a CalculationLogic entity from the database.
     * 
     * @param calculationLogicDto The DTO containing information of the CalculationLogic to be deleted.
     */
    @Override
    public void deleteCalculationLogic(CalculationLogicDto calculationLogicDto) {
        LOGGER.info("start : deleteCalculationLogic");
        calculationLogicRepository.deleteById(calculationLogicDto.getElementName());
    }
    
    // Utility methods to convert between DTO and entity
    
    private CalculationLogic convertDtoToEntity(CalculationLogicDto calculationLogicDto) {
        return new CalculationLogic(calculationLogicDto.getElementName(), calculationLogicDto.getFormula());
    }
    
    private CalculationLogicDto convertEntityToDto(CalculationLogic singleLogic) {
        return new CalculationLogicDto(singleLogic.getElementName(), singleLogic.getFormula());
    }

}
