package com.shift4.validator;

import com.shift4.mapper.CronInputParam;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static com.shift4.mapper.CronInputParam.*;
import static com.shift4.validator.CommonValidator.isCommonApproach;
import static com.shift4.validator.CommonValidator.isNotEmpty;

public class TimeValidators {
    private final static int DAYS_IN_MOUTH = 31;
    private final static int NUMBER_OF_MOUTHS = 12;
    private final static String SET_OF_SPECIAL_CHARACTERS = "[-,]";

    private final static Pattern IS_NUMBER = Pattern.compile("^\\d+$");
    private final static Pattern SEPARATORS = Pattern.compile("[,|-]");

    private final static Pattern DAY_OF_WEEK_REGEX = Pattern.compile("^[1-7]$");
    private final static Pattern TIME_REGEX = Pattern.compile("^([0-5]?[0-9])$");
    private final static Pattern TIME_INTERVAL_REGEX = Pattern.compile("(^\\*/([0-5]?[0-9])$)");

    private static final Predicate<String> timeIntervalRegex = hours -> TIME_INTERVAL_REGEX.matcher(hours).find();
    private static final Predicate<String> dayOfWeekRegex = hours -> DAY_OF_WEEK_REGEX.matcher(hours).find();

    private static final Predicate<String> isNumber = number -> IS_NUMBER.matcher(number).find();
    private static final Predicate<String> timeRegex = hours -> TIME_REGEX.matcher(hours).find();
    private static final Predicate<String> separators = hours -> SEPARATORS.matcher(hours).find();

    private static final Predicate<String> dayOfMount = day ->
        isNumber.test(day) && Integer.parseInt(day) <= DAYS_IN_MOUTH;

    private static final Predicate<String> mountAsNumber = day ->
            isNumber.test(day) && Integer.parseInt(day) <= 12 && Integer.parseInt(day) >= 1;

    private static final Function<String, List<Integer>> rangeTime = time ->
    {
        String[] days = time.split(SET_OF_SPECIAL_CHARACTERS);
        return List.of(Integer.parseInt(days[0]), Integer.parseInt(days[1]));
    };

    private static final Predicate<String> rangeOfDays = day ->
    {
        List<Integer> days = rangeTime.apply(day);
        if(checkDayRange(days.get(0), DAYS_IN_MOUTH + 1) && checkDayRange(days.get(1), DAYS_IN_MOUTH + 1)) {
            return days.get(0) < days.get(1);
        }
        return false;
    };

    private static final Predicate<String> rangeOfMouth = mouth ->
    {
        List<Integer> mouths = rangeTime.apply(mouth);
        if(checkDayRange(mouths.get(0), NUMBER_OF_MOUTHS) && checkDayRange(mouths.get(1), NUMBER_OF_MOUTHS)) {
            return mouths.get(0) < mouths.get(1);
        }
        return false;
    };

    private static final Predicate<String> rangeOfDaysOfWeek = mouth ->
    {
        List<Integer> mouths = rangeTime.apply(mouth);
        if(checkDayRange(mouths.get(0), 7) && checkDayRange(mouths.get(1), 8)) {
            return mouths.get(0) < mouths.get(1);
        }
        return false;
    };

    private static boolean checkDayRange(int day, int moreThan){
        return 0 < day && day < moreThan;
    }

    private static final Predicate<String> command = isNotEmpty;

    private static final Predicate<String> dayOfWeek = week ->
            isNotEmpty.and(isCommonApproach.or(separators.and(rangeOfDaysOfWeek).or(dayOfWeekRegex))).test(week);

    private static final Predicate<String> day = day ->
            isNotEmpty.and(isCommonApproach.or(dayOfMount.or(separators.and(rangeOfDays)))).test(day);

    private static final Predicate<String> mouth = day ->
            isNotEmpty.and(isCommonApproach.or(mountAsNumber.or(separators.and(rangeOfMouth)))).test(day);

    private static final Predicate<String> time = time -> isNotEmpty.and(
            isCommonApproach.or(timeRegex.or(timeIntervalRegex))).test(time);

    public static final Map<CronInputParam, Predicate<String>> VALIDATORS = Map.of(
            MINUTES, time,
            HOURS, time,
            DAYS, day,
            MONTH, mouth,
            DAY_OF_WEEK, dayOfWeek,
            COMMAND, command
    );
}
