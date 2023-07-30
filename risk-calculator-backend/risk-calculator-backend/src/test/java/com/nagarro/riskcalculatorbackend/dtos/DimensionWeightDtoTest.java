package com.nagarro.riskcalculatorbackend.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import com.nagarro.riskcalculatorbackend.models.DimensionWeight;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DimensionWeightDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link DimensionWeightDto#DimensionWeightDto()}
     *   <li>{@link DimensionWeightDto#setDimensionWeights(List)}
     *   <li>{@link DimensionWeightDto#toString()}
     *   <li>{@link DimensionWeightDto#getDimensionWeights()}
     * </ul>
     */
    @Test
    void testConstructor() {
        // Arrange and Act
        DimensionWeightDto actualDimensionWeightDto = new DimensionWeightDto();
        ArrayList<DimensionWeight> dimensionWeights = new ArrayList<>();
        actualDimensionWeightDto.setDimensionWeights(dimensionWeights);
        String actualToStringResult = actualDimensionWeightDto.toString();

        // Assert that nothing has changed
        assertSame(dimensionWeights, actualDimensionWeightDto.getDimensionWeights());
        assertEquals("DimensionWeightDto(dimensionWeights=[])", actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link DimensionWeightDto#DimensionWeightDto(List)}
     *   <li>{@link DimensionWeightDto#setDimensionWeights(List)}
     *   <li>{@link DimensionWeightDto#toString()}
     *   <li>{@link DimensionWeightDto#getDimensionWeights()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        // Arrange
        ArrayList<DimensionWeight> dimensionWeights = new ArrayList<>();

        // Act
        DimensionWeightDto actualDimensionWeightDto = new DimensionWeightDto(dimensionWeights);
        ArrayList<DimensionWeight> dimensionWeights2 = new ArrayList<>();
        actualDimensionWeightDto.setDimensionWeights(dimensionWeights2);
        String actualToStringResult = actualDimensionWeightDto.toString();

        // Assert that nothing has changed
        List<DimensionWeight> dimensionWeights3 = actualDimensionWeightDto.getDimensionWeights();
        assertSame(dimensionWeights2, dimensionWeights3);
        assertEquals(dimensionWeights, dimensionWeights3);
        assertEquals("DimensionWeightDto(dimensionWeights=[])", actualToStringResult);
    }

    /**
     * Method under test: {@link DimensionWeightDto#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange, Act and Assert
        assertNotEquals(new DimensionWeightDto(), null);
        assertNotEquals(new DimensionWeightDto(), "Different type to DimensionWeightDto");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link DimensionWeightDto#equals(Object)}
     *   <li>{@link DimensionWeightDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals2() {
        // Arrange
        DimensionWeightDto dimensionWeightDto = new DimensionWeightDto();

        // Act and Assert
        assertEquals(dimensionWeightDto, dimensionWeightDto);
        int expectedHashCodeResult = dimensionWeightDto.hashCode();
        assertEquals(expectedHashCodeResult, dimensionWeightDto.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link DimensionWeightDto#equals(Object)}
     *   <li>{@link DimensionWeightDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        // Arrange
        DimensionWeightDto dimensionWeightDto = new DimensionWeightDto();
        DimensionWeightDto dimensionWeightDto2 = new DimensionWeightDto();

        // Act and Assert
        assertEquals(dimensionWeightDto, dimensionWeightDto2);
        int expectedHashCodeResult = dimensionWeightDto.hashCode();
        assertEquals(expectedHashCodeResult, dimensionWeightDto2.hashCode());
    }

    /**
     * Method under test: {@link DimensionWeightDto#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        DimensionWeightDto dimensionWeightDto = new DimensionWeightDto(new ArrayList<>());

        // Act and Assert
        assertNotEquals(dimensionWeightDto, new DimensionWeightDto());
    }

    /**
     * Method under test: {@link DimensionWeightDto#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        DimensionWeightDto dimensionWeightDto = new DimensionWeightDto();

        // Act and Assert
        assertNotEquals(dimensionWeightDto, new DimensionWeightDto(new ArrayList<>()));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link DimensionWeightDto#equals(Object)}
     *   <li>{@link DimensionWeightDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals6() {
        // Arrange
        DimensionWeightDto dimensionWeightDto = new DimensionWeightDto(new ArrayList<>());
        DimensionWeightDto dimensionWeightDto2 = new DimensionWeightDto(new ArrayList<>());

        // Act and Assert
        assertEquals(dimensionWeightDto, dimensionWeightDto2);
        int expectedHashCodeResult = dimensionWeightDto.hashCode();
        assertEquals(expectedHashCodeResult, dimensionWeightDto2.hashCode());
    }

    /**
     * Method under test: {@link DimensionWeightDto#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        DimensionWeight dimensionWeight = mock(DimensionWeight.class);
        doNothing().when(dimensionWeight).setDimension(Mockito.<String>any());
        doNothing().when(dimensionWeight).setWeight(anyInt());
        dimensionWeight.setDimension("Dimension");
        dimensionWeight.setWeight(3);

        ArrayList<DimensionWeight> dimensionWeights = new ArrayList<>();
        dimensionWeights.add(dimensionWeight);
        DimensionWeightDto dimensionWeightDto = new DimensionWeightDto(dimensionWeights);

        // Act and Assert
        assertNotEquals(dimensionWeightDto, new DimensionWeightDto());
    }
}

