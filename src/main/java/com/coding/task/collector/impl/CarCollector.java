package com.coding.task.collector.impl;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Collections;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;

/**
 * Provides functionality to collect items with cars category
 */
public class CarCollector extends AbstractCollector {

    private static final String CAR_RESULT_FORMAT = "%s (%s)";

    private Map<String, String> cars = new TreeMap<>(Collections.reverseOrder());

    public CarCollector(String name) {
        super(name);
    }

    @Override
    public void addItem(String data) {
        cars.putIfAbsent(data, DigestUtils.md5Hex(data));
    }

    @Override
    public void addResult(StringJoiner result) {
        cars.forEach((key, value) -> result.add(String.format(CAR_RESULT_FORMAT, key, value)));
    }
}
