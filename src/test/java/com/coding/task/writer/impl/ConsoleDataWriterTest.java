package com.coding.task.writer.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ConsoleDataWriterTest {

    private static final String TEST_DATA = "test";

    private ConsoleDataWriter consoleDataWriter;

    private PrintStream defaultOutput = System.out;

    private ByteArrayOutputStream testOutput;

    @Before
    public void init() {
        testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));
        consoleDataWriter = new ConsoleDataWriter();

    }

    @After
    public void destroy() {
        System.setOut(defaultOutput);
    }

    @Test
    public void shouldWriteStringToConsole() {
        consoleDataWriter.write(TEST_DATA);

        assertEquals(TEST_DATA + System.lineSeparator(), testOutput.toString());
    }
}
