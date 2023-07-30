package com.nagarro.riskcalculatorbackend.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ScoreLevelTest {
    /**
     * Method under test: {@link ScoreLevel#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new ScoreLevel()).canEqual("Other"));
    }

    /**
     * Method under test: {@link ScoreLevel#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel("Level");
        scoreLevel.setScore("Score");

        ScoreLevel scoreLevel2 = new ScoreLevel();
        scoreLevel2.setLevel("Level");
        scoreLevel2.setScore("Score");

        // Act and Assert
        assertTrue(scoreLevel.canEqual(scoreLevel2));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreLevel#ScoreLevel()}
     *   <li>{@link ScoreLevel#setLevel(String)}
     *   <li>{@link ScoreLevel#setScore(String)}
     *   <li>{@link ScoreLevel#toString()}
     *   <li>{@link ScoreLevel#getLevel()}
     *   <li>{@link ScoreLevel#getScore()}
     * </ul>
     */
    @Test
    void testConstructor() {
        // Arrange and Act
        ScoreLevel actualScoreLevel = new ScoreLevel();
        actualScoreLevel.setLevel("Level");
        actualScoreLevel.setScore("Score");
        String actualToStringResult = actualScoreLevel.toString();

        // Assert that nothing has changed
        assertEquals("Level", actualScoreLevel.getLevel());
        assertEquals("Score", actualScoreLevel.getScore());
        assertEquals("ScoreLevel(score=Score, level=Level)", actualToStringResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreLevel#ScoreLevel(String, String)}
     *   <li>{@link ScoreLevel#setLevel(String)}
     *   <li>{@link ScoreLevel#setScore(String)}
     *   <li>{@link ScoreLevel#toString()}
     *   <li>{@link ScoreLevel#getLevel()}
     *   <li>{@link ScoreLevel#getScore()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        // Arrange and Act
        ScoreLevel actualScoreLevel = new ScoreLevel("Score", "Level");
        actualScoreLevel.setLevel("Level");
        actualScoreLevel.setScore("Score");
        String actualToStringResult = actualScoreLevel.toString();

        // Assert that nothing has changed
        assertEquals("Level", actualScoreLevel.getLevel());
        assertEquals("Score", actualScoreLevel.getScore());
        assertEquals("ScoreLevel(score=Score, level=Level)", actualToStringResult);
    }

    /**
     * Method under test: {@link ScoreLevel#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel("Level");
        scoreLevel.setScore("Score");

        // Act and Assert
        assertNotEquals(scoreLevel, null);
    }

    /**
     * Method under test: {@link ScoreLevel#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel("Level");
        scoreLevel.setScore("Score");

        // Act and Assert
        assertNotEquals(scoreLevel, "Different type to ScoreLevel");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreLevel#equals(Object)}
     *   <li>{@link ScoreLevel#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        // Arrange
        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel("Level");
        scoreLevel.setScore("Score");

        // Act and Assert
        assertEquals(scoreLevel, scoreLevel);
        int expectedHashCodeResult = scoreLevel.hashCode();
        assertEquals(expectedHashCodeResult, scoreLevel.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreLevel#equals(Object)}
     *   <li>{@link ScoreLevel#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
        // Arrange
        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel("Level");
        scoreLevel.setScore("Score");

        ScoreLevel scoreLevel2 = new ScoreLevel();
        scoreLevel2.setLevel("Level");
        scoreLevel2.setScore("Score");

        // Act and Assert
        assertEquals(scoreLevel, scoreLevel2);
        int expectedHashCodeResult = scoreLevel.hashCode();
        assertEquals(expectedHashCodeResult, scoreLevel2.hashCode());
    }

    /**
     * Method under test: {@link ScoreLevel#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel("Score");
        scoreLevel.setScore("Score");

        ScoreLevel scoreLevel2 = new ScoreLevel();
        scoreLevel2.setLevel("Level");
        scoreLevel2.setScore("Score");

        // Act and Assert
        assertNotEquals(scoreLevel, scoreLevel2);
    }

    /**
     * Method under test: {@link ScoreLevel#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel(null);
        scoreLevel.setScore("Score");

        ScoreLevel scoreLevel2 = new ScoreLevel();
        scoreLevel2.setLevel("Level");
        scoreLevel2.setScore("Score");

        // Act and Assert
        assertNotEquals(scoreLevel, scoreLevel2);
    }

    /**
     * Method under test: {@link ScoreLevel#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel("Level");
        scoreLevel.setScore("Level");

        ScoreLevel scoreLevel2 = new ScoreLevel();
        scoreLevel2.setLevel("Level");
        scoreLevel2.setScore("Score");

        // Act and Assert
        assertNotEquals(scoreLevel, scoreLevel2);
    }

    /**
     * Method under test: {@link ScoreLevel#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel("Level");
        scoreLevel.setScore(null);

        ScoreLevel scoreLevel2 = new ScoreLevel();
        scoreLevel2.setLevel("Level");
        scoreLevel2.setScore("Score");

        // Act and Assert
        assertNotEquals(scoreLevel, scoreLevel2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreLevel#equals(Object)}
     *   <li>{@link ScoreLevel#hashCode()}
     * </ul>
     */
    @Test
    void testEquals9() {
        // Arrange
        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel(null);
        scoreLevel.setScore("Score");

        ScoreLevel scoreLevel2 = new ScoreLevel();
        scoreLevel2.setLevel(null);
        scoreLevel2.setScore("Score");

        // Act and Assert
        assertEquals(scoreLevel, scoreLevel2);
        int expectedHashCodeResult = scoreLevel.hashCode();
        assertEquals(expectedHashCodeResult, scoreLevel2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ScoreLevel#equals(Object)}
     *   <li>{@link ScoreLevel#hashCode()}
     * </ul>
     */
    @Test
    void testEquals10() {
        // Arrange
        ScoreLevel scoreLevel = new ScoreLevel();
        scoreLevel.setLevel("Level");
        scoreLevel.setScore(null);

        ScoreLevel scoreLevel2 = new ScoreLevel();
        scoreLevel2.setLevel("Level");
        scoreLevel2.setScore(null);

        // Act and Assert
        assertEquals(scoreLevel, scoreLevel2);
        int expectedHashCodeResult = scoreLevel.hashCode();
        assertEquals(expectedHashCodeResult, scoreLevel2.hashCode());
    }
}

