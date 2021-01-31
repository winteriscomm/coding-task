package com.coding.task.collector.impl;

import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 * Provides functionality to collect items with animals category
 */
public class AnimalCollector extends AbstractCollector {

    private Set<String> animals = new TreeSet<>();

    public AnimalCollector(String name) {
        super(name);
    }

    @Override
    public void addItem(String data) {
        animals.add(data);
    }

    @Override
    public void addResult(StringJoiner result) {
        animals.forEach(result::add);
    }
}
