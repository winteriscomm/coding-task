package com.coding.task.collector.impl;

import com.coding.task.collector.Collector;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class AnimalCollectorTest {

    private static final String ANIMALS_CATEGORY = "ANIMALS";

    private static final String COW = "cow";

    private static final String HORSE = "horse";

    private static final String MOOSE = "moose";

    private static final String EXPECTED = ANIMALS_CATEGORY + ":" + System.lineSeparator()
            + COW + System.lineSeparator()
            + HORSE + System.lineSeparator()
            + MOOSE;

    @Test
    public void shouldReturnAnimalsInNaturalOrder() {
        Collector collector = new AnimalCollector(ANIMALS_CATEGORY);

        Arrays.asList(HORSE, COW, MOOSE).forEach(collector::collect);

        assertEquals(EXPECTED, collector.getResult());
    }

    @Test
    public void shouldReturnAnimalsInNaturalOrderAndSkipCategoryName() {
        Collector collector = new AnimalCollector(ANIMALS_CATEGORY);

        Arrays.asList(ANIMALS_CATEGORY, HORSE, ANIMALS_CATEGORY.toLowerCase(), COW, MOOSE).forEach(collector::collect);

        assertEquals(EXPECTED, collector.getResult());
    }

    @Test
    public void shouldReturnUniqueAnimals() {
        Collector collector = new AnimalCollector(ANIMALS_CATEGORY);

        Arrays.asList(ANIMALS_CATEGORY, HORSE, COW, MOOSE, COW.toUpperCase(), MOOSE).forEach(collector::collect);

        assertEquals(EXPECTED, collector.getResult());
    }

}
