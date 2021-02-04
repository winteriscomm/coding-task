package com.coding.task.collector.impl;

import com.coding.task.collector.Collector;
import com.coding.task.collector.CollectorManager;
import com.coding.task.exception.CollectorInitializationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollectorManagerImplTest {

    @Test
    public void shouldThrowExceptionWhenWrongMappingFormatIsUsed() {
        Assertions.assertThrows(CollectorInitializationException.class, () -> {
            new CollectorManagerImpl("/collectorMappingWithWrongFormat.properties");
        });
    }

    @Test
    public void shouldThrowExceptionWhenFileDoesNotExist() {
        Assertions.assertThrows(CollectorInitializationException.class, () -> {
            new CollectorManagerImpl("/collectorMappingNonExisting.properties");
        });
    }

    @Test
    public void shouldThrowExceptionWhenFileFileHasWrongClassExist() {
        Assertions.assertThrows(CollectorInitializationException.class, () -> {
            new CollectorManagerImpl("/collectorMappingWithWrongClassName.properties");
        });
    }

    @Test
    public void shouldGetTheLastUsedCollectorWhenCollectorNameIsWrong() {
        CollectorManager collectorManager = new CollectorManagerImpl("/collectorMapping.properties");

        Collector firstCollector = collectorManager.getCollector("test");
        Collector secondCollector = collectorManager.getCollector("asd");

        assertTrue(firstCollector == secondCollector);
    }

    @Test
    public void shouldReturnAllCollectors() {
        CollectorManager collectorManager = new CollectorManagerImpl("/collectorMapping.properties");

        List<Collector> collectors = collectorManager.getCollectors();

        assertFalse(collectors.isEmpty());
    }
}
