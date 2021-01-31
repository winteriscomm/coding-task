package com.coding.task.writer.impl;

import com.coding.task.writer.DataWriter;

/**
 * Provides functionality to write data into console
 */
public class ConsoleDataWriter implements DataWriter {

    @Override
    public void write(String data) {
        System.out.println(data);
    }
}
