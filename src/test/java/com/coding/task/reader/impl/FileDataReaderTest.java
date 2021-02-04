package com.coding.task.reader.impl;

import com.coding.task.collector.Collector;
import com.coding.task.collector.CollectorManager;
import com.coding.task.exception.ApplicationRunException;
import com.coding.task.reader.DataReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileDataReaderTest {

    private static final String RESULT_STRING = "result-string";

    private static final String TEST_FILE_NAME = "test.txt";

    private static final String UNEXIST_FILE_NAME = "/unexist.txt";

    @Mock
    private CollectorManager collectorManager;

    @Mock
    private Collector first;

    @Test
    public void shouldThrowExpectionWhenFileDoesNotExists() {
        DataReader dataReader = new FileDataReader(UNEXIST_FILE_NAME);

        Assertions.assertThrows(ApplicationRunException.class, () -> {
            dataReader.read(collectorManager);
        });
    }

    @Test
    public void shouldReturnCollectorsResult() {
        when(collectorManager.getCollector(anyString())).thenReturn(first);
        when(collectorManager.getCollectors()).thenReturn(Collections.singletonList(first));
        when(first.getResult()).thenReturn(RESULT_STRING);
        String path = getClass().getClassLoader().getResource(TEST_FILE_NAME).getPath();
        DataReader dataReader = new FileDataReader(path);

        String result = dataReader.read(collectorManager);

        verify(first, atLeast(3)).collect(any());
        assertEquals(RESULT_STRING, result);
    }
}
