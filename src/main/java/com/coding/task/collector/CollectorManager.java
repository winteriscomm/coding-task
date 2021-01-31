package com.coding.task.collector;

import java.util.List;

/**
 * Interface to manage lifecycle of collectors
 */
public interface CollectorManager {

    /**
     * Returns collector by name
     *
     * @param name of collector
     * @return collector with the given name when collector exists otherwise last used collector
     */
    Collector getCollector(String name);

    /**
     * Returns list of collectors
     *
     * @return list of collectors
     */
    List<Collector> getCollectors();
}
