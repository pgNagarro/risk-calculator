package com.nagarro.riskcalculatorbackend.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Test;

class ScoreCapDtoTest {

    /**
     * Method under test: {@link ScoreCapDto#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange, Act and Assert
        assertNotEquals(new ScoreCapDto("Condition 1", 100), null);
        assertNotEquals(new ScoreCapDto("Condition 1", 100), "Different type to ScoreCapDto");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreCapDto#equals(Object)}
     *   <li>{@link ScoreCapDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals2() {
        // Arrange
        ScoreCapDto scoreCapDto = new ScoreCapDto("Condition 1", 100);

        // Act and Assert
        assertEquals(scoreCapDto, scoreCapDto);
        int expectedHashCodeResult = scoreCapDto.hashCode();
        assertEquals(expectedHashCodeResult, scoreCapDto.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreCapDto#equals(Object)}
     *   <li>{@link ScoreCapDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        // Arrange
        ScoreCapDto scoreCapDto = new ScoreCapDto("Condition 1", 100);
        ScoreCapDto scoreCapDto2 = new ScoreCapDto("Condition 1", 100);

        // Act and Assert
        assertEquals(scoreCapDto, scoreCapDto2);
        int expectedHashCodeResult = scoreCapDto.hashCode();
        assertEquals(expectedHashCodeResult, scoreCapDto2.hashCode());
    }

    /**
     * Method under test: {@link ScoreCapDto#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        ScoreCapDto scoreCapDto = new ScoreCapDto("Condition 1", 100);

        // Act and Assert
        assertNotEquals(scoreCapDto, new ScoreCapDto("Condition 2", 100));
    }

    /**
     * Method under test: {@link ScoreCapDto#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        ScoreCapDto scoreCapDto = new ScoreCapDto("Condition 1", 100);

        // Act and Assert
        assertNotEquals(scoreCapDto, new ScoreCapDto("Condition 1", 200));
    }

    /**
     * Method under test: {@link ScoreCapDto#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        ScoreCapDto scoreCapDto = new ScoreCapDto("Condition 1", 100);

        // Act and Assert
        assertNotEquals(scoreCapDto, new ScoreCapDto(null, 100));
    }

    /**
     * Method under test: {@link ScoreCapDto#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        ScoreCapDto scoreCapDto = new ScoreCapDto(null, 100);

        // Act and Assert
        assertNotEquals(scoreCapDto, new ScoreCapDto("Condition 1", 100));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreCapDto#equals(Object)}
     *   <li>{@link ScoreCapDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals8() {
        // Arrange
        ScoreCapDto scoreCapDto = new ScoreCapDto(null, 100);
        ScoreCapDto scoreCapDto2 = new ScoreCapDto(null, 100);

        // Act and Assert
        assertEquals(scoreCapDto, scoreCapDto2);
        int expectedHashCodeResult = scoreCapDto.hashCode();
        assertEquals(expectedHashCodeResult, scoreCapDto2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreCapDto#equals(Object)}
     *   <li>{@link ScoreCapDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals9() {
        // Arrange
        ScoreCapDto scoreCapDto = new ScoreCapDto("Condition 1", 100);
        ScoreCapDto scoreCapDto2 = new ScoreCapDto(null, 100);

        // Act and Assert
        assertNotEquals(scoreCapDto, scoreCapDto2);
    }

    /**
     * Method under test: {@link ScoreCapDto#equals(Object)}
     */
    @Test
    void testEquals10() {
        // Arrange
        ScoreCapDto scoreCapDto = new ScoreCapDto(null, 100);

        // Act and Assert
        assertNotEquals(scoreCapDto, new ScoreCapDto(null, 200));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreCapDto#equals(Object)}
     *   <li>{@link ScoreCapDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals11() {
        // Arrange
        ScoreCapDto scoreCapDto = new ScoreCapDto(null, 100);
        ScoreCapDto scoreCapDto2 = new ScoreCapDto(null, 100);

        // Act and Assert
        assertEquals(scoreCapDto, scoreCapDto2);
        int expectedHashCodeResult = scoreCapDto.hashCode();
        assertEquals(expectedHashCodeResult, scoreCapDto2.hashCode());
    }
    
    /**
     * Method under test: {@link ScoreCapDto#ScoreCapDto()}
     */
    @Test
    void testNoArgsConstructor() {
        // Arrange and Act
        ScoreCapDto scoreCapDto = new ScoreCapDto();

        // Assert
        assertNotNull(scoreCapDto);
    }
   
}
