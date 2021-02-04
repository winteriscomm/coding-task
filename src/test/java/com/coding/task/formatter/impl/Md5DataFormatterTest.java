package com.coding.task.formatter.impl;

import com.coding.task.formatter.DataFormatter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Md5DataFormatterTest {

    private static final String OPEL = "opel";

    private static final String BMW = "bmw";

    private static final String AUDI = "audi";

    private static final String EXPECTED = "opel (f65b7d39472c52142ea2f4ea5e115d59)" + System.lineSeparator() +
            "bmw (71913f59e458e026d6609cdb5a7cc53d)" + System.lineSeparator() +
            "audi (4d9fa555e7c23996e99f1fb0e286aea8)";

    private DataFormatter dataFormatter = new Md5DataFormatter();

    @Test
    public void shouldReturnDataInReverseOrderWithMd5Hash() {
        String dataWithMd5Hash = dataFormatter.format(Arrays.asList(BMW, BMW, OPEL, AUDI));

        assertEquals(EXPECTED, dataWithMd5Hash);
    }
}
