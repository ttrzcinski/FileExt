package org.ttrzcinski.fileext;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MountsTest {

    @Test
    void getInstance_notNull() {
        // Arrange

        // Act
        var testObject = Mounts.getInstance();

        // Assert
        assertNotNull(testObject);
    }

    // TODO void readBasePaths() {

    @Test
    void contains() {
        // Arrange
        Mounts mounts = Mounts.getInstance();
        var testKey = "the_home";
        var testObject = Mount.of("~");

        // Act
        mounts.mount(testKey, testObject);
        var result = Mounts.contains(testKey);
        mounts.unmount(testKey);

        // Assert
        assertTrue(result);
    }

    @Test
    void clear() {
        // Arrange
        Mounts mounts = Mounts.getInstance();
        var testKey = "the_home";
        var testObject = Mount.of("~");
        var expected = 0;

        // Act
        mounts.mount(testKey, testObject);
        mounts.clear();
        var result = mounts.size();

        // Assert
        assertEquals(0, result);
    }

    @Test
    void size_empty() {
        // Arrange
        Mounts mounts = Mounts.getInstance();
        var testKey = "the_home";
        var testObject = Mount.of("~");
        var expected = 0;

        // Act
        mounts.clear();
        var result = mounts.size();
        mounts.clear();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void size_someValue() {
        // Arrange
        Mounts mounts = Mounts.getInstance();
        var testKey = "the_home";
        var testObject = Mount.of("~");
        var expected = 1;

        // Act
        mounts.clear();
        mounts.mount(testKey, testObject);
        var result = mounts.size();
        mounts.clear();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void withMount_mount() {
        // Arrange
        var testKey = "the_home";
        var expected = Mount.of("~");
        var mounts = Mounts.getInstance()
        // Act
            .withMount(testKey, expected);
        var result = Mounts.get(testKey);
        mounts.clear();

        // Assert
        assertNotNull(result);
        assertEquals(expected.getPath().toString(), result.getPath().toString());
    }

    @Test
    void withMount_path() {
        // Arrange
        var testKey = "the_home";
        var testObject = Path.of("~");
        var expected = Mount.of(testObject);
        var mounts = Mounts.getInstance()
        // Act
                .withMount(testKey, testObject);
        var result = Mounts.get(testKey);
        mounts.clear();

        // Assert
        assertNotNull(result);
        assertEquals(expected.getPath().toString(), result.getPath().toString());
    }

    @Test
    void withMount_string() {
        // Arrange
        var testKey = "the_home";
        var testObject = "~";
        var expected = Mount.of(testObject);
        var mounts = Mounts.getInstance()
                // Act
                .withMount(testKey, testObject);
        var result = Mounts.get(testKey);
        mounts.clear();

        // Assert
        assertNotNull(result);
        assertEquals(expected.getPath().toString(), result.getPath().toString());
    }

    // TODO void mount_mount();
    // TODO void mount_path();
    // TODO void mount_string();

    @Test
    void unmount_notNull() {
        // Arrange
        var testKey = "the_home";
        var testObject = Mount.of("~");
        var mounts = Mounts.getInstance();

        // Act
        mounts.mount(testKey, testObject);
        var existsBefore = Mounts.contains(testKey);
        mounts.unmount(testKey);
        var result = Mounts.contains(testKey);
        mounts.clear();

        // Assert
        assertTrue(existsBefore);
        assertFalse(result);
    }

    @Test
    void unmount_null() {
        // Arrange
        var testKey = "the_home";
        Mount testObject = null;
        var mounts = Mounts.getInstance();

        // Act
        mounts.mount(testKey, testObject);
        var result = Mounts.contains(testKey);
        mounts.clear();

        // Assert
        assertFalse(result);
    }

    @Test
    void get_goodOne() {
        // Arrange
        var testKey = "the_home";
        Mount testObject = Mount.of("~");
        var mounts = Mounts.getInstance();

        // Act
        mounts.clear();
        mounts.mount(testKey, testObject);
        var result = Mounts.get(testKey);
        mounts.clear();

        // Assert
        assertNotNull(result);
    }

    @Test
    void get_notExistingKey() {
        // Arrange
        var testKey = "the_home";
        var wrongKey = "sss";
        Mount testObject = Mount.of("~");
        var mounts = Mounts.getInstance();

        // Act
        mounts.clear();
        mounts.mount(testKey, testObject);
        var result = Mounts.get(wrongKey);
        mounts.clear();

        // Assert
        assertNull(result);
    }

    @Test
    void getAll() {
        // Arrange
        var testKey = "the_home";
        var testKey2 = "the_peak";
        Mount testObject = Mount.of("~");
        Mount testObject2 = Mount.of("~");
        var expected = 2;
        var mounts = Mounts.getInstance();

        // Act
        mounts.clear();
        mounts.mount(testKey, testObject);
        mounts.mount(testKey2, testObject2);
        var result = Mounts.getAll();
        mounts.clear();

        // Assert
        assertEquals(expected, result.size());
    }

    @Test
    void toConsole() {
        // Arrange
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        PrintStream originalOut = System.out;
        System.setOut(ps);
        var testKey = "the_home";
        Mount testObject = Mount.of("~");
        var mounts = Mounts.getInstance();

        // Act
        mounts.clear();
        mounts.mount(testKey, testObject);
        mounts.toConsole();
        mounts.clear();

        // Assert
        assertTrue(os.toString().contains("List of mounts:"));

        // Clean after test
        System.setOut(originalOut);
    }

    @Test
    void toJSON_notNull() {
        // Arrange
        Mounts mounts = Mounts.getInstance();
        var testKey = "the_home";
        var testObject = Mount.of("~");

        // Act
        mounts.clear();
        mounts.mount(testKey, testObject);
        var result = mounts.toJSON();

        // Assert
        assertNotNull(result);
    }

    @Test
    void toJSON_byValue() {
        // Arrange
        Mounts mounts = Mounts.getInstance();
        var testKey = "the_home";
        var testObject = Mount.of("~");
        var path = testObject.getPath().toAbsolutePath();
        var expected = String.format("{\"the_home\":\"%s\"}", path)
                .replace("\\", "\\\\");

        // Act
        mounts.clear();
        mounts.mount(testKey, testObject);
        var result = mounts.toJSON();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void toJSON_empty() {
        // Arrange
        Mounts mounts = Mounts.getInstance();
        var expected = "{}";

        // Act
        mounts.clear();
        var result = mounts.toJSON();

        // Assert
        assertEquals(expected, result);
    }
}