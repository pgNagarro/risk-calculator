package com.nagarro.riskcalculatorbackend.services.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nagarro.riskcalculatorbackend.dtos.DimensionWeightDto;
import com.nagarro.riskcalculatorbackend.models.DimensionWeight;
import com.nagarro.riskcalculatorbackend.repositories.DimensionWeightRepository;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DimensionWeightServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DimensionWeightServiceImplTest {
    @MockBean
    private DimensionWeightRepository dimensionWeightRepository;

    @Autowired
    private DimensionWeightServiceImpl dimensionWeightServiceImpl;

    /**
     * Method under test: {@link DimensionWeightServiceImpl#getAllDimensionWeight()}
     */
    @Test
    void testGetAllDimensionWeight() {
        // Arrange
        ArrayList<DimensionWeight> dimensionWeightList = new ArrayList<>();
        when(dimensionWeightRepository.findAll()).thenReturn(dimensionWeightList);

        // Act
        List<DimensionWeight> actualAllDimensionWeight = dimensionWeightServiceImpl.getAllDimensionWeight();

        // Assert
        assertSame(dimensionWeightList, actualAllDimensionWeight);
        assertTrue(actualAllDimensionWeight.isEmpty());
        verify(dimensionWeightRepository).findAll();
    }

    /**
     * Method under test: {@link DimensionWeightServiceImpl#saveDimensionWeight(DimensionWeightDto)}
     */
    @Test
    void testSaveDimensionWeight() throws IOException {
        // Arrange
        doNothing().when(dimensionWeightRepository).deleteAll();

        // Act and Assert
        assertThrows(IOException.class, () -> dimensionWeightServiceImpl.saveDimensionWeight(new DimensionWeightDto()));
        verify(dimensionWeightRepository).deleteAll();
    }

    /**
     * Method under test: {@link DimensionWeightServiceImpl#saveDimensionWeight(DimensionWeightDto)}
     */
    @Test
    void testSaveDimensionWeight2() throws IOException {
        // Arrange
        doNothing().when(dimensionWeightRepository).deleteAll();

        DimensionWeightDto dimensionWeightDto = new DimensionWeightDto();
        dimensionWeightDto.setDimensionWeights(new ArrayList<>());

        // Act and Assert
        assertSame(dimensionWeightDto, dimensionWeightServiceImpl.saveDimensionWeight(dimensionWeightDto));
        verify(dimensionWeightRepository).deleteAll();
    }

    /**
     * Method under test: {@link DimensionWeightServiceImpl#saveDimensionWeight(DimensionWeightDto)}
     */
    @Test
    void testSaveDimensionWeight3() throws IOException {
        // Arrange
        doNothing().when(dimensionWeightRepository).deleteAll();

        // Act and Assert
        assertThrows(IOException.class, () -> dimensionWeightServiceImpl.saveDimensionWeight(null));
        verify(dimensionWeightRepository).deleteAll();
    }

    /**
     * Method under test: {@link DimensionWeightServiceImpl#saveDimensionWeight(DimensionWeightDto)}
     */
    @Test
    void testSaveDimensionWeight4() throws IOException {
        // Arrange
        DimensionWeight dimensionWeight = new DimensionWeight();
        dimensionWeight.setDimension("Dimension");
        dimensionWeight.setWeight(3);
        when(dimensionWeightRepository.save(Mockito.<DimensionWeight>any())).thenReturn(dimensionWeight);
        doNothing().when(dimensionWeightRepository).deleteAll();

        DimensionWeight dimensionWeight2 = new DimensionWeight();
        dimensionWeight2.setDimension("Saving DimensionWeight entities to the database.");
        dimensionWeight2.setWeight(3);

        ArrayList<DimensionWeight> dimensionWeights = new ArrayList<>();
        dimensionWeights.add(dimensionWeight2);

        DimensionWeightDto dimensionWeightDto = new DimensionWeightDto();
        dimensionWeightDto.setDimensionWeights(dimensionWeights);

        // Act and Assert
        assertSame(dimensionWeightDto, dimensionWeightServiceImpl.saveDimensionWeight(dimensionWeightDto));
        verify(dimensionWeightRepository).save(Mockito.<DimensionWeight>any());
        verify(dimensionWeightRepository).deleteAll();
    }
}

