package com.coding.task.collector.impl;

import com.coding.task.collector.Collector;
import com.coding.task.collector.CollectorManager;
import com.coding.task.exception.CollectorInitializationException;
import com.coding.task.formatter.DataFormatter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class CollectorManagerImpl implements CollectorManager {

    private static final Logger LOGGER = Logger.getLogger(CollectorManagerImpl.class);

    private static final String CONFIG_DELIMITER = ":";

    private static final String CANNOT_INITIALIZE_COLLECTORS_MESSAGE = "Cannot initialize collectors.";

    private static final String CANNOT_READ_COLLECTOR_MAPPING_MESSAGE = "Cannot read collector mapping.";

    private static final String WRONG_MAPPING_FORMAT_MESSAGE = "Wrong format for collector mapping is used.";

    private static final String WRONG_FILE_NAME_MESSAGE = "Wrong config file name is used.";

    private Map<String, Collector> collectors = new LinkedHashMap<>();

    private Collector lastUsedCollector;

    public CollectorManagerImpl(String mappingPath) {
        initCollectors(mappingPath);
    }

    private void initCollectors(String mappingPath) {
        try (InputStream input = getClass().getResourceAsStream(mappingPath)) {
            Properties prop = new Properties();

            Optional.ofNullable(input)
                    .orElseThrow(() -> new CollectorInitializationException(WRONG_FILE_NAME_MESSAGE));

            prop.load(input);
            prop.stringPropertyNames().forEach(collectorName -> createCollectors(prop, collectorName, collectors));
        } catch (IOException e) {
            LOGGER.error(CANNOT_READ_COLLECTOR_MAPPING_MESSAGE, e);
            throw new CollectorInitializationException(CANNOT_READ_COLLECTOR_MAPPING_MESSAGE, e);
        }
    }

    private void createCollectors(Properties prop, String collectorName, Map<String, Collector> collectors) {
        try {
            String[] collectorMapping = Optional.ofNullable(prop.getProperty(collectorName))
                    .map(config -> config.split(CONFIG_DELIMITER))
                    .filter(classes -> classes.length == 2)
                    .orElseThrow(() -> new CollectorInitializationException(WRONG_MAPPING_FORMAT_MESSAGE));

            String capitalizedCollectorName = collectorName.toUpperCase();
            DataFormatter formatter = createFormatter(collectorMapping[1]);
            collectors.put(capitalizedCollectorName, createCollector(capitalizedCollectorName, collectorMapping[0], formatter));
        } catch (InstantiationException | NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error(CANNOT_INITIALIZE_COLLECTORS_MESSAGE, e);
            throw new CollectorInitializationException(CANNOT_INITIALIZE_COLLECTORS_MESSAGE, e);
        }
    }

    private DataFormatter createFormatter(String formatterClassName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Class<DataFormatter> formatterClass = (Class<DataFormatter>) Class.forName(formatterClassName);
        Constructor<DataFormatter> constructor = formatterClass.getConstructor();
        return constructor.newInstance();
    }

    private Collector createCollector(String collectorName, String collectorClassName, DataFormatter formatter) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Class<Collector> collectorClass = (Class<Collector>) Class.forName(collectorClassName);
        Constructor<Collector> constructor = collectorClass.getConstructor(String.class, DataFormatter.class);
        return constructor.newInstance(collectorName, formatter);
    }

    @Override
    public Collector getCollector(String name) {
        lastUsedCollector = collectors.getOrDefault(name.toUpperCase(), lastUsedCollector);

        return lastUsedCollector;
    }

    @Override
    public List<Collector> getCollectors() {
        return new ArrayList<>(collectors.values());
    }
}
