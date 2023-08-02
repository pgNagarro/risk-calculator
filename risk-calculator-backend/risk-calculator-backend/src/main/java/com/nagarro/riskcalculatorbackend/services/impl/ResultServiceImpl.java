package com.nagarro.riskcalculatorbackend.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;
import com.nagarro.riskcalculatorbackend.config.JobConfig;
import com.nagarro.riskcalculatorbackend.enums.JobStatus;
import com.nagarro.riskcalculatorbackend.models.CalculationLogic;
import com.nagarro.riskcalculatorbackend.models.CompanyDimension;
import com.nagarro.riskcalculatorbackend.models.DimensionWeight;
import com.nagarro.riskcalculatorbackend.models.OutputValues;
import com.nagarro.riskcalculatorbackend.models.Result;
import com.nagarro.riskcalculatorbackend.models.ScoreCap;
import com.nagarro.riskcalculatorbackend.models.ScoreLevel;
import com.nagarro.riskcalculatorbackend.repositories.ResultRepository;
import com.nagarro.riskcalculatorbackend.services.CalculationLogicService;
import com.nagarro.riskcalculatorbackend.services.CompanyDimensionService;
import com.nagarro.riskcalculatorbackend.services.DimensionWeightService;
import com.nagarro.riskcalculatorbackend.services.ResultService;
import com.nagarro.riskcalculatorbackend.services.ScoreCapService;
import com.nagarro.riskcalculatorbackend.services.ScoreLevelService;

/**
 * Service Implementation Class for Result Service
 * @author parasgautam
 *
 */
