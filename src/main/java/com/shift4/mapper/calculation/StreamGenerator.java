package com.shift4.mapper.calculation;

import java.util.stream.IntStream;

public interface StreamGenerator {
    boolean test(String minute);
    IntStream apply(String minute);
}
