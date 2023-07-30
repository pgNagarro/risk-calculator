package com.nagarro.riskcalculatorbackend.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CalculationLogicDtoTest {
    /**
     * Method under test: {@link CalculationLogicDto#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new CalculationLogicDto("Element Name", "Formula")).canEqual("Other"));
    }

    /**
     * Method under test: {@link CalculationLogicDto#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        CalculationLogicDto calculationLogicDto = new CalculationLogicDto("Element Name", "Formula");

        // Act and Assert
        assertTrue(calculationLogicDto.canEqual(new CalculationLogicDto("Element Name", "Formula")));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CalculationLogicDto#CalculationLogicDto()}
     *   <li>{@link CalculationLogicDto#setElementName(String)}
     *   <li>{@link CalculationLogicDto#setFormula(String)}
     *   <li>{@link CalculationLogicDto#toString()}
     *   <li>{@link CalculationLogicDto#getElementName()}
     *   <li>{@link CalculationLogicDto#getFormula()}
     * </ul>
     */
    @Test
    void testConstructor() {
        // Arrange and Act
        CalculationLogicDto actualCalculationLogicDto = new CalculationLogicDto();
        actualCalculationLogicDto.setElementName("Element Name");
        actualCalculationLogicDto.setFormula("Formula");
        String actualToStringResult = actualCalculationLogicDto.toString();

        // Assert that nothing has changed
        assertEquals("Element Name", actualCalculationLogicDto.getElementName());
        assertEquals("Formula", actualCalculationLogicDto.getFormula());
        assertEquals("CalculationLogicDto(elementName=Element Name, formula=Formula)", actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CalculationLogicDto#CalculationLogicDto(String, String)}
     *   <li>{@link CalculationLogicDto#setElementName(String)}
     *   <li>{@link CalculationLogicDto#setFormula(String)}
     *   <li>{@link CalculationLogicDto#toString()}
     *   <li>{@link CalculationLogicDto#getElementName()}
     *   <li>{@link CalculationLogicDto#getFormula()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        // Arrange and Act
        CalculationLogicDto actualCalculationLogicDto = new CalculationLogicDto("Element Name", "Formula");
        actualCalculationLogicDto.setElementName("Element Name");
        actualCalculationLogicDto.setFormula("Formula");
        String actualToStringResult = actualCalculationLogicDto.toString();

        // Assert that nothing has changed
        assertEquals("Element Name", actualCalculationLogicDto.getElementName());
        assertEquals("Formula", actualCalculationLogicDto.getFormula());
        assertEquals("CalculationLogicDto(elementName=Element Name, formula=Formula)", actualToStringResult);
    }

    /**
     * Method under test: {@link CalculationLogicDto#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange, Act and Assert
        assertNotEquals(new CalculationLogicDto("Element Name", "Formula"), null);
        assertNotEquals(new CalculationLogicDto("Element Name", "Formula"), "Different type to CalculationLogicDto");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CalculationLogicDto#equals(Object)}
     *   <li>{@link CalculationLogicDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals2() {
        // Arrange
        CalculationLogicDto calculationLogicDto = new CalculationLogicDto("Element Name", "Formula");

        // Act and Assert
        assertEquals(calculationLogicDto, calculationLogicDto);
        int expectedHashCodeResult = calculationLogicDto.hashCode();
        assertEquals(expectedHashCodeResult, calculationLogicDto.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CalculationLogicDto#equals(Object)}
     *   <li>{@link CalculationLogicDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        // Arrange
        CalculationLogicDto calculationLogicDto = new CalculationLogicDto("Element Name", "Formula");
        CalculationLogicDto calculationLogicDto2 = new CalculationLogicDto("Element Name", "Formula");

        // Act and Assert
        assertEquals(calculationLogicDto, calculationLogicDto2);
        int expectedHashCodeResult = calculationLogicDto.hashCode();
        assertEquals(expectedHashCodeResult, calculationLogicDto2.hashCode());
    }

    /**
     * Method under test: {@link CalculationLogicDto#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        CalculationLogicDto calculationLogicDto = new CalculationLogicDto("Formula", "Formula");

        // Act and Assert
        assertNotEquals(calculationLogicDto, new CalculationLogicDto("Element Name", "Formula"));
    }

    /**
     * Method under test: {@link CalculationLogicDto#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        CalculationLogicDto calculationLogicDto = new CalculationLogicDto(null, "Formula");

        // Act and Assert
        assertNotEquals(calculationLogicDto, new CalculationLogicDto("Element Name", "Formula"));
    }

    /**
     * Method under test: {@link CalculationLogicDto#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        CalculationLogicDto calculationLogicDto = new CalculationLogicDto("Element Name", "Element Name");

        // Act and Assert
        assertNotEquals(calculationLogicDto, new CalculationLogicDto("Element Name", "Formula"));
    }

    /**
     * Method under test: {@link CalculationLogicDto#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        CalculationLogicDto calculationLogicDto = new CalculationLogicDto("Element Name", null);

        // Act and Assert
        assertNotEquals(calculationLogicDto, new CalculationLogicDto("Element Name", "Formula"));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CalculationLogicDto#equals(Object)}
     *   <li>{@link CalculationLogicDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals8() {
        // Arrange
        CalculationLogicDto calculationLogicDto = new CalculationLogicDto(null, "Formula");
        CalculationLogicDto calculationLogicDto2 = new CalculationLogicDto(null, "Formula");

        // Act and Assert
        assertEquals(calculationLogicDto, calculationLogicDto2);
        int expectedHashCodeResult = calculationLogicDto.hashCode();
        assertEquals(expectedHashCodeResult, calculationLogicDto2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CalculationLogicDto#equals(Object)}
     *   <li>{@link CalculationLogicDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals9() {
        // Arrange
        CalculationLogicDto calculationLogicDto = new CalculationLogicDto("Element Name", null);
        CalculationLogicDto calculationLogicDto2 = new CalculationLogicDto("Element Name", null);

        // Act and Assert
        assertEquals(calculationLogicDto, calculationLogicDto2);
        int expectedHashCodeResult = calculationLogicDto.hashCode();
        assertEquals(expectedHashCodeResult, calculationLogicDto2.hashCode());
    }
}

