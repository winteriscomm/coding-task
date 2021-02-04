package com.coding.task.collector.test;

import com.coding.task.collector.Collector;
import com.coding.task.formatter.DataFormatter;

public class TestCollector implements Collector {

    private String category;

    private DataFormatter dataFormatter;

    public TestCollector(String category, DataFormatter dataFormatter) {
        this.dataFormatter = dataFormatter;
        this.category = category;
    }

    @Override
    public void collect(String data) {

    }

    @Override
    public String getResult() {
        return null;
    }

    @Override
    public String getCategory() {
        return category;
    }
}
