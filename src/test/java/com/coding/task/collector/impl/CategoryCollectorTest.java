package com.coding.task.collector.impl;

import com.coding.task.collector.Collector;
import com.coding.task.formatter.DataFormatter;
import com.coding.task.formatter.test.TestFormatter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryCollectorTest {

    private DataFormatter formatter = new TestFormatter();

    private static final String CARS_CATEGORY = "CARS";

    private static final String OPEL = "opel";

    private static final String BMW = "bmw";

    private static final String AUDI = "audi";

    private static final String EXPECTED = CARS_CATEGORY + ":" + System.lineSeparator() +
            AUDI + System.lineSeparator() +
            BMW + System.lineSeparator() +
            BMW + System.lineSeparator() +
            OPEL;

    @Test
    public void shouldCollectAllItems() {
        Collector collector = new CategoryCollector(CARS_CATEGORY, formatter);

        Arrays.asList(BMW, BMW.toUpperCase(), OPEL, AUDI).forEach(collector::collect);

        assertEquals(EXPECTED, collector.getResult());
    }

    @Test
    public void shouldCollectAllItemsAndSkipCategory() {
        Collector collector = new CategoryCollector(CARS_CATEGORY, formatter);

        Arrays.asList(CARS_CATEGORY, BMW, CARS_CATEGORY.toLowerCase(), OPEL, AUDI, BMW).forEach(collector::collect);

        assertEquals(EXPECTED, collector.getResult());
    }
}
