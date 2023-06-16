package com.shift4.mapper;

import com.shift4.mapper.calculation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.shift4.mapper.CronInputParam.*;

public class CronMapper {

    private static final List<StreamGenerator> timeGenerators =
            List.of(new SingleSignal(), new IntervalInput());

    private static final List<StreamGenerator> daysGenerators =
            List.of(new SingleSignal(), new RangeOfInput(), new SetOfInput());

    private static final List<StreamGenerator> mouthsGenerators =
            List.of(new SingleSignal(), new RangeOfInput());


    private static String generateLabel(IntStream stream){
        return stream.mapToObj(Integer::toString).collect(Collectors.joining(" "));
    }

    private static Optional<IntStream> getGenerator(List<StreamGenerator> generators, String time){
        return generators.stream()
                .filter(generator -> generator.test(time))
                .map(generator -> generator.apply(time))
                .findFirst();
    }

    private final static Function<String, String> calculateCommand = input -> input;

    private final static Function<String, String> calculateMinutes = minutes -> {
        IntStream intervals = getGenerator(timeGenerators, minutes)
                .orElseGet(() -> IntStream.range(0, 59));
        return generateLabel(intervals);
    };

    private final static Function<String, String> calculateHours = hours -> {
        IntStream intervals = getGenerator(timeGenerators, hours)
                .orElseGet(() -> IntStream.range(0, 24));

        return generateLabel(intervals);
    };

    private final static Function<String, String> calculateMonth = months -> {
        IntStream intervals = getGenerator(mouthsGenerators, months)
                .orElseGet(() -> IntStream.range(1, 13));

        return generateLabel(intervals);
    };

    private final static Function<String, String> calculateDays = day -> {
        IntStream intervals = getGenerator(daysGenerators, day)
                .orElseGet(() -> IntStream.range(0, 31));

        return generateLabel(intervals);
    };

    private final static Function<String, String> calculateDayOfWeek = day -> {
        IntStream intervals = getGenerator(daysGenerators, day)
                .orElseGet(() -> IntStream.range(1, 8));

        return generateLabel(intervals);
    };

    public static final Map<CronInputParam, Function<String, String>> GENERATORS = Map.of(
            MINUTES, calculateMinutes,
            HOURS, calculateHours,
            DAYS, calculateDays,
            MONTH, calculateMonth,
            DAY_OF_WEEK, calculateDayOfWeek,
            COMMAND, calculateCommand
    );

}
