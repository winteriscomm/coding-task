package com.coding.task.formatter.impl;

import com.coding.task.formatter.DataFormatter;

import java.util.List;
import java.util.stream.Collectors;

public class GroupedDataFormatter implements DataFormatter {

    private static final String NUMBERS_RESULT_FORMAT = "%s: %s";

    @Override
    public String format(List<String> data) {
        return data.stream()
                .collect(Collectors.groupingBy(number -> number, Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> (String.format(NUMBERS_RESULT_FORMAT, entry.getKey(), entry.getValue())))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
