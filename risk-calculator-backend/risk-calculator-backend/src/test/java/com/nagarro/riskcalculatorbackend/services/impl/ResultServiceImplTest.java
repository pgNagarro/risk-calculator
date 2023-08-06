package com.nagarro.riskcalculatorbackend.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nagarro.riskcalculatorbackend.config.JobConfig;
import com.nagarro.riskcalculatorbackend.enums.JobStatus;
import com.nagarro.riskcalculatorbackend.models.CalculationLogic;
import com.nagarro.riskcalculatorbackend.models.CompanyDimension;
import com.nagarro.riskcalculatorbackend.models.Dimension;
import com.nagarro.riskcalculatorbackend.models.DimensionWeight;
import com.nagarro.riskcalculatorbackend.models.Result;
import com.nagarro.riskcalculatorbackend.models.ScoreLevel;
import com.nagarro.riskcalculatorbackend.repositories.ResultRepository;
import com.nagarro.riskcalculatorbackend.services.CalculationLogicService;
import com.nagarro.riskcalculatorbackend.services.CompanyDimensionService;
import com.nagarro.riskcalculatorbackend.services.DimensionWeightService;
import com.nagarro.riskcalculatorbackend.services.ScoreCapService;
import com.nagarro.riskcalculatorbackend.services.ScoreLevelService;

