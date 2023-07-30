package com.nagarro.riskcalculatorbackend.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import com.nagarro.riskcalculatorbackend.models.Dimension;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CompanyDimensionDtoTest {
    /**
     * Method under test: {@link CompanyDimensionDto#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new CompanyDimensionDto()).canEqual("Other"));
    }

    /**
     * Method under test: {@link CompanyDimensionDto#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        CompanyDimensionDto companyDimensionDto = new CompanyDimensionDto();

        // Act and Assert
        assertTrue(companyDimensionDto.canEqual(new CompanyDimensionDto()));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CompanyDimensionDto#CompanyDimensionDto()}
     *   <li>{@link CompanyDimensionDto#setCompanyName(String)}
     *   <li>{@link CompanyDimensionDto#setDimensions(List)}
     *   <li>{@link CompanyDimensionDto#toString()}
     *   <li>{@link CompanyDimensionDto#getCompanyName()}
     *   <li>{@link CompanyDimensionDto#getDimensions()}
     * </ul>
     */
    @Test
    void testConstructor() {
        // Arrange and Act
        CompanyDimensionDto actualCompanyDimensionDto = new CompanyDimensionDto();
        actualCompanyDimensionDto.setCompanyName("Company Name");
        ArrayList<Dimension> dimensions = new ArrayList<>();
        actualCompanyDimensionDto.setDimensions(dimensions);
        String actualToStringResult = actualCompanyDimensionDto.toString();

        // Assert that nothing has changed
        assertEquals("Company Name", actualCompanyDimensionDto.getCompanyName());
        assertSame(dimensions, actualCompanyDimensionDto.getDimensions());
        assertEquals("CompanyDimensionDto(companyName=Company Name, dimensions=[])", actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CompanyDimensionDto#CompanyDimensionDto(String, List)}
     *   <li>{@link CompanyDimensionDto#setCompanyName(String)}
     *   <li>{@link CompanyDimensionDto#setDimensions(List)}
     *   <li>{@link CompanyDimensionDto#toString()}
     *   <li>{@link CompanyDimensionDto#getCompanyName()}
     *   <li>{@link CompanyDimensionDto#getDimensions()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        // Arrange
        ArrayList<Dimension> dimensions = new ArrayList<>();

        // Act
        CompanyDimensionDto actualCompanyDimensionDto = new CompanyDimensionDto("Company Name", dimensions);
        actualCompanyDimensionDto.setCompanyName("Company Name");
        ArrayList<Dimension> dimensions2 = new ArrayList<>();
        actualCompanyDimensionDto.setDimensions(dimensions2);
        String actualToStringResult = actualCompanyDimensionDto.toString();

        // Assert that nothing has changed
        assertEquals("Company Name", actualCompanyDimensionDto.getCompanyName());
        List<Dimension> dimensions3 = actualCompanyDimensionDto.getDimensions();
        assertSame(dimensions2, dimensions3);
        assertEquals(dimensions, dimensions3);
        assertEquals("CompanyDimensionDto(companyName=Company Name, dimensions=[])", actualToStringResult);
    }

    /**
     * Method under test: {@link CompanyDimensionDto#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange, Act and Assert
        assertNotEquals(new CompanyDimensionDto(), null);
        assertNotEquals(new CompanyDimensionDto(), "Different type to CompanyDimensionDto");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CompanyDimensionDto#equals(Object)}
     *   <li>{@link CompanyDimensionDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals2() {
        // Arrange
        CompanyDimensionDto companyDimensionDto = new CompanyDimensionDto();

        // Act and Assert
        assertEquals(companyDimensionDto, companyDimensionDto);
        int expectedHashCodeResult = companyDimensionDto.hashCode();
        assertEquals(expectedHashCodeResult, companyDimensionDto.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CompanyDimensionDto#equals(Object)}
     *   <li>{@link CompanyDimensionDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        // Arrange
        CompanyDimensionDto companyDimensionDto = new CompanyDimensionDto();
        CompanyDimensionDto companyDimensionDto2 = new CompanyDimensionDto();

        // Act and Assert
        assertEquals(companyDimensionDto, companyDimensionDto2);
        int expectedHashCodeResult = companyDimensionDto.hashCode();
        assertEquals(expectedHashCodeResult, companyDimensionDto2.hashCode());
    }

    /**
     * Method under test: {@link CompanyDimensionDto#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        CompanyDimensionDto companyDimensionDto = new CompanyDimensionDto("Company Name", new ArrayList<>());

        // Act and Assert
        assertNotEquals(companyDimensionDto, new CompanyDimensionDto());
    }

    /**
     * Method under test: {@link CompanyDimensionDto#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        CompanyDimensionDto companyDimensionDto = new CompanyDimensionDto();

        // Act and Assert
        assertNotEquals(companyDimensionDto, new CompanyDimensionDto("Company Name", new ArrayList<>()));
    }

    /**
     * Method under test: {@link CompanyDimensionDto#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        CompanyDimensionDto companyDimensionDto = new CompanyDimensionDto();
        companyDimensionDto.setDimensions(new ArrayList<>());

        // Act and Assert
        assertNotEquals(companyDimensionDto, new CompanyDimensionDto());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CompanyDimensionDto#equals(Object)}
     *   <li>{@link CompanyDimensionDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals7() {
        // Arrange
        CompanyDimensionDto companyDimensionDto = new CompanyDimensionDto("Company Name", new ArrayList<>());
        CompanyDimensionDto companyDimensionDto2 = new CompanyDimensionDto("Company Name", new ArrayList<>());

        // Act and Assert
        assertEquals(companyDimensionDto, companyDimensionDto2);
        int expectedHashCodeResult = companyDimensionDto.hashCode();
        assertEquals(expectedHashCodeResult, companyDimensionDto2.hashCode());
    }

    /**
     * Method under test: {@link CompanyDimensionDto#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        CompanyDimensionDto companyDimensionDto = new CompanyDimensionDto();

        CompanyDimensionDto companyDimensionDto2 = new CompanyDimensionDto();
        companyDimensionDto2.setDimensions(new ArrayList<>());

        // Act and Assert
        assertNotEquals(companyDimensionDto, companyDimensionDto2);
    }

    /**
     * Method under test: {@link CompanyDimensionDto#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        Dimension dimension = mock(Dimension.class);
        doNothing().when(dimension).setDimensionName(Mockito.<String>any());
        doNothing().when(dimension).setDimensionValue(anyInt());
        dimension.setDimensionName("Dimension Name");
        dimension.setDimensionValue(42);

        ArrayList<Dimension> dimensions = new ArrayList<>();
        dimensions.add(dimension);
        CompanyDimensionDto companyDimensionDto = new CompanyDimensionDto("Company Name", dimensions);

        // Act and Assert
        assertNotEquals(companyDimensionDto, new CompanyDimensionDto());
    }
}

