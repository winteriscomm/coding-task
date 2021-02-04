package com.coding.task.formatter.impl;

import com.coding.task.formatter.DataFormatter;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Md5DataFormatter implements DataFormatter {

    private static final String MD5_DATA_FORMAT = "%s (%s)";

    @Override
    public String format(List<String> data) {
        return data.stream()
                .distinct()
                .sorted(Collections.reverseOrder())
                .map(d -> String.format(MD5_DATA_FORMAT, d, DigestUtils.md5Hex(d)))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
