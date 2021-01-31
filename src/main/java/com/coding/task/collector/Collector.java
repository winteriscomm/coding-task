package com.coding.task.collector;

/**
 * Interface for collecting data and providing result
 */
public interface Collector {

    /**
     * Collects data into internal collection
     *
     * @param data the data to collect
     */
    void collect(String data);

    /**
     * Returns result in required format
     *
     * @return result of collecting
     */
    String getResult();

    /**
     * Returns category name
     *
     * @return category name
     */
    String getCategory();
}
