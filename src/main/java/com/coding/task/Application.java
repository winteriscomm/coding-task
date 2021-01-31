package com.coding.task;

import com.coding.task.collector.Collector;
import com.coding.task.collector.CollectorManager;
import com.coding.task.collector.impl.AnimalCollector;
import com.coding.task.collector.impl.CarCollector;
import com.coding.task.collector.impl.CollectorManagerImpl;
import com.coding.task.collector.impl.NumberCollector;
import com.coding.task.reader.DataReader;
import com.coding.task.reader.impl.FileDataReader;
import com.coding.task.writer.DataWriter;
import com.coding.task.writer.impl.ConsoleDataWriter;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        Arrays.stream(args).forEach(path -> {
            CollectorManager collectorManager = new CollectorManagerImpl();
            DataReader dataReader = new FileDataReader(path);
            DataWriter dataWriter = new ConsoleDataWriter();

            dataWriter.write(dataReader.read(collectorManager));
        });
    }
}
