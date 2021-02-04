package com.coding.task.writer.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleDataWriterTest {

    private static final String TEST_DATA = "test";

    private ConsoleDataWriter consoleDataWriter;

    private PrintStream defaultOutput = System.out;

    private ByteArrayOutputStream testOutput;

    @BeforeEach
    public void init() {
        testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));
        consoleDataWriter = new ConsoleDataWriter();

    }

    @AfterEach
    public void destroy() {
        System.setOut(defaultOutput);
    }

    @Test
    public void shouldWriteStringToConsole() {
        consoleDataWriter.write(TEST_DATA);

        assertEquals(TEST_DATA + System.lineSeparator(), testOutput.toString());
    }
}
