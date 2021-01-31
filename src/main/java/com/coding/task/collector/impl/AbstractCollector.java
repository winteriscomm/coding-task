package com.coding.task.collector.impl;

import com.coding.task.collector.Collector;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.StringJoiner;

/**
 * Provides basic implementation for collecting data and processing result.
 */
public abstract class AbstractCollector implements Collector {

    private static final String COLON = ":";

    private String name;

    public AbstractCollector(String name) {
        this.name = name;
    }

    @Override
    public void collect(String data) {
        Optional.of(data)
                .filter(StringUtils::isNotBlank)
                .map(StringUtils::trim)
                .filter(s -> !getCategory().equalsIgnoreCase(s))
                .map(String::toLowerCase)
                .ifPresent(this::addItem);
    }

    /**
     * Adds new data into internal collection.
     *
     * @param data to add
     */
    public abstract void addItem(String data);

    @Override
    public String getResult() {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        addResult(result);

        return Optional.of(result.toString())
                .filter(StringUtils::isNotBlank)
                .map(this::addCategoryName)
                .orElse(StringUtils.EMPTY);
    }

    private String addCategoryName(String data) {
        return getCategory() + COLON + System.lineSeparator() + data;
    }

    /**
     * Adds data to string joiner into desired format.
     *
     * @param result string joiner to processing result with line separator.
     */
    public abstract void addResult(StringJoiner result);

    @Override
    public String getCategory() {
        return name;
    }
}
