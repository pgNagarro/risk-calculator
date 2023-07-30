package com.nagarro.riskcalculatorbackend.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ScoreLevelDtoTest {

    /**
     * Method under test: {@link ScoreLevelDto#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange, Act and Assert
        assertNotEquals(new ScoreLevelDto("Condition 1", "Level 1"), null);
        assertNotEquals(new ScoreLevelDto("Condition 1", "Level 1"), "Different type to ScoreLevelDto");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreLevelDto#equals(Object)}
     *   <li>{@link ScoreLevelDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals2() {
        // Arrange
        ScoreLevelDto scoreLevelDto = new ScoreLevelDto("Condition 1", "Level 1");

        // Act and Assert
        assertEquals(scoreLevelDto, scoreLevelDto);
        int expectedHashCodeResult = scoreLevelDto.hashCode();
        assertEquals(expectedHashCodeResult, scoreLevelDto.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreLevelDto#equals(Object)}
     *   <li>{@link ScoreLevelDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        // Arrange
        ScoreLevelDto scoreLevelDto = new ScoreLevelDto("Condition 1", "Level 1");
        ScoreLevelDto scoreLevelDto2 = new ScoreLevelDto("Condition 1", "Level 1");

        // Act and Assert
        assertEquals(scoreLevelDto, scoreLevelDto2);
        int expectedHashCodeResult = scoreLevelDto.hashCode();
        assertEquals(expectedHashCodeResult, scoreLevelDto2.hashCode());
    }

    /**
     * Method under test: {@link ScoreLevelDto#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        ScoreLevelDto scoreLevelDto = new ScoreLevelDto("Condition 1", "Level 1");

        // Act and Assert
        assertNotEquals(scoreLevelDto, new ScoreLevelDto("Condition 2", "Level 1"));
    }

    /**
     * Method under test: {@link ScoreLevelDto#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        ScoreLevelDto scoreLevelDto = new ScoreLevelDto("Condition 1", "Level 1");

        // Act and Assert
        assertNotEquals(scoreLevelDto, new ScoreLevelDto("Condition 1", "Level 2"));
    }

    /**
     * Method under test: {@link ScoreLevelDto#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        ScoreLevelDto scoreLevelDto = new ScoreLevelDto("Condition 1", "Level 1");

        // Act and Assert
        assertNotEquals(scoreLevelDto, new ScoreLevelDto(null, "Level 1"));
    }

    /**
     * Method under test: {@link ScoreLevelDto#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        ScoreLevelDto scoreLevelDto = new ScoreLevelDto(null, "Level 1");

        // Act and Assert
        assertNotEquals(scoreLevelDto, new ScoreLevelDto("Condition 1", "Level 1"));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreLevelDto#equals(Object)}
     *   <li>{@link ScoreLevelDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals8() {
        // Arrange
        ScoreLevelDto scoreLevelDto = new ScoreLevelDto(null, "Level 1");
        ScoreLevelDto scoreLevelDto2 = new ScoreLevelDto(null, "Level 1");

        // Act and Assert
        assertEquals(scoreLevelDto, scoreLevelDto2);
        int expectedHashCodeResult = scoreLevelDto.hashCode();
        assertEquals(expectedHashCodeResult, scoreLevelDto2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreLevelDto#equals(Object)}
     *   <li>{@link ScoreLevelDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals9() {
        // Arrange
        ScoreLevelDto scoreLevelDto = new ScoreLevelDto("Condition 1", "Level 1");
        ScoreLevelDto scoreLevelDto2 = new ScoreLevelDto(null, "Level 1");

        // Act and Assert
        assertNotEquals(scoreLevelDto, scoreLevelDto2);
    }

    /**
     * Method under test: {@link ScoreLevelDto#equals(Object)}
     */
    @Test
    void testEquals10() {
        // Arrange
        ScoreLevelDto scoreLevelDto = new ScoreLevelDto(null, "Level 1");

        // Act and Assert
        assertNotEquals(scoreLevelDto, new ScoreLevelDto(null, "Level 2"));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreLevelDto#equals(Object)}
     *   <li>{@link ScoreLevelDto#hashCode()}
     * </ul>
     */
    @Test
    void testEquals11() {
        // Arrange
        ScoreLevelDto scoreLevelDto = new ScoreLevelDto(null, "Level 1");
        ScoreLevelDto scoreLevelDto2 = new ScoreLevelDto(null, "Level 1");

        // Act and Assert
        assertEquals(scoreLevelDto, scoreLevelDto2);
        int expectedHashCodeResult = scoreLevelDto.hashCode();
        assertEquals(expectedHashCodeResult, scoreLevelDto2.hashCode());
    }
    
    /**
     * Method under test: {@link ScoreLevelDto#ScoreLevelDto()}
     */
    @Test
    void testNoArgsConstructor() {
        // Arrange and Act
        ScoreLevelDto scoreLevelDto = new ScoreLevelDto();

        // Assert
        assertNotNull(scoreLevelDto);
    }
   
}
