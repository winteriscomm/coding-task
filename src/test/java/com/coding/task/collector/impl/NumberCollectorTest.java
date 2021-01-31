package com.coding.task.collector.impl;

import com.coding.task.collector.Collector;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class NumberCollectorTest {

    private static final String NUMBERS_CATEGORY = "NUMBERS";

    private static final String ONE = "one";

    private static final String TWO = "two";

    private static final String THREE = "three";

    private static final String EXPECTED = NUMBERS_CATEGORY + ":" + System.lineSeparator() +
            "one: 2" + System.lineSeparator() +
            "two: 2" + System.lineSeparator() +
            "three: 1";

    @Test
    public void shouldReturnNumbers() {
        Collector collector = new NumberCollector(NUMBERS_CATEGORY);

        Arrays.asList(ONE, ONE, THREE, TWO, TWO).forEach(collector::collect);

        assertEquals(EXPECTED, collector.getResult());
    }

    @Test
    public void shouldReturnNumbersAndSkipCategoryName() {
        Collector collector = new NumberCollector(NUMBERS_CATEGORY);

        Arrays.asList(NUMBERS_CATEGORY, ONE, ONE, NUMBERS_CATEGORY.toLowerCase(), THREE, TWO, TWO).forEach(collector::collect);

        assertEquals(EXPECTED, collector.getResult());
    }

}