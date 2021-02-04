package com.coding.task.formatter;

import java.util.List;

/**
 * Interface to format data
 */
public interface DataFormatter {

    /**
     * Returns given data in appropriate format
     *
     * @param data to format
     * @return formatted data
     */
    String format(List<String> data);
}
