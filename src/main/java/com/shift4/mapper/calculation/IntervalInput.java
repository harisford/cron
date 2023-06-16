package com.shift4.mapper.calculation;

import java.util.stream.IntStream;

public class IntervalInput implements StreamGenerator {
    private final static int ONE_HOURS = 60;
    private final static String SEPARATOR = "/";
    private final static String SPECIAL_CHARACTERS = "*/";

    @Override
    public boolean test(String minute) {
        return minute.startsWith(SPECIAL_CHARACTERS);
    }

    @Override
    public IntStream apply(String minute) {
        int interval = Integer.parseInt(minute.split(SEPARATOR)[1]);
        return IntStream.range(0, (int) Math.floor(ONE_HOURS / interval))
                .map(element -> element * interval);
    }
}
