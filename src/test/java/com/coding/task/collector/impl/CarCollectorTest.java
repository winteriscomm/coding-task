package com.coding.task.collector.impl;

import com.coding.task.collector.Collector;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CarCollectorTest {

    private static final String CARS_CATEGORY = "CARS";

    private static final String OPEL = "opel";

    private static final String BMW = "bmw";

    private static final String AUDI = "audi";

    private static final String EXPECTED = CARS_CATEGORY + ":" + System.lineSeparator() +
            "opel (f65b7d39472c52142ea2f4ea5e115d59)" + System.lineSeparator() +
            "bmw (71913f59e458e026d6609cdb5a7cc53d)" + System.lineSeparator() +
            "audi (4d9fa555e7c23996e99f1fb0e286aea8)";

    @Test
    public void shouldReturnCarsInReverseOrder() {
        Collector collector = new CarCollector(CARS_CATEGORY);

        Arrays.asList(BMW, BMW.toUpperCase(), OPEL, AUDI).forEach(collector::collect);

        assertEquals(EXPECTED, collector.getResult());
    }

    @Test
    public void shouldReturnCarsInReverseOrderAndSkipCategoryName() {
        Collector collector = new CarCollector(CARS_CATEGORY);

        Arrays.asList(CARS_CATEGORY, BMW, CARS_CATEGORY.toLowerCase(), OPEL, AUDI).forEach(collector::collect);

        assertEquals(EXPECTED, collector.getResult());
    }

}
