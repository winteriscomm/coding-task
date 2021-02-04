package com.coding.task.formatter.impl;

import com.coding.task.formatter.DataFormatter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupedDataFormatterTest {

    private static final String ONE = "one";

    private static final String TWO = "two";

    private static final String THREE = "three";

    private static final String EXPECTED = "one: 2" + System.lineSeparator() +
            "two: 2" + System.lineSeparator() +
            "three: 1";

    private DataFormatter dataFormatter = new GroupedDataFormatter();

    @Test
    public void shouldReturnGroupedData() {
        String groupedData = dataFormatter.format(Arrays.asList(ONE, ONE, THREE, TWO, TWO));

        assertEquals(EXPECTED, groupedData);
    }
}
