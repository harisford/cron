package com.shift4.parser;

import com.shift4.mapper.CronInputParam;

import java.util.HashMap;
import java.util.Map;

import static com.shift4.validator.CommonValidator.validateInputParam;
import static com.shift4.validator.CommonValidator.isNotEquals;
import static com.shift4.validator.TimeValidators.VALIDATORS;

public class InputStringParser {
    public final static String INPUT_ARG_REGEXP = " ";

    static public Map<CronInputParam, String> parse(String arg){
        String[] cronParams = arg.split(INPUT_ARG_REGEXP);
        validateInputParam(isNotEquals, cronParams.length, 6,  "Incorrect number of params");
        Map<CronInputParam, String> inputParams = new HashMap<>();

        for(int index = 0; index < cronParams.length; index++){
            CronInputParam cronInputParam = CronInputParam.valueOfIndex(index);
            String time = cronParams[index];
            assert cronInputParam != null;
            validateInputParam(VALIDATORS.get(cronInputParam).negate(), time,
                    String.format("Incorrect value %s in field %s ", time, cronInputParam.getLabel()));
            inputParams.put(CronInputParam.valueOfIndex(index), cronParams[index]);
        }

        return inputParams;
    }
}
