package com.shift4.validator;

import com.shift4.error.InvalidInputParameterException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

import static com.shift4.validator.CommonValidator.*;
import static org.junit.jupiter.api.Assertions.*;

class CommonValidatorTest {

    @ParameterizedTest
    @EmptySource
    void isNotEmpty(String input) {
        assertFalse(isNotEmpty.test(input));
    }

    @ParameterizedTest
    @NullSource
    void isEmpty(String input) {
        assertTrue(isNull.test(input));
    }

    @Test
    void isHasStar() {
        assertTrue(isCommonApproach.test("*"));
    }

    @ParameterizedTest
    @EmptySource
    void isHasStar(String input) {
        assertFalse(isCommonApproach.test(input));
    }

    @ParameterizedTest
    @MethodSource("provideInputValues")
    void isNotEquals(int first, int second, boolean expected ) {
        assertEquals(isNotEquals.test(first,second), expected);
    }

    @Test
    void hasCorrectInputParams() {
        validateInputParam(isNotEquals, 1, 1, "test");
    }

    @Test
    void invalidInputParams() {
        assertThrows(InvalidInputParameterException.class,
                () -> validateInputParam(isLessThan, 1, 3, "test"));
    }

    private static Stream<Arguments> provideInputValues() {
        return Stream.of(
                Arguments.of(1, 2, true),
                Arguments.of(1, 1, false),
                Arguments.of(2, 1, true)
        );
    }
}
