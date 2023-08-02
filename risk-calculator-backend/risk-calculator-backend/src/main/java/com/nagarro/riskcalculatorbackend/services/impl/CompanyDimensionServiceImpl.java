package com.nagarro.riskcalculatorbackend.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.riskcalculatorbackend.dtos.CompanyDimensionDto;
import com.nagarro.riskcalculatorbackend.models.CompanyDimension;
import com.nagarro.riskcalculatorbackend.models.Dimension;
import com.nagarro.riskcalculatorbackend.repositories.CompanyDimensionRepository;
import com.nagarro.riskcalculatorbackend.services.CompanyDimensionService;

/**
 * Service Implementation Class for Company Dimension Service.
 * 
 * This class provides methods to interact with the CompanyDimension entity and
 * perform CRUD operations.
 * 
 * It implements the CompanyDimensionService interface.
 * 
 * @author parasgautam
 */
@Service
public class CompanyDimensionServiceImpl implements CompanyDimensionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDimensionServiceImpl.class);

	@Autowired
	private CompanyDimensionRepository companyDimensionRepository;

	/**
	 * Retrieves all company dimension data from the database.
	 * 
	 * @return List of CompanyDimension objects representing all the company
	 *         dimensions.
	 */
	@Override
	public List<CompanyDimension> getAllCompanyDimension() {
		LOGGER.info("Start: getAllCompanyDimension");
		List<CompanyDimension> companyDimensions = companyDimensionRepository.findAll();
		return companyDimensions;
	}

	/**
	 * Saves the given CompanyDimensionDto into the database.
	 * 
	 * @param companyDimensionDto The CompanyDimensionDto object to save.
	 * @throws IOException If an error occurs while saving the data.
	 */
	@Override
	public void saveCompanyDimension(CompanyDimensionDto companyDimensionDto) throws IOException {
		LOGGER.info("start : saveCompanyDimension");

		CompanyDimension companyDimension = convertDtoToEntity(companyDimensionDto);

		List<CompanyDimension> companyDimensions = companyDimensionRepository.findAll();

		try {
			// Condition to check if data is already present or not
			if (!checkDataIfPresent(companyDimension)) {
				// find the risk score which is present and then add dimension
				for (CompanyDimension singleCompanyDimension : companyDimensions) {

					if (singleCompanyDimension.getCompanyName().equals(companyDimension.getCompanyName())) {
						// check if the dimension is already present
						int flag = -1;
						for (int i = 0; i < singleCompanyDimension.getDimensions().size(); i++) {
							if (singleCompanyDimension.getDimensions().get(i).getDimensionName()
									.equals(companyDimension.getDimensions().get(0).getDimensionName())) {
								flag = 0;
								throw new IOException("Dimension already present. Enter new dimension or update data"); 
							}
						}

						String dname = companyDimension.getDimensions().get(0).getDimensionName();
						int dvalue = companyDimension.getDimensions().get(0).getDimensionValue();

						if (flag == -1) {
							Dimension dimension = new Dimension((int)Math.random(), dname, dvalue);
							singleCompanyDimension.getDimensions().add(dimension);
							companyDimensionRepository.save(singleCompanyDimension);
							addDimensiontoRestCompanies(singleCompanyDimension.getCompanyName(), dimension);
							break;
						}
					}
				}
			} else {
				if (companyDimensions.isEmpty()) {
					CompanyDimension newCompanyDimension = new CompanyDimension();
					newCompanyDimension.setCompanyName(companyDimension.getCompanyName());
					List<Dimension> dimensions = new ArrayList<>();
					dimensions.add(companyDimension.getDimensions().get(0));
					newCompanyDimension.setDimensions(dimensions);
					companyDimensionRepository.save(newCompanyDimension);
				} else {
					// compare dimension with present dimensions
					CompanyDimension newCompanyDimension = new CompanyDimension();
					newCompanyDimension.setCompanyName(companyDimension.getCompanyName());
					List<Dimension> dimensions = new ArrayList<>();
					int flag = -1;
					for (Dimension dimension : companyDimensions.get(0).getDimensions()) {
						if (companyDimension.getDimensions().get(0).getDimensionName().equals(dimension.getDimensionName())) {
							dimensions.add(new Dimension((int)Math.random(),dimension.getDimensionName(), companyDimension.getDimensions().get(0).getDimensionValue()));
							flag = 0;
						} else {
							dimensions.add(new Dimension((int)Math.random(),dimension.getDimensionName(), 0));
						}
					}
					if (flag == -1) {
						String dname = companyDimension.getDimensions().get(0).getDimensionName();
						int dvalue = companyDimension.getDimensions().get(0).getDimensionValue();
						for (CompanyDimension singleCompanyDimension : companyDimensions) {
							if (!(singleCompanyDimension.getCompanyName().equals(companyDimension.getCompanyName()))) {
								// check if company is present in the list
								Dimension dimension = new Dimension((int)Math.random(),dname, 0);
								singleCompanyDimension.getDimensions().add(dimension);
								companyDimensionRepository.save(singleCompanyDimension); 
							}
						}
						dimensions.add(new Dimension((int)Math.random(),dname, dvalue));
					}
					newCompanyDimension.setDimensions(dimensions);
					companyDimensionRepository.save(newCompanyDimension);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occurred while saving company dimension data: {}", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Converts a CompanyDimensionDto object to a CompanyDimension entity.
	 * 
	 * @param companyDimensionDto The CompanyDimensionDto to convert.
	 * @return The corresponding CompanyDimension entity.
	 */
	private CompanyDimension convertDtoToEntity(CompanyDimensionDto companyDimensionDto) {
		CompanyDimension companyDimension = new CompanyDimension();
		companyDimension.setCompanyName(companyDimensionDto.getCompanyName());
		List<Dimension> list = new ArrayList<>();
		list.add(companyDimensionDto.getDimensions().get(0));
		companyDimension.setDimensions(list);
		return companyDimension;
	}

	/**
	 * Retrieves the CompanyDimensionDto for the given company name.
	 * 
	 * @param companyName The name of the company.
	 * @return The CompanyDimensionDto representing the company dimension data.
	 * @throws IOException If the company data is not present in the database.
	 */
	@Override
	public CompanyDimensionDto getCompanyDimensionByCompanyName(String companyName) throws IOException {
		LOGGER.info("start : getCompanyDimensionByCompanyName");

		List<CompanyDimension> companyDimensions = companyDimensionRepository.findByCompanyName(companyName);

		if (companyDimensions.isEmpty()) {
			throw new IOException("Company data not present");
		}

		CompanyDimension companyDimension = companyDimensions.get(0);

		return convertEntityToDto(companyDimension);
	}

	/**
	 * Converts a CompanyDimension entity to a CompanyDimensionDto object.
	 * 
	 * @param companyDimension The CompanyDimension entity to convert.
	 * @return The corresponding CompanyDimensionDto object.
	 */
	private CompanyDimensionDto convertEntityToDto(CompanyDimension companyDimension) {
		return new CompanyDimensionDto(companyDimension.getCompanyName(), companyDimension.getDimensions());
	}

	/**
	 * Updates the CompanyDimension data in the database.
	 * 
	 * @param companyDimensionDto The updated CompanyDimensionDto object.
	 * @return The updated CompanyDimensionDto.
	 */
	@Override
	public CompanyDimensionDto updateCompanyDimension(CompanyDimensionDto companyDimensionDto) {
		LOGGER.info("start : updateCompanyDimension");

		List<CompanyDimension> companyDimensions = companyDimensionRepository
				.findByCompanyName(companyDimensionDto.getCompanyName());

		CompanyDimension companyDimension = companyDimensions.get(0);
		companyDimension.setCompanyName(companyDimensionDto.getCompanyName());
		companyDimension.setDimensions(companyDimensionDto.getDimensions());
		companyDimensionRepository.save(companyDimension);

		return companyDimensionDto;
	}

	/**
	 * Deletes the CompanyDimension data from the database.
	 * 
	 * @param companyDimensionDto The CompanyDimensionDto to delete.
	 */
	@Override
	public void deleteCompanyDimension(CompanyDimensionDto companyDimensionDto) {
		LOGGER.info("start : deleteCompanyDimension");

		List<CompanyDimension> companyDimensions = companyDimensionRepository
				.findByCompanyName(companyDimensionDto.getCompanyName());

		CompanyDimension companyDimension = companyDimensions.get(0);

		companyDimensionRepository.deleteById(companyDimension.getId());
	}

	/**
	 * Method for checking if company dimension data is present or not.
	 * 
	 * @param companyDimension The CompanyDimension object to check.
	 * @return True if data is present, false otherwise.
	 */
	@Override
	public boolean checkDataIfPresent(CompanyDimension companyDimension) {
		LOGGER.info("start : checkDataIfPresent");
		List<CompanyDimension> companyDimensions = companyDimensionRepository
				.findByCompanyName(companyDimension.getCompanyName());
		return companyDimensions.isEmpty();
	}

	/**
	 * Adds a new dimension to all other companies with a value of 0.
	 * 
	 * @param companyName The company name whose dimension is being added.
	 * @param dimension    The new dimension to add to other companies.
	 */
	private void addDimensiontoRestCompanies(String companyName, Dimension dimension) {
		List<CompanyDimension> riskScoreList = companyDimensionRepository.findAll();
		for (CompanyDimension companyDimension : riskScoreList) {
			if (!(companyDimension.getCompanyName().equals(companyName))) {
				Dimension newdimension = new Dimension((int)Math.random(),dimension.getDimensionName(), 0);
				companyDimension.getDimensions().add(newdimension);
				companyDimensionRepository.save(companyDimension);
			}
		}
	}
}
