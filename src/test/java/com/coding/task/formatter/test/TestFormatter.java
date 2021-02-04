package com.coding.task.formatter.test;

import com.coding.task.formatter.DataFormatter;

import java.util.List;
import java.util.stream.Collectors;

public class TestFormatter implements DataFormatter {

    @Override
    public String format(List<String> data) {
        return data.stream()
                .sorted()
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
