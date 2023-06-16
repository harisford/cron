package com.shift4.mapper.calculation;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SetOfInput implements StreamGenerator {
    private static final String SEPARATOR = ",";

    @Override
    public boolean test(String day) {
        return day.contains(SEPARATOR);
    }

    @Override
    public IntStream apply(String day) {
        String[] days = day.split(SEPARATOR);
        return Arrays.stream(days).mapToInt(Integer::parseInt);
    }
}
