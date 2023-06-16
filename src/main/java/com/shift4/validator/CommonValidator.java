package com.shift4.validator;

import com.shift4.error.InvalidInputParameterException;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class CommonValidator {
    public final static Predicate<String> isNull = Objects::isNull;
    public final static Predicate<String> isNotEmpty = element -> element != null && element.toCharArray().length > 0;
    public final static Predicate<String> isCommonApproach = "*"::equals;
    public final static BiPredicate<Integer, Integer> isNotEquals = (first, second) -> !first.equals(second);
    public final static BiPredicate<Integer, Integer> isLessThan = (first, second) -> first < second;

    public static <T> void validateInputParam(Predicate<T> arg, T value, String errorDescription){
        if(arg.test(value)) {
            throw InvalidInputParameterException.of(errorDescription);
        }
    }

    public static <T> void validateInputParam(BiPredicate<T, T> arg, T first, T second, String errorDescription){
        if(arg.test(first, second)) {
            throw InvalidInputParameterException.of(errorDescription);
        }
    }

}
