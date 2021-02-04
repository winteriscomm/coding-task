package com.coding.task.reader.impl;

import com.coding.task.collector.Collector;
import com.coding.task.collector.CollectorManager;
import com.coding.task.exception.ApplicationRunException;
import com.coding.task.reader.DataReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

/**
 * Provides functionality to read data from file
 */
public class FileDataReader implements DataReader {

    private static final Logger LOGGER = Logger.getLogger(FileDataReader.class);

    private static final String CANNOT_READ_DATA_FROM_FILE_MESSAGE = "Cannot read data from file %s";

    private String fileName;

    public FileDataReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String read(CollectorManager collectorManager) {
        try (Scanner scanner = new Scanner(new File(fileName), StandardCharsets.UTF_8.name())) {
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                Collector collector = collectorManager.getCollector(data);
                Optional.ofNullable(collector)
                        .ifPresent(c -> c.collect(data));
            }

            return StringUtils.join(collectorManager.getCollectors()
                    .stream()
                    .map(Collector::getResult)
                    .filter(StringUtils::isNotBlank)
                    .toArray(), System.lineSeparator());
        } catch (FileNotFoundException e) {
            LOGGER.error(String.format(CANNOT_READ_DATA_FROM_FILE_MESSAGE, fileName), e);
            throw new ApplicationRunException(String.format(CANNOT_READ_DATA_FROM_FILE_MESSAGE, fileName), e);
        }
    }
}
