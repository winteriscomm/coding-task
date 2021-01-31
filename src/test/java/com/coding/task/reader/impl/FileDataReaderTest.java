package com.coding.task.reader.impl;

import com.coding.task.collector.Collector;
import com.coding.task.collector.CollectorManager;
import com.coding.task.reader.DataReader;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileDataReaderTest {

    private static final String RESULT_STRING = "result-string";

    private static final String TEST_FILE_NAME = "test.txt";

    private static final String UNEXIST_FILE_NAME = "/unexist.txt";

    @Mock
    private CollectorManager collectorManager;

    @Mock
    private Collector first;

    @Before
    public void init() {
        when(collectorManager.getCollector(anyString())).thenReturn(first);
        when(collectorManager.getCollectors()).thenReturn(Collections.singletonList(first));
    }

    @Test
    public void shouldReturnEmptyStringWhenScannerCannotBeCreated() {
        DataReader dataReader = new FileDataReader(UNEXIST_FILE_NAME);

        String result = dataReader.read(collectorManager);

        assertTrue(StringUtils.isEmpty(result));
    }

    @Test
    public void shouldReturnCollectorsResult() {
        when(first.getResult()).thenReturn(RESULT_STRING);
        String path = getClass().getClassLoader().getResource(TEST_FILE_NAME).getPath();
        DataReader dataReader = new FileDataReader(path);

        String result = dataReader.read(collectorManager);

        verify(first, atLeast(3)).collect(any());
        assertEquals(RESULT_STRING, result);
    }
}
