package org.ttrzcinski.fileext.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParamCheckTest {

    @Test
    void isPath_withNull() {
        // Arrange
        String testObject = null;

        // Act
        boolean result = ParamCheck.isPath(testObject);

        // Assert
        assertFalse(result);
    }

    @Test
    void isPath_withEmpty() {
        // Arrange
        String testObject = "";

        // Act
        boolean result = ParamCheck.isPath(testObject);

        // Assert
        assertFalse(result);
    }

    @Test
    void isPath_withTrimToEmpty() {
        // Arrange
        String testObject = "   ";

        // Act
        boolean result = ParamCheck.isPath(testObject);

        // Assert
        assertFalse(result);
    }

    @Test
    void isPath_withTrimToGood() {
        // Arrange
        String testObject = "  /home  ";

        // Act
        boolean result = ParamCheck.isPath(testObject);

        // Assert
        assertTrue(result);
    }

    @Test
    void isPath_withGood() {
        // Arrange
        String testObject = "/home";

        // Act
        boolean result = ParamCheck.isPath(testObject);

        // Assert
        assertTrue(result);
    }

    @Test
    void isPath_withTrimToWrong() {
        // Arrange
        String testObject = "  @  ";

        // Act
        boolean result = ParamCheck.isPath(testObject);

        // Assert
        assertFalse(result);
    }

    @Test
    void isPath_withWrong() {
        // Arrange
        String testObject = "@";

        // Act
        boolean result = ParamCheck.isPath(testObject);

        // Assert
        assertFalse(result);
    }

    @Test
    void isSet_withNull() {
        // Arrange
        String testObject = null;

        // Act
        boolean result = ParamCheck.isSet(testObject);

        // Assert
        assertFalse(result);
    }

    @Test
    void isSet_withEmpty() {
        // Arrange
        String testObject = "";

        // Act
        boolean result = ParamCheck.isSet(testObject);

        // Assert
        assertFalse(result);
    }

    @Test
    void isSet_withTrimToEmpty() {
        // Arrange
        String testObject = "   ";

        // Act
        boolean result = ParamCheck.isSet(testObject);

        // Assert
        assertFalse(result);
    }

    @Test
    void isSet_withTrimToGood() {
        // Arrange
        String testObject = "  test  ";

        // Act
        boolean result = ParamCheck.isSet(testObject);

        // Assert
        assertTrue(result);
    }

    @Test
    void isSet_withGood() {
        // Arrange
        String testObject = "test";

        // Act
        boolean result = ParamCheck.isSet(testObject);

        // Assert
        assertTrue(result);
    }
}