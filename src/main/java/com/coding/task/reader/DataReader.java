package com.coding.task.reader;

import com.coding.task.collector.CollectorManager;

/**
 * Interface to read and collect data
 */
public interface DataReader {

    /**
     * Read data from certain source and collect with the given collector manager
     *
     * @param collectorManager the collector manager
     * @return result of collecting data by collector manager
     */
    String read(CollectorManager collectorManager);
}
