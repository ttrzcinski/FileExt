package org.ttrzcinski.fileext.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringFixTest {

    @Test
    void toNotNull_withNull() {
        // Arrange
        String testObject = null;

        // Act
        String result = StringFix.toNotNull(testObject);

        // Assert
        assertEquals("", result);
    }

    @Test
    void toNotNull_withEmpty() {
        // Arrange
        String testObject = "";

        // Act
        String result = StringFix.toNotNull(testObject);

        // Assert
        assertEquals("", result);
    }

    @Test
    void toNotNull_withTrimToEmpty() {
        // Arrange
        String testObject = "   ";

        // Act
        String result = StringFix.toNotNull(testObject);

        // Assert
        assertEquals("", result);
    }

    @Test
    void toNotNull_withTrimToGood() {
        // Arrange
        String testObject = "  test  ";
        String expected = testObject.trim();

        // Act
        String result = StringFix.toNotNull(testObject);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void toNotNull_withGood() {
        // Arrange
        String testObject = "test";

        // Act
        String result = StringFix.toNotNull(testObject);

        // Assert
        assertEquals(testObject, result);
    }
}