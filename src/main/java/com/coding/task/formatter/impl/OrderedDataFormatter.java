package com.coding.task.formatter.impl;

import com.coding.task.formatter.DataFormatter;

import java.util.List;
import java.util.stream.Collectors;

public class OrderedDataFormatter implements DataFormatter {

    @Override
    public String format(List<String> data) {
        return data.stream()
                .distinct()
                .sorted()
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