import java.io.IOException;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ResultServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ResultServiceImplTest {
    @MockBean
    private CalculationLogicService calculationLogicService;

    @MockBean
    private CompanyDimensionService companyDimensionService;

    @MockBean
    private DimensionWeightService dimensionWeightService;

    @MockBean
    private JobConfig jobConfig;

    @MockBean
    private ResultRepository resultRepository;

    @Autowired
    private ResultServiceImpl resultServiceImpl;

    @MockBean
    private ScoreCapService scoreCapService;

    @MockBean
    private ScoreLevelService scoreLevelService;

    /**
     * Method under test: {@link ResultServiceImpl#addResult(Result)}
     */
    @Test
    void testAddResult() {
        Result result = new Result();
        result.setCompanyName("Company Name");
        result.setTotalRiskCappedScore(10.0d);
        result.setValues(new ArrayList<>());
        when(resultRepository.save(Mockito.<Result>any())).thenReturn(result);

        Result result2 = new Result();
        result2.setCompanyName("Company Name");
        result2.setTotalRiskCappedScore(10.0d);
        result2.setValues(new ArrayList<>());
        resultServiceImpl.addResult(result2);
        verify(resultRepository).save(Mockito.<Result>any());
        assertEquals("Company Name", result2.getCompanyName());
        assertTrue(result2.getValues().isEmpty());
        assertEquals(10.0d, result2.getTotalRiskCappedScore());
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#getResult()}
     */
    @Test
    void testGetResult() {
        ArrayList<Result> resultList = new ArrayList<>();
        when(resultRepository.findAll()).thenReturn(resultList);
        List<Result> actualResult = resultServiceImpl.getResult();
        assertSame(resultList, actualResult);
        assertTrue(actualResult.isEmpty());
        verify(resultRepository).findAll();
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult() {
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(new ArrayList<>());
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(new ArrayList<>());
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(new ArrayList<>());
        doNothing().when(resultRepository).deleteAll();
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult2() {
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(new ArrayList<>());

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateResult");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(new ArrayList<>());
        doNothing().when(resultRepository).deleteAll();
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult3() {
        CalculationLogic calculationLogic = new CalculationLogic();
        calculationLogic.setElementName("start : calculateTotalRiskedCappedScore");
        calculationLogic.setFormula("start : calculateTotalRiskedCappedScore");

        ArrayList<CalculationLogic> calculationLogicList = new ArrayList<>();
        calculationLogicList.add(calculationLogic);
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(calculationLogicList);

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateResult");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(new ArrayList<>());
        doNothing().when(resultRepository).deleteAll();
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult4() {
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(new ArrayList<>());

        Dimension dimension = new Dimension();
        dimension.setDimensionName("start : calculateResult");
        dimension.setDimensionValue(42);

        ArrayList<Dimension> dimensions = new ArrayList<>();
        dimensions.add(dimension);

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateResult");
        companyDimension.setDimensions(dimensions);
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(new ArrayList<>());
        doNothing().when(resultRepository).deleteAll();
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult5() {
        CalculationLogic calculationLogic = new CalculationLogic();
        calculationLogic.setElementName("start : calculateTotalRiskedCappedScore");
        calculationLogic.setFormula("start : calculateTotalRiskedCappedScore");

        ArrayList<CalculationLogic> calculationLogicList = new ArrayList<>();
        calculationLogicList.add(calculationLogic);
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(calculationLogicList);

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateResult");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);

        DimensionWeight dimensionWeight = new DimensionWeight();
        dimensionWeight.setDimension("[(),%/*+-]");
        dimensionWeight.setWeight(100);

        ArrayList<DimensionWeight> dimensionWeightList = new ArrayList<>();
        dimensionWeightList.add(dimensionWeight);
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(dimensionWeightList);
        doNothing().when(resultRepository).deleteAll();
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult6() {
        CalculationLogic calculationLogic = new CalculationLogic();
        calculationLogic.setElementName("start : calculateTotalRiskedCappedScore");
        calculationLogic.setFormula("start : calculateTotalRiskedCappedScore");

        ArrayList<CalculationLogic> calculationLogicList = new ArrayList<>();
        calculationLogicList.add(calculationLogic);
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(calculationLogicList);

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateResult");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);

        DimensionWeight dimensionWeight = new DimensionWeight();
        dimensionWeight.setDimension("[(),%/*+-]");
        dimensionWeight.setWeight(100);

        DimensionWeight dimensionWeight2 = new DimensionWeight();
        dimensionWeight2.setDimension("start : calculateResult");
        dimensionWeight2.setWeight(3);

        ArrayList<DimensionWeight> dimensionWeightList = new ArrayList<>();
        dimensionWeightList.add(dimensionWeight2);
        dimensionWeightList.add(dimensionWeight);
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(dimensionWeightList);
        doNothing().when(resultRepository).deleteAll();
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult7() {
        CalculationLogic calculationLogic = new CalculationLogic();
        calculationLogic.setElementName("start : calculateTotalRiskedCappedScore");
        calculationLogic.setFormula("start : calculateTotalRiskedCappedScore");

        ArrayList<CalculationLogic> calculationLogicList = new ArrayList<>();
        calculationLogicList.add(calculationLogic);
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(calculationLogicList);

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateResult");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);

        DimensionWeight dimensionWeight = new DimensionWeight();
        dimensionWeight.setDimension("[(),%/*+-]");
        dimensionWeight.setWeight(100);

        DimensionWeight dimensionWeight2 = new DimensionWeight();
        dimensionWeight2.setDimension("start : calculateResult");
        dimensionWeight2.setWeight(3);

        DimensionWeight dimensionWeight3 = new DimensionWeight();
        dimensionWeight3.setDimension("start : calculateTotalRiskedCappedScore");
        dimensionWeight3.setWeight(1);

        ArrayList<DimensionWeight> dimensionWeightList = new ArrayList<>();
        dimensionWeightList.add(dimensionWeight3);
        dimensionWeightList.add(dimensionWeight2);
        dimensionWeightList.add(dimensionWeight);
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(dimensionWeightList);
        doNothing().when(resultRepository).deleteAll();
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult8() {
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(new ArrayList<>());

        Dimension dimension = new Dimension();
        dimension.setDimensionName("start : calculateResult");
        dimension.setDimensionValue(42);

        ArrayList<Dimension> dimensions = new ArrayList<>();
        dimensions.add(dimension);

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateResult");
        companyDimension.setDimensions(dimensions);
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(new ArrayList<>());
        doNothing().when(resultRepository).deleteAll();

        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel("start : calculateResult");
        scoreLevel.setScore("start : calculateResult");

        ArrayList<ScoreLevel> scoreLevelList = new ArrayList<>();
        scoreLevelList.add(scoreLevel);
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(scoreLevelList);
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult9() {
        CalculationLogic calculationLogic = mock(CalculationLogic.class);
        when(calculationLogic.getElementName()).thenReturn("Element Name");
        when(calculationLogic.getFormula()).thenReturn("Formula");
        doNothing().when(calculationLogic).setElementName(Mockito.<String>any());
        doNothing().when(calculationLogic).setFormula(Mockito.<String>any());
        calculationLogic.setElementName("start : calculateTotalRiskedCappedScore");
        calculationLogic.setFormula("start : calculateTotalRiskedCappedScore");

        CalculationLogic calculationLogic2 = new CalculationLogic();
        calculationLogic2.setElementName("42");
        calculationLogic2.setFormula("42");

        ArrayList<CalculationLogic> calculationLogicList = new ArrayList<>();
        calculationLogicList.add(calculationLogic2);
        calculationLogicList.add(calculationLogic);
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(calculationLogicList);

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateResult");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);

        DimensionWeight dimensionWeight = new DimensionWeight();
        dimensionWeight.setDimension("[(),%/*+-]");
        dimensionWeight.setWeight(100);

        ArrayList<DimensionWeight> dimensionWeightList = new ArrayList<>();
        dimensionWeightList.add(dimensionWeight);
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(dimensionWeightList);
        doNothing().when(resultRepository).deleteAll();
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(calculationLogic).getElementName();
        verify(calculationLogic).getFormula();
        verify(calculationLogic).setElementName(Mockito.<String>any());
        verify(calculationLogic).setFormula(Mockito.<String>any());
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult10() {
        CalculationLogic calculationLogic = mock(CalculationLogic.class);
        when(calculationLogic.getElementName()).thenReturn("Element Name");
        when(calculationLogic.getFormula()).thenReturn("42");
        doNothing().when(calculationLogic).setElementName(Mockito.<String>any());
        doNothing().when(calculationLogic).setFormula(Mockito.<String>any());
        calculationLogic.setElementName("start : calculateTotalRiskedCappedScore");
        calculationLogic.setFormula("start : calculateTotalRiskedCappedScore");

        ArrayList<CalculationLogic> calculationLogicList = new ArrayList<>();
        calculationLogicList.add(calculationLogic);
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(calculationLogicList);

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateResult");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);

        DimensionWeight dimensionWeight = new DimensionWeight();
        dimensionWeight.setDimension("[(),%/*+-]");
        dimensionWeight.setWeight(100);

        ArrayList<DimensionWeight> dimensionWeightList = new ArrayList<>();
        dimensionWeightList.add(dimensionWeight);
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(dimensionWeightList);

        Result result = new Result();
        result.setCompanyName("Company Name");
        result.setTotalRiskCappedScore(10.0d);
        result.setValues(new ArrayList<>());
        when(resultRepository.save(Mockito.<Result>any())).thenReturn(result);
        doNothing().when(resultRepository).deleteAll();
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(calculationLogic, atLeast(1)).getElementName();
        verify(calculationLogic, atLeast(1)).getFormula();
        verify(calculationLogic).setElementName(Mockito.<String>any());
        verify(calculationLogic).setFormula(Mockito.<String>any());
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).save(Mockito.<Result>any());
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult11() {
        CalculationLogic calculationLogic = mock(CalculationLogic.class);
        when(calculationLogic.getElementName()).thenReturn("42");
        when(calculationLogic.getFormula()).thenReturn("42");
        doNothing().when(calculationLogic).setElementName(Mockito.<String>any());
        doNothing().when(calculationLogic).setFormula(Mockito.<String>any());
        calculationLogic.setElementName("start : calculateTotalRiskedCappedScore");
        calculationLogic.setFormula("start : calculateTotalRiskedCappedScore");

        ArrayList<CalculationLogic> calculationLogicList = new ArrayList<>();
        calculationLogicList.add(calculationLogic);
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(calculationLogicList);

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateResult");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);

        DimensionWeight dimensionWeight = new DimensionWeight();
        dimensionWeight.setDimension("[(),%/*+-]");
        dimensionWeight.setWeight(100);

        ArrayList<DimensionWeight> dimensionWeightList = new ArrayList<>();
        dimensionWeightList.add(dimensionWeight);
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(dimensionWeightList);

        Result result = new Result();
        result.setCompanyName("Company Name");
        result.setTotalRiskCappedScore(10.0d);
        result.setValues(new ArrayList<>());
        when(resultRepository.save(Mockito.<Result>any())).thenReturn(result);
        doNothing().when(resultRepository).deleteAll();
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(calculationLogic, atLeast(1)).getElementName();
        verify(calculationLogic, atLeast(1)).getFormula();
        verify(calculationLogic).setElementName(Mockito.<String>any());
        verify(calculationLogic).setFormula(Mockito.<String>any());
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).save(Mockito.<Result>any());
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult12() {
        CalculationLogic calculationLogic = mock(CalculationLogic.class);
        when(calculationLogic.getElementName()).thenReturn("Element Name");
        when(calculationLogic.getFormula()).thenReturn("min");
        doNothing().when(calculationLogic).setElementName(Mockito.<String>any());
        doNothing().when(calculationLogic).setFormula(Mockito.<String>any());
        calculationLogic.setElementName("start : calculateTotalRiskedCappedScore");
        calculationLogic.setFormula("start : calculateTotalRiskedCappedScore");

        ArrayList<CalculationLogic> calculationLogicList = new ArrayList<>();
        calculationLogicList.add(calculationLogic);
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(calculationLogicList);

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateResult");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);

        DimensionWeight dimensionWeight = new DimensionWeight();
        dimensionWeight.setDimension("[(),%/*+-]");
        dimensionWeight.setWeight(100);

        ArrayList<DimensionWeight> dimensionWeightList = new ArrayList<>();
        dimensionWeightList.add(dimensionWeight);
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(dimensionWeightList);

        Result result = new Result();
        result.setCompanyName("Company Name");
        result.setTotalRiskCappedScore(10.0d);
        result.setValues(new ArrayList<>());
        when(resultRepository.save(Mockito.<Result>any())).thenReturn(result);
        doNothing().when(resultRepository).deleteAll();
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(calculationLogic, atLeast(1)).getElementName();
        verify(calculationLogic, atLeast(1)).getFormula();
        verify(calculationLogic).setElementName(Mockito.<String>any());
        verify(calculationLogic).setFormula(Mockito.<String>any());
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).save(Mockito.<Result>any());
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult13() {
        CalculationLogic calculationLogic = mock(CalculationLogic.class);
        when(calculationLogic.getElementName()).thenReturn("Element Name");
        when(calculationLogic.getFormula()).thenReturn("max");
        doNothing().when(calculationLogic).setElementName(Mockito.<String>any());
        doNothing().when(calculationLogic).setFormula(Mockito.<String>any());
        calculationLogic.setElementName("start : calculateTotalRiskedCappedScore");
        calculationLogic.setFormula("start : calculateTotalRiskedCappedScore");

        ArrayList<CalculationLogic> calculationLogicList = new ArrayList<>();
        calculationLogicList.add(calculationLogic);
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(calculationLogicList);

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateResult");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);

        DimensionWeight dimensionWeight = new DimensionWeight();
        dimensionWeight.setDimension("[(),%/*+-]");
        dimensionWeight.setWeight(100);

        ArrayList<DimensionWeight> dimensionWeightList = new ArrayList<>();
        dimensionWeightList.add(dimensionWeight);
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(dimensionWeightList);

        Result result = new Result();
        result.setCompanyName("Company Name");
        result.setTotalRiskCappedScore(10.0d);
        result.setValues(new ArrayList<>());
        when(resultRepository.save(Mockito.<Result>any())).thenReturn(result);
        doNothing().when(resultRepository).deleteAll();
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(calculationLogic, atLeast(1)).getElementName();
        verify(calculationLogic, atLeast(1)).getFormula();
        verify(calculationLogic).setElementName(Mockito.<String>any());
        verify(calculationLogic).setFormula(Mockito.<String>any());
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).save(Mockito.<Result>any());
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult14() {
        CalculationLogic calculationLogic = mock(CalculationLogic.class);
        when(calculationLogic.getElementName()).thenReturn("Element Name");
        when(calculationLogic.getFormula()).thenReturn("%.3f");
        doNothing().when(calculationLogic).setElementName(Mockito.<String>any());
        doNothing().when(calculationLogic).setFormula(Mockito.<String>any());
        calculationLogic.setElementName("start : calculateTotalRiskedCappedScore");
        calculationLogic.setFormula("start : calculateTotalRiskedCappedScore");

        ArrayList<CalculationLogic> calculationLogicList = new ArrayList<>();
        calculationLogicList.add(calculationLogic);
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(calculationLogicList);

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateResult");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);

        DimensionWeight dimensionWeight = new DimensionWeight();
        dimensionWeight.setDimension("[(),%/*+-]");
        dimensionWeight.setWeight(100);

        ArrayList<DimensionWeight> dimensionWeightList = new ArrayList<>();
        dimensionWeightList.add(dimensionWeight);
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(dimensionWeightList);
        doNothing().when(resultRepository).deleteAll();
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(calculationLogic).getElementName();
        verify(calculationLogic).getFormula();
        verify(calculationLogic).setElementName(Mockito.<String>any());
        verify(calculationLogic).setFormula(Mockito.<String>any());
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult15() {
        CalculationLogic calculationLogic = mock(CalculationLogic.class);
        when(calculationLogic.getElementName()).thenReturn("Element Name");
        when(calculationLogic.getFormula()).thenReturn("");
        doNothing().when(calculationLogic).setElementName(Mockito.<String>any());
        doNothing().when(calculationLogic).setFormula(Mockito.<String>any());
        calculationLogic.setElementName("start : calculateTotalRiskedCappedScore");
        calculationLogic.setFormula("start : calculateTotalRiskedCappedScore");

        ArrayList<CalculationLogic> calculationLogicList = new ArrayList<>();
        calculationLogicList.add(calculationLogic);
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(calculationLogicList);

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateResult");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);

        DimensionWeight dimensionWeight = new DimensionWeight();
        dimensionWeight.setDimension("[(),%/*+-]");
        dimensionWeight.setWeight(100);

        ArrayList<DimensionWeight> dimensionWeightList = new ArrayList<>();
        dimensionWeightList.add(dimensionWeight);
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(dimensionWeightList);
        doNothing().when(resultRepository).deleteAll();
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(calculationLogic).getElementName();
        verify(calculationLogic).getFormula();
        verify(calculationLogic).setElementName(Mockito.<String>any());
        verify(calculationLogic).setFormula(Mockito.<String>any());
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateResult()}
     */
    @Test
    void testCalculateResult16() {
        CalculationLogic calculationLogic = mock(CalculationLogic.class);
        when(calculationLogic.getElementName()).thenReturn("Element Name");
        when(calculationLogic.getFormula()).thenReturn("42");
        doNothing().when(calculationLogic).setElementName(Mockito.<String>any());
        doNothing().when(calculationLogic).setFormula(Mockito.<String>any());
        calculationLogic.setElementName("start : calculateTotalRiskedCappedScore");
        calculationLogic.setFormula("start : calculateTotalRiskedCappedScore");

        ArrayList<CalculationLogic> calculationLogicList = new ArrayList<>();
        calculationLogicList.add(calculationLogic);
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(calculationLogicList);

        Dimension dimension = new Dimension();
        dimension.setDimensionName("start : calculateResult");
        dimension.setDimensionValue(42);

        ArrayList<Dimension> dimensions = new ArrayList<>();
        dimensions.add(dimension);

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateResult");
        companyDimension.setDimensions(dimensions);
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);

        DimensionWeight dimensionWeight = new DimensionWeight();
        dimensionWeight.setDimension("[(),%/*+-]");
        dimensionWeight.setWeight(100);

        ArrayList<DimensionWeight> dimensionWeightList = new ArrayList<>();
        dimensionWeightList.add(dimensionWeight);
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(dimensionWeightList);

        Result result = new Result();
        result.setCompanyName("Company Name");
        result.setTotalRiskCappedScore(10.0d);
        result.setValues(new ArrayList<>());
        when(resultRepository.save(Mockito.<Result>any())).thenReturn(result);
        doNothing().when(resultRepository).deleteAll();
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        doNothing().when(jobConfig)
                .createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
        resultServiceImpl.calculateResult();
        verify(calculationLogicService).getAllCalculationLogic();
        verify(calculationLogic, atLeast(1)).getElementName();
        verify(calculationLogic, atLeast(1)).getFormula();
        verify(calculationLogic).setElementName(Mockito.<String>any());
        verify(calculationLogic).setFormula(Mockito.<String>any());
        verify(companyDimensionService, atLeast(1)).getAllCompanyDimension();
        verify(dimensionWeightService).getAllDimensionWeight();
        verify(resultRepository).save(Mockito.<Result>any());
        verify(resultRepository).deleteAll();
        verify(scoreLevelService).getAllRiskScoreLevel();
        verify(jobConfig).createAndSaveJob(Mockito.<LocalDateTime>any(), Mockito.<JobStatus>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link ResultServiceImpl#evaluateFormula(List, List, List, Map, String, int)}
     */
    @Test
    void testEvaluateFormula() throws IOException {
        ArrayList<CompanyDimension> riskScoreList = new ArrayList<>();
        ArrayList<CalculationLogic> riskCalcList = new ArrayList<>();
        ArrayList<DimensionWeight> riskDimensionList = new ArrayList<>();
        resultServiceImpl.evaluateFormula(riskScoreList, riskCalcList, riskDimensionList, new HashMap<>(), "Operations", 1);
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#evaluateFormula(List, List, List, Map, String, int)}
     */
    @Test
    void testEvaluateFormula2() throws IOException {
        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("Company Name");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> riskScoreList = new ArrayList<>();
        riskScoreList.add(companyDimension);
        ArrayList<CalculationLogic> riskCalcList = new ArrayList<>();
        ArrayList<DimensionWeight> riskDimensionList = new ArrayList<>();
        resultServiceImpl.evaluateFormula(riskScoreList, riskCalcList, riskDimensionList, new HashMap<>(), "Operations", 1);
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#evaluateFormula(List, List, List, Map, String, int)}
     */
    @Test
    void testEvaluateFormula3() throws IOException {
        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("Company Name");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        CompanyDimension companyDimension2 = new CompanyDimension();
        companyDimension2.setCompanyName("com.nagarro.riskcalculatorbackend.models.CompanyDimension");
        companyDimension2.setDimensions(new ArrayList<>());
        companyDimension2.setId(2);

        ArrayList<CompanyDimension> riskScoreList = new ArrayList<>();
        riskScoreList.add(companyDimension2);
        riskScoreList.add(companyDimension);
        ArrayList<CalculationLogic> riskCalcList = new ArrayList<>();
        ArrayList<DimensionWeight> riskDimensionList = new ArrayList<>();
        resultServiceImpl.evaluateFormula(riskScoreList, riskCalcList, riskDimensionList, new HashMap<>(), "Operations", 1);
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

  



    /**
     * Method under test: {@link ResultServiceImpl#evaluateFormula(List, List, List, Map, String, int)}
     */
    @Test
    void testEvaluateFormula8() throws IOException {
        ArrayList<CompanyDimension> riskScoreList = new ArrayList<>();
        ArrayList<CalculationLogic> riskCalcList = new ArrayList<>();

        DimensionWeight dimensionWeight = new DimensionWeight();
        dimensionWeight.setDimension("Dimension");
        dimensionWeight.setWeight(3);

        ArrayList<DimensionWeight> riskDimensionList = new ArrayList<>();
        riskDimensionList.add(dimensionWeight);
        resultServiceImpl.evaluateFormula(riskScoreList, riskCalcList, riskDimensionList, new HashMap<>(), "Operations", 1);
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#evaluateFormula(List, List, List, Map, String, int)}
     */
    @Test
    void testEvaluateFormula9() throws IOException {
        ArrayList<CompanyDimension> riskScoreList = new ArrayList<>();
        ArrayList<CalculationLogic> riskCalcList = new ArrayList<>();

        DimensionWeight dimensionWeight = new DimensionWeight();
        dimensionWeight.setDimension("Dimension");
        dimensionWeight.setWeight(3);

        DimensionWeight dimensionWeight2 = new DimensionWeight();
        dimensionWeight2.setDimension("com.nagarro.riskcalculatorbackend.models.DimensionWeight");
        dimensionWeight2.setWeight(1);

        ArrayList<DimensionWeight> riskDimensionList = new ArrayList<>();
        riskDimensionList.add(dimensionWeight2);
        riskDimensionList.add(dimensionWeight);
        resultServiceImpl.evaluateFormula(riskScoreList, riskCalcList, riskDimensionList, new HashMap<>(), "Operations", 1);
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#evaluateFormula(List, List, List, Map, String, int)}
     */
    @Test
    void testEvaluateFormula10() throws IOException {
        CompanyDimension companyDimension = mock(CompanyDimension.class);
        doNothing().when(companyDimension).setCompanyName(Mockito.<String>any());
        doNothing().when(companyDimension).setDimensions(Mockito.<List<Dimension>>any());
        doNothing().when(companyDimension).setId(anyInt());
        companyDimension.setCompanyName("Company Name");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> riskScoreList = new ArrayList<>();
        riskScoreList.add(companyDimension);
        ArrayList<CalculationLogic> riskCalcList = new ArrayList<>();
        ArrayList<DimensionWeight> riskDimensionList = new ArrayList<>();
        resultServiceImpl.evaluateFormula(riskScoreList, riskCalcList, riskDimensionList, new HashMap<>(), "Operations", 1);
        verify(companyDimension).setCompanyName(Mockito.<String>any());
        verify(companyDimension).setDimensions(Mockito.<List<Dimension>>any());
        verify(companyDimension).setId(anyInt());
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#insertValuesInResultTable(Map, List, int)}
     */
    @Test
    void testInsertValuesInResultTable6() {
        Result result = new Result();
        result.setCompanyName("Company Name");
        result.setTotalRiskCappedScore(10.0d);
        result.setValues(new ArrayList<>());
        when(resultRepository.save(Mockito.<Result>any())).thenReturn(result);

        HashMap<String, Double> elementResultMap = new HashMap<>();
        elementResultMap.put("total_risk_capped_score", 10.0d);
        CompanyDimension companyDimension = mock(CompanyDimension.class);
        when(companyDimension.getCompanyName()).thenReturn("Company Name");
        doNothing().when(companyDimension).setCompanyName(Mockito.<String>any());
        doNothing().when(companyDimension).setDimensions(Mockito.<List<Dimension>>any());
        doNothing().when(companyDimension).setId(anyInt());
        companyDimension.setCompanyName("Company Name");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> riskScoreList = new ArrayList<>();
        riskScoreList.add(companyDimension);
        resultServiceImpl.insertValuesInResultTable(elementResultMap, riskScoreList, 0);
        verify(resultRepository).save(Mockito.<Result>any());
        verify(companyDimension).getCompanyName();
        verify(companyDimension).setCompanyName(Mockito.<String>any());
        verify(companyDimension).setDimensions(Mockito.<List<Dimension>>any());
        verify(companyDimension).setId(anyInt());
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateTotalRiskedCappedScore()}
     */
    @Test
    void testCalculateTotalRiskedCappedScore() throws IOException {
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(new ArrayList<>());
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        assertTrue(resultServiceImpl.calculateTotalRiskedCappedScore().isEmpty());
        verify(companyDimensionService).getAllCompanyDimension();
        verify(scoreLevelService).getAllRiskScoreLevel();
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateTotalRiskedCappedScore()}
     */
    @Test
    void testCalculateTotalRiskedCappedScore2() throws IOException {
        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateTotalRiskedCappedScore");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        Map<String, Integer> actualCalculateTotalRiskedCappedScoreResult = resultServiceImpl
                .calculateTotalRiskedCappedScore();
        assertEquals(1, actualCalculateTotalRiskedCappedScoreResult.size());
        Integer expectedGetResult = new Integer(100);
        assertEquals(expectedGetResult,
                actualCalculateTotalRiskedCappedScoreResult.get("start : calculateTotalRiskedCappedScore"));
        verify(companyDimensionService).getAllCompanyDimension();
        verify(scoreLevelService).getAllRiskScoreLevel();
    }

    /**
     * Method under test: {@link ResultServiceImpl#calculateTotalRiskedCappedScore()}
     */
    @Test
    void testCalculateTotalRiskedCappedScore3() throws IOException {
        Dimension dimension = new Dimension();
        dimension.setDimensionName("start : calculateTotalRiskedCappedScore");
        dimension.setDimensionValue(42);

        ArrayList<Dimension> dimensions = new ArrayList<>();
        dimensions.add(dimension);

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : calculateTotalRiskedCappedScore");
        companyDimension.setDimensions(dimensions);
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(companyDimensionList);
        when(scoreLevelService.getAllRiskScoreLevel()).thenReturn(new ArrayList<>());
        Map<String, Integer> actualCalculateTotalRiskedCappedScoreResult = resultServiceImpl
                .calculateTotalRiskedCappedScore();
        assertEquals(1, actualCalculateTotalRiskedCappedScoreResult.size());
        Integer expectedGetResult = new Integer(100);
        assertEquals(expectedGetResult,
                actualCalculateTotalRiskedCappedScoreResult.get("start : calculateTotalRiskedCappedScore"));
        verify(companyDimensionService).getAllCompanyDimension();
        verify(scoreLevelService).getAllRiskScoreLevel();
    }

  

   


    /**
     * Method under test: {@link ResultServiceImpl#compareScore(Map, List, List, int)}
     */
    @Test
    void testCompareScore() {
        HashMap<String, Integer> level = new HashMap<>();
        ArrayList<ScoreLevel> riskScoreLevelList = new ArrayList<>();
        resultServiceImpl.compareScore(level, riskScoreLevelList, new ArrayList<>(), 3);
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#compareScore(Map, List, List, int)}
     */
    @Test
    void testCompareScore2() {
        HashMap<String, Integer> level = new HashMap<>();

        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel("Level");
        scoreLevel.setScore("Score");

        ArrayList<ScoreLevel> riskScoreLevelList = new ArrayList<>();
        riskScoreLevelList.add(scoreLevel);
        resultServiceImpl.compareScore(level, riskScoreLevelList, new ArrayList<>(), 3);
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#compareScore(Map, List, List, int)}
     */
    @Test
    void testCompareScore3() {
        HashMap<String, Integer> level = new HashMap<>();

        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel("Level");
        scoreLevel.setScore("Score");

        ScoreLevel scoreLevel2 = new ScoreLevel();
        scoreLevel2.setLevel("com.nagarro.riskcalculatorbackend.models.ScoreLevel");
        scoreLevel2.setScore("com.nagarro.riskcalculatorbackend.models.ScoreLevel");

        ArrayList<ScoreLevel> riskScoreLevelList = new ArrayList<>();
        riskScoreLevelList.add(scoreLevel2);
        riskScoreLevelList.add(scoreLevel);
        resultServiceImpl.compareScore(level, riskScoreLevelList, new ArrayList<>(), 3);
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#compareScore(Map, List, List, int)}
     */
    @Test
    void testCompareScore4() {
        HashMap<String, Integer> level = new HashMap<>();
        ArrayList<ScoreLevel> riskScoreLevelList = new ArrayList<>();

        ArrayList<Integer> values = new ArrayList<>();
        values.add(2);
        resultServiceImpl.compareScore(level, riskScoreLevelList, values, 3);
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#compareScore(Map, List, List, int)}
     */
    @Test
    void testCompareScore5() {
        HashMap<String, Integer> level = new HashMap<>();
        ArrayList<ScoreLevel> riskScoreLevelList = new ArrayList<>();

        ArrayList<Integer> values = new ArrayList<>();
        values.add(1);
        values.add(2);
        resultServiceImpl.compareScore(level, riskScoreLevelList, values, 3);
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }

    /**
     * Method under test: {@link ResultServiceImpl#compareScore(Map, List, List, int)}
     */
    @Test
    void testCompareScore6() {
        HashMap<String, Integer> level = new HashMap<>();
        ScoreLevel scoreLevel = mock(ScoreLevel.class);
        doNothing().when(scoreLevel).setLevel(Mockito.<String>any());
        doNothing().when(scoreLevel).setScore(Mockito.<String>any());
        scoreLevel.setLevel("Level");
        scoreLevel.setScore("Score");

        ArrayList<ScoreLevel> riskScoreLevelList = new ArrayList<>();
        riskScoreLevelList.add(scoreLevel);
        resultServiceImpl.compareScore(level, riskScoreLevelList, new ArrayList<>(), 3);
        verify(scoreLevel).setLevel(Mockito.<String>any());
        verify(scoreLevel).setScore(Mockito.<String>any());
        assertTrue(resultServiceImpl.getResult().isEmpty());
    }


    

    /**
     * Method under test: {@link ResultServiceImpl#compareScore(Map, List, List, int)}
     */
    @Test
    void testCompareScore9() {
        HashMap<String, Integer> level = new HashMap<>();
        ScoreLevel scoreLevel = mock(ScoreLevel.class);
        when(scoreLevel.getScore()).thenReturn("42");
        doNothing().when(scoreLevel).setLevel(Mockito.<String>any());
        doNothing().when(scoreLevel).setScore(Mockito.<String>any());
        scoreLevel.setLevel("Level");
        scoreLevel.setScore("Score");

        ArrayList<ScoreLevel> riskScoreLevelList = new ArrayList<>();
        riskScoreLevelList.add(scoreLevel);

        ArrayList<Integer> values = new ArrayList<>();
        values.add(2);
        resultServiceImpl.compareScore(level, riskScoreLevelList, values, 3);
        verify(scoreLevel).getScore();
        verify(scoreLevel).setLevel(Mockito.<String>any());
        verify(scoreLevel).setScore(Mockito.<String>any());
    }

    
}

