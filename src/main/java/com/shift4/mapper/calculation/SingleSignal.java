package com.shift4.mapper.calculation;

import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class SingleSignal implements StreamGenerator {
    private static final Pattern SINGLE_NUMBER = Pattern.compile("^\\d+$");

    @Override
    public boolean test(String minute) {
        return SINGLE_NUMBER.matcher(minute).find();
    }

    @Override
    public IntStream apply(String minute) {
        return IntStream.of(Integer.parseInt(minute));
    }
}
