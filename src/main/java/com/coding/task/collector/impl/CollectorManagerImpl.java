package com.coding.task.collector.impl;

import com.coding.task.collector.Collector;
import com.coding.task.collector.CollectorManager;
import com.coding.task.exception.CollectorInitializationException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CollectorManagerImpl implements CollectorManager {

    private static final Logger LOGGER = Logger.getLogger(CollectorManagerImpl.class);

    private static final String CANNOT_INITIALIZE_COLLECTORS_MESSAGE = "Cannot initialize collectors.";

    private static final String CANNOT_READ_COLLECTOR_MAPPING_MESSAGE = "Cannot read collector mapping.";

    private static final String COLLECTOR_MAPPING_PROPERTIES = "/collectorMapping.properties";

    private Map<String, Collector> collectors = new LinkedHashMap<>();

    private Collector lastUsedCollector;

    public CollectorManagerImpl() {
        initCollectors();
    }

    private void initCollectors() {
        try (InputStream input = getClass().getResourceAsStream(COLLECTOR_MAPPING_PROPERTIES)) {
            Properties prop = new Properties();

            prop.load(input);
            prop.stringPropertyNames().forEach(collectorName -> createCollectors(prop, collectorName, collectors));
        } catch (IOException e) {
            LOGGER.error(CANNOT_READ_COLLECTOR_MAPPING_MESSAGE, e);
            throw new CollectorInitializationException(CANNOT_READ_COLLECTOR_MAPPING_MESSAGE, e);
        }
    }

    private void createCollectors(Properties prop, String collectorName, Map<String, Collector> collectors) {
        try {
            String className = prop.getProperty(collectorName);
            Class<Collector> clazz = (Class<Collector>) Class.forName(className);
            Constructor<Collector> constructor = clazz.getConstructor(String.class);

            String capitalizedCollectorName = collectorName.toUpperCase();
            collectors.put(capitalizedCollectorName, constructor.newInstance(capitalizedCollectorName));
        } catch (InstantiationException | NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error(CANNOT_INITIALIZE_COLLECTORS_MESSAGE, e);
            throw new CollectorInitializationException(CANNOT_INITIALIZE_COLLECTORS_MESSAGE, e);
        }
    }

    @Override
    public Collector getCollector(String name) {
        Collector collector = collectors.getOrDefault(name, lastUsedCollector);
        lastUsedCollector = collector;

        return collector;
    }

    @Override
    public List<Collector> getCollectors() {
        return new ArrayList<>(collectors.values());
    }
}
