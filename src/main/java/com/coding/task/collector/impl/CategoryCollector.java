package com.coding.task.collector.impl;

import com.coding.task.collector.Collector;
import com.coding.task.formatter.DataFormatter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Provides basic implementation for collecting data and processing result.
 */
public class CategoryCollector implements Collector {

    private static final String COLON = ":";

    private String category;

    private DataFormatter formatter;

    private List<String> data = new ArrayList<>();

    public CategoryCollector(String category, DataFormatter formatter) {
        this.category = category;
        this.formatter = formatter;
    }

    @Override
    public void collect(String dataItem) {
        Optional.of(dataItem)
                .filter(StringUtils::isNotBlank)
                .map(StringUtils::trim)
                .filter(s -> !getCategory().equalsIgnoreCase(s))
                .map(String::toLowerCase)
                .ifPresent(this::addItem);
    }

    private void addItem(String dataItem) {
        data.add(dataItem);
    }

    @Override
    public String getResult() {
        return Optional.of(formatter.format(data))
                .filter(StringUtils::isNotBlank)
                .map(this::addCategoryName)
                .orElse(StringUtils.EMPTY);
    }

    private String addCategoryName(String data) {
        return getCategory() + COLON + System.lineSeparator() + data;
    }

    @Override
    public String getCategory() {
        return category;
    }
}
