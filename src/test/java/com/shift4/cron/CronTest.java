package com.shift4.cron;

import com.shift4.error.InvalidInputParameterException;
import com.shift4.provider.Environment;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CronTest {

    final Cron cron = new Cron();

    @ParameterizedTest
    @MethodSource("provideInputString")
    void invalidInputParameters(String[] input) {
        TestEnvironment testEnvironment = new TestEnvironment();
        assertThrows(InvalidInputParameterException.class, () -> {
            cron.print(input, testEnvironment);
        });
    }

    @ParameterizedTest
    @MethodSource("provideCronInputString")
    void getCronParseOutput(String cronTimeInput, List<String> expected){
        String[] input = new String[] {cronTimeInput};
        TestEnvironment testEnvironment = new TestEnvironment();
        cron.print(input, testEnvironment);
        assertEquals(expected,testEnvironment.testResult);
    }

    private static Stream<Arguments> provideCronInputString() {
        return Stream.of(
                Arguments.of("*/15 0 1,15 * 1-5 test",
                        List.of(
                                "minute               0 15 30 45",
                                "hour                 0",
                                "day of month         1 15",
                                "month                1 2 3 4 5 6 7 8 9 10 11 12",
                                "day of week          1 2 3 4 5",
                                "command              test")
                ),
                Arguments.of("15 2 15 1 1 bin/user",
                        List.of("minute               15",
                                "hour                 2",
                                "day of month         15",
                                "month                1",
                                "day of week          1",
                                "command              bin/user")
                ),
                Arguments.of("*/10 * 15 2 1-2 user",
                        List.of("minute               0 10 20 30 40 50",
                                "hour                 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23",
                                "day of month         15",
                                "month                2",
                                "day of week          1 2",
                                "command              user"))
        );
    }

    private static Stream<Arguments> provideInputString() {
        return Stream.of(
                Arguments.of((Object) new String[] {"2 1,4 * 1-5 t"}),
                Arguments.of((Object) new String[] {"0 15 30 45"}),
                Arguments.of((Object) new String[] {"50"})
        );
    }

    private class TestEnvironment implements Environment {
        public List<String> testResult = new ArrayList<>();

        @Override
        public String getInputParam(String[] arg) {
            return arg[0];
        }

        @Override
        public void print(String input) {
            testResult.add(input);
        }
    }
}
