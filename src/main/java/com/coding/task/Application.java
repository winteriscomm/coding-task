package com.coding.task;

import com.coding.task.collector.CollectorManager;
import com.coding.task.collector.impl.CollectorManagerImpl;
import com.coding.task.exception.ApplicationRunException;
import com.coding.task.exception.CollectorInitializationException;
import com.coding.task.reader.DataReader;
import com.coding.task.reader.impl.FileDataReader;
import com.coding.task.writer.DataWriter;
import com.coding.task.writer.impl.ConsoleDataWriter;

import java.util.Arrays;

public class Application {

    private static final String COLLECTOR_MAPPING_PROPERTIES = "/collectorMapping.properties";

    private static final String APPLICATION_ERROR_MESSAGE = "Application cannot be started due to: %s";

    public static void main(String[] args) {
        Arrays.stream(args).forEach(path -> {
            DataWriter dataWriter = new ConsoleDataWriter();
            try {
                CollectorManager collectorManager = new CollectorManagerImpl(COLLECTOR_MAPPING_PROPERTIES);
                DataReader dataReader = new FileDataReader(path);

                dataWriter.write(dataReader.read(collectorManager));
            } catch (CollectorInitializationException | ApplicationRunException e) {
                dataWriter.write(String.format(APPLICATION_ERROR_MESSAGE, e.getMessage()));
            }
        });
    }
}
