package com.coding.task.collector.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Provides functionality to collect items with numbers category
 */
public class NumberCollector extends AbstractCollector {

    private static final String NUMBERS_RESULT_FORMAT = "%s: %s";

    private List<String> numbers = new ArrayList<>();

    public NumberCollector(String name) {
        super(name);
    }

    @Override
    public void addItem(String data) {
        numbers.add(data);
    }

    @Override
    public void addResult(StringJoiner result) {
        numbers.stream()
                .collect(Collectors.groupingBy(number -> number, Collectors.counting()))
                .forEach((key, value) -> result.add(String.format(NUMBERS_RESULT_FORMAT, key, value)));
    }
}
