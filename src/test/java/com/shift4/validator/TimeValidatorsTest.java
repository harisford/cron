package com.shift4.validator;

import com.shift4.mapper.CronInputParam;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.shift4.validator.TimeValidators.VALIDATORS;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeValidatorsTest {

    static Map<CronInputParam, Predicate<String>> mapper = VALIDATORS;

    @Nested
    class Minute {

        @ParameterizedTest
        @MethodSource("com.shift4.validator.TimeValidatorsTest#provideInputMinuteValues")
        void generateMinuteFormat(String input, boolean expected) {
            assertEquals(expected, mapper.get(CronInputParam.MINUTES).test(input));
        }
    }

    @Nested
    class Hours {

        @ParameterizedTest
        @MethodSource("com.shift4.validator.TimeValidatorsTest#provideInputHoursValues")
        void generateMinuteFormat(String input, boolean expected) {
            assertEquals(expected, mapper.get(CronInputParam.HOURS).test(input));
        }
    }

    @Nested
    class Days {

        @ParameterizedTest
        @MethodSource("com.shift4.validator.TimeValidatorsTest#provideInputDaysValues")
        void generateMinuteFormat(String input, boolean expected) {
            assertEquals(expected, mapper.get(CronInputParam.DAYS).test(input));
        }
    }

    @Nested
    class DayOfWeek {

        @ParameterizedTest
        @MethodSource("com.shift4.validator.TimeValidatorsTest#provideInputDayOfWeekValues")
        void generateMinuteFormat(String input, boolean expected) {
            assertEquals(expected, mapper.get(CronInputParam.DAY_OF_WEEK).test(input));
        }
    }

    @Nested
    class Month {

        @ParameterizedTest
        @MethodSource("com.shift4.validator.TimeValidatorsTest#provideInputMouthValues")
        void generateMinuteFormat(String input, boolean expected) {
            assertEquals(expected, mapper.get(CronInputParam.MONTH).test(input));
        }
    }

    private static Stream<Arguments> provideInputDayOfWeekValues() {
        return Stream.of(
                Arguments.of("*", true),
                Arguments.of("2", true),
                Arguments.of("1-7", true),
                Arguments.of("123", false),
                Arguments.of("", false),
                Arguments.of("test", false)
        );
    }

    private static Stream<Arguments> provideInputDaysValues() {
        return Stream.of(
                Arguments.of("*", true),
                Arguments.of("2", true),
                Arguments.of("1-23", true),
                Arguments.of("1,23", true),
                Arguments.of("123", false),
                Arguments.of("", false),
                Arguments.of("test", false)
        );
    }

    private static Stream<Arguments> provideInputMouthValues() {
        return Stream.of(
                Arguments.of("*", true),
                Arguments.of("0", false),
                Arguments.of("1", true),
                Arguments.of("1-10", true),
                Arguments.of("1,11", true),
                Arguments.of("13", false),
                Arguments.of("12", true),
                Arguments.of("", false),
                Arguments.of("test", false)
        );
    }

    private static Stream<Arguments> provideInputHoursValues() {
        return Stream.of(
                Arguments.of("*", true),
                Arguments.of("2", true),
                Arguments.of("200", false),
                Arguments.of("", false),
                Arguments.of("test", false)
        );
    }

    private static Stream<Arguments> provideInputMinuteValues() {
        return Stream.of(
                Arguments.of("*", true),
                Arguments.of("2", true),
                Arguments.of("*/10", true),
                Arguments.of("", false),
                Arguments.of("test", false)
        );
    }

}
