package com.shift4.cron;

import com.shift4.mapper.CronInputParam;
import com.shift4.parser.InputStringParser;
import com.shift4.provider.Environment;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.IntStream;

import static com.shift4.mapper.CronMapper.GENERATORS;

public class Cron {
    private static final String WHITESPACE = " ";

    public void print(String[] args, Environment environment){
        String inputParam = environment.getInputParam(args);
        Map<CronInputParam, String> parserOutput = InputStringParser.parse(inputParam);
        parserOutput.keySet().stream().sorted(Comparator.comparingInt(CronInputParam::getIndex))
                .forEachOrdered(cronInputParam -> {
                    String input = parserOutput.get(cronInputParam);
                    String output = GENERATORS.get(cronInputParam).apply(input);
                    environment.print(getFormattedString(cronInputParam, output));
                });
    }

    private String getFormattedString(CronInputParam input, String outputCalculation){
        String label = padding(input.getLabel(), input.getWhitespaceBetweenColumn());
        return String.format("%s %s", label, outputCalculation);
    }

    private String padding(String text, int length) {
        return IntStream.range(0, length)
                .mapToObj(element -> WHITESPACE)
                .reduce(text, (init, element) -> init += element);
    }

}
