package com.coding.task.formatter.impl;

import com.coding.task.formatter.DataFormatter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderedDataFormatterTest {

    private static final String COW = "cow";

    private static final String HORSE = "horse";

    private static final String MOOSE = "moose";

    private static final String EXPECTED = COW + System.lineSeparator()
            + HORSE + System.lineSeparator()
            + MOOSE;

    private DataFormatter dataFormatter = new OrderedDataFormatter();

    @Test
    public void shouldReturnItemsInNaturalOrder() {
        String orderedData = dataFormatter.format(Arrays.asList(HORSE, COW, MOOSE));

        assertEquals(EXPECTED, orderedData);
    }
}
