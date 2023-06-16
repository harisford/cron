package com.shift4.mapper.calculation;

import java.util.stream.IntStream;

public class RangeOfInput implements StreamGenerator {
    private static final String SEPARATOR = "-";

    @Override
    public boolean test(String input) {
        return input.contains(SEPARATOR);
    }

    @Override
    public IntStream apply(String input) {
        String[] range = input.split(SEPARATOR);
        return IntStream.range(Integer.parseInt(range[0]), Integer.parseInt(range[1]) + 1);
    }
}