@Service
public class ResultServiceImpl implements ResultService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResultServiceImpl.class);

	@Autowired
	private ResultRepository resultRepository;

	@Autowired
	private CompanyDimensionService companyDimensionService;

	@Autowired
	private CalculationLogicService calculationLogicService;

	@Autowired
	private DimensionWeightService dimensionWeightService;

	@Autowired
	private ScoreLevelService scoreLevelService;

	@Autowired
	private ScoreCapService scoreCapService;
	
	private JobConfig jobConfig;

    @Autowired
    public ResultServiceImpl(JobConfig jobConfig) {
        this.jobConfig = jobConfig;
    }

	/**
	 * Method to add result to repo
	 */
	@Override
	public void addResult(Result result) {
		resultRepository.save(result);
	}
	
	/**
	 * Method to get result
	 */
	@Override
	public List<Result> getResult() {
		return resultRepository.findAll();
	}

	/**
	 * Method to calculate result
	 */
	@Override
	@Scheduled(fixedRate = 900000)
	public void calculateResult() {
		
		LOGGER.info("start : calculateResult");
		
		resultRepository.deleteAll();
	
		
		try {
			
			List<CompanyDimension> riskScoreList = companyDimensionService.getAllCompanyDimension();
			List<CalculationLogic> riskCalcList = calculationLogicService.getAllCalculationLogic();
			List<DimensionWeight> riskDimensionList = dimensionWeightService.getAllDimensionWeight();
			
			Map<String, Integer> totalCappedScoreMap = calculateTotalRiskedCappedScore();
			
			String operations = "[(),%/*+-]";
			
			if( riskScoreList.isEmpty() || riskCalcList.isEmpty() || riskDimensionList.isEmpty() || totalCappedScoreMap.isEmpty()) {
				LOGGER.error("One or multiple tables are empty in database");
				throw new IOException("One or multiple tables are empty in database");
			}
	
			for (int m = 0; m < riskScoreList.size(); m++) { // for each company and for its values
				
				Map<String, Double> elementResultMap = new HashMap<>(); // stores formula element name and its
																			// corresponding resulting value

				elementResultMap.put("total_risk_capped_score",
						(double)totalCappedScoreMap.get(riskScoreList.get(m).getCompanyName()));
				
				for (int i = 0; i < 10; i++) { // for evaluating values if missing then skipping					
					evaluateFormula(riskScoreList,riskCalcList,riskDimensionList,elementResultMap,operations,m);
				}
				
				insertValuesInResultTable(elementResultMap, riskScoreList, m);
			}
			jobConfig.createAndSaveJob(new Date(),JobStatus.SUCCESSFULL, "Job executed with no errors");
		} catch (Exception e) {
			
			jobConfig.createAndSaveJob(new Date(),JobStatus.FAILED, "Formula is invalid or There is an inappropriate calculation ");
			e.printStackTrace();
		}
	}
	

	/**
	 * Method for formula evaluation
	 * @param riskScoreList
	 * @param riskCalcList
	 * @param riskDimensionList
	 * @param elementResultMap
	 * @param operations
	 * @param m
	 * @throws IOException
	 */
	public void evaluateFormula(List<CompanyDimension> riskScoreList,List<CalculationLogic> riskCalcList,List<DimensionWeight> riskDimensionList, Map<String, Double> elementResultMap, String operations,int m) throws IOException{
		
		for (int j = 0; j < riskCalcList.size(); j++) {

			String elementName = riskCalcList.get(j).getElementName();
			String formula = riskCalcList.get(j).getFormula();

			String[] formula_array = formula.split(operations);
			
			double formulaResultValue = -1;
			int flag = -1;

			DoubleEvaluator eval = new DoubleEvaluator();
			StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
				
			if(Arrays.stream(formula_array).anyMatch("min"::equals) || Arrays.stream(formula_array).anyMatch("max"::equals)) {
				
				int flag2 = -1, flag3=-1;
				double value1=0;
				double value2=0;
				for(int i=0;i<formula_array.length;i++) {	
					
					if(formula_array[i].equals("min") || formula_array[i].equals("max") ) {
						
						for (int l = 0; l < riskScoreList.get(m).getDimensions().size(); l++) {
							if ((i+1)<formula_array.length && formula_array[i+1].equals(riskScoreList.get(m).getDimensions().get(l).getDimensionName())) {
								value1 = riskScoreList.get(m).getDimensions().get(l).getDimensionValue();
								flag2=0;
							}
						}
						if((i+1)<formula_array.length && elementResultMap.containsKey(formula_array[i+1])){
							value1=elementResultMap.get(formula_array[i+1]);
							flag2=0;
						}
						
						for (int l = 0; l < riskScoreList.get(m).getDimensions().size(); l++) {
							if ((i+2)<formula_array.length && formula_array[i+2].equals(riskScoreList.get(m).getDimensions().get(l).getDimensionName())) {
								value2 = riskScoreList.get(m).getDimensions().get(l).getDimensionValue();
								flag3=0;
							}
						}
						if((i+2)<formula_array.length && elementResultMap.containsKey(formula_array[i+2])){
							value2=elementResultMap.get(formula_array[i+2]);
							flag3=0;
						}
						double minMaxResult=0;
						if(flag2==0 && flag3==0) {
							
							if(formula_array[i].equals("min")) {
								minMaxResult = Math.min(value1,value2);
								variables.set("min("+formula_array[i+1]+","+formula_array[i+2]+")", minMaxResult);
								
							}
							if(formula_array[i].equals("max")) {
								minMaxResult = Math.max(value1,value2);
								variables.set("max("+formula_array[i+1]+","+formula_array[i+2]+")", minMaxResult);
							}
							
							for (int k = 0; k < formula_array.length; k++) {
								// check if value is present or not in dimension table
								if (elementResultMap.containsKey(formula_array[k])) {
									variables.set(formula_array[k], elementResultMap.get(formula_array[k]));
								} else {

									for (int l = 0; l < riskScoreList.get(m).getDimensions().size(); l++) {
										if (formula_array[k]
												.equals(riskScoreList.get(m).getDimensions().get(l).getDimensionName())) {
											variables.set(formula_array[k], Double.valueOf(
													riskScoreList.get(m).getDimensions().get(l).getDimensionValue()));
											flag = 0;
								
										}
									}

									for (int p = 0; p < riskDimensionList.size(); p++) {
										if (formula_array[k].equals(riskDimensionList.get(p).getDimension())) {
											variables.set(riskDimensionList.get(p).getDimension()+" weight",
													Double.valueOf(riskDimensionList.get(p).getWeight() * 0.01f));
											flag = 0;
											
										}
									}
								}
							}
						}
					}
				}
				
				if( flag2==-1 || flag3==-1 ) {
					continue;
				}
			}
			else {
				for (int k = 0; k < formula_array.length; k++) {
					// check if value is present or not in dimension table
					if (elementResultMap.containsKey(formula_array[k])) {
						variables.set(formula_array[k], elementResultMap.get(formula_array[k]));
					} else {

						for (int l = 0; l < riskScoreList.get(m).getDimensions().size(); l++) {
							if (formula_array[k]
									.equals(riskScoreList.get(m).getDimensions().get(l).getDimensionName())) {
								variables.set(formula_array[k], Double.valueOf(
										riskScoreList.get(m).getDimensions().get(l).getDimensionValue()));
								flag = 0;
								break;
							}
						}

						for (int p = 0; p < riskDimensionList.size(); p++) {
							if (formula_array[k].equals(riskDimensionList.get(p).getDimension())) {
								variables.set(riskDimensionList.get(p).getDimension()+" weight",
										Double.valueOf(riskDimensionList.get(p).getWeight() * 0.01f));
								flag = 0;
								break;
							}
						}
					}
				}
			}
			
			try {
		        if (formulaResultValue == -1 || flag == 1) {
		            Double result = eval.evaluate(formula, variables);
		            elementResultMap.put(elementName, result);
		        }
		    } catch (Exception e) {
		        // Throw a custom EvaluationException if there's an error in evaluation
		        throw new IOException("Error in evaluating formula: " + formula);
		    }
		} 
	}
	
	// Method to insert calculated values from risk calc logic to output table
	
		public void insertValuesInResultTable(Map<String, Double> elementResultMap, List<CompanyDimension> riskScoreList,
				int companyRiskScore) {
			Result result = new Result();
			List<OutputValues> outputValuesList = new ArrayList<>();
			result.setCompanyName(riskScoreList.get(companyRiskScore).getCompanyName());
			result.setTotalRiskCappedScore(elementResultMap.get("total_risk_capped_score"));

			for (Map.Entry<String, Double> entry : elementResultMap.entrySet()) {
				OutputValues outputValues = new OutputValues(entry.getKey(), Double.parseDouble(String.format("%.3f",entry.getValue())));
				outputValuesList.add(outputValues);
			}
			result.setValues(outputValuesList);
			addResult(result);
		}
		
		
	// Method to calculate total risk capped score
		
	public Map<String, Integer> calculateTotalRiskedCappedScore() throws IOException{
		
		LOGGER.info("start : calculateTotalRiskedCappedScore");
		
		Map<String, Integer> map = new HashMap<>();

		List<CompanyDimension> riskScoreList = companyDimensionService.getAllCompanyDimension();
		List<ScoreLevel> riskScoreLevelList = scoreLevelService.getAllRiskScoreLevel();


		for (int i = 0; i < riskScoreList.size(); i++) {

			int totalRiskCappedScore = 100;
			Map<String, Integer> level = new HashMap<>();

			for (int j = 0; j < riskScoreList.get(i).getDimensions().size(); j++) {
				List<Integer> values = new ArrayList<>();
				values.add(riskScoreList.get(i).getDimensions().get(j).getDimensionValue());
				compareScore(level,riskScoreLevelList,values,j);
			}
			for (Map.Entry<String, Integer> entry : level.entrySet()) {	
				
				String s = numberToWord(entry.getValue()) + " \"" + entry.getKey() + "\"";
				
				ScoreCap scoreCap = scoreCapService.getScoreCap(s);
				
				if (scoreCap != null) {
					totalRiskCappedScore = scoreCap.getTotalRiskCappedScore();
					break;
				}
				
			}
			map.put(riskScoreList.get(i).getCompanyName(), totalRiskCappedScore);
		}
		return map;
	}
	
	private String numberToWord(int number) {
		
		String[] single_digits = new String[]{"zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
	    String[] two_digits = new String[]{"", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	    String[] tens_multiple = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	    
        if (number < 10) {
            return single_digits[number];
        } else if (number >= 10 && number < 20) {
            return two_digits[number % 10 + 1];
        } else if (number >= 20 && number < 100) {
            return tens_multiple[number / 10] + single_digits[number % 10];
        } else if (number == 100) {
            return "One Hundred";
        } else {
            return "Number out of range (0 to 100)";
        }
    }
	
	// Method to get score level from risk score level table for each value of dimension
	public void compareScore(Map<String,Integer> level,List<ScoreLevel> riskScoreLevelList, List<Integer> values, int j) {
		for (int k = 0; k < values.size(); k++) {
			for (int l = 0; l < riskScoreLevelList.size(); l++) {
				String[] scores = riskScoreLevelList.get(l).getScore().split("-");
				if (values.get(k) >= Integer.parseInt(scores[0])
						&& values.get(k) <= Integer.parseInt(scores[1])) {
					level.put(riskScoreLevelList.get(l).getLevel(),
							level.getOrDefault(riskScoreLevelList.get(l).getLevel(), 0) + 1);
					break;
				}
			}
		}
	}
	
	

}
