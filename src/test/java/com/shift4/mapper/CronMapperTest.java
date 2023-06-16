package com.shift4.mapper;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CronMapperTest {

    static Map<CronInputParam, Function<String, String>> mapper = CronMapper.GENERATORS;

    @Nested
    class Minute {

        @ParameterizedTest
        @MethodSource("com.shift4.mapper.CronMapperTest#provideInputMinuteValues")
        void generateMinuteFormat(String input, String expected) {
            assertEquals(expected, mapper.get(CronInputParam.MINUTES).apply(input));
        }
    }

    @Nested
    class Hours {

        @ParameterizedTest
        @MethodSource("com.shift4.mapper.CronMapperTest#provideInputHoursValues")
        void generateHoursFormat(String input, String expected) {
            assertEquals(expected, mapper.get(CronInputParam.HOURS).apply(input));
        }
    }

    @Nested
    class Day {

        @ParameterizedTest
        @MethodSource("com.shift4.mapper.CronMapperTest#provideInputDayValues")
        void generateHoursFormat(String input, String expected) {
            assertEquals(expected, mapper.get(CronInputParam.DAYS).apply(input));
        }
    }

    @Nested
    class Mouth {

        @ParameterizedTest
        @MethodSource("com.shift4.mapper.CronMapperTest#provideInputMonthValues")
        void generateHoursFormat(String input, String expected) {
            assertEquals(expected, mapper.get(CronInputParam.MONTH).apply(input));
        }
    }

    @Nested
    class DayOfWeek {

        @ParameterizedTest
        @MethodSource("com.shift4.mapper.CronMapperTest#provideInputDayOfWeekValues")
        void generateHoursFormat(String input, String expected) {
            assertEquals(expected, mapper.get(CronInputParam.DAY_OF_WEEK).apply(input));
        }
    }

    private static Stream<Arguments> provideInputMinuteValues() {
        return Stream.of(
                Arguments.of("*", IntStream.range(0, 59).mapToObj(index -> index + "").collect(Collectors.joining(" "))),
                Arguments.of("*/15", "0 15 30 45"),
                Arguments.of("50", "50")
        );
    }

    private static Stream<Arguments> provideInputHoursValues() {
        return Stream.of(
                Arguments.of("*", IntStream.range(0, 24).mapToObj(index -> index + "").collect(Collectors.joining(" "))),
                Arguments.of("50", "50")
        );
    }

    private static Stream<Arguments> provideInputDayValues() {
        return Stream.of(
                Arguments.of("*", IntStream.range(0, 31).mapToObj(index -> index + "").collect(Collectors.joining(" "))),
                Arguments.of("20", "20"),
                Arguments.of("1-5", "1 2 3 4 5")
        );
    }

    private static Stream<Arguments> provideInputMonthValues() {
        return Stream.of(
                Arguments.of("*", IntStream.range(1, 13).mapToObj(index -> index + "").collect(Collectors.joining(" "))),
                Arguments.of("2", "2"),
                Arguments.of("1-5", "1 2 3 4 5")
        );
    }

    private static Stream<Arguments> provideInputDayOfWeekValues() {
        return Stream.of(
                Arguments.of("*", IntStream.range(1, 8).mapToObj(index -> index + "").collect(Collectors.joining(" "))),
                Arguments.of("2", "2"),
                Arguments.of("1-7", "1 2 3 4 5 6 7"),
                Arguments.of("2-4", "2 3 4")
        );
    }
}
