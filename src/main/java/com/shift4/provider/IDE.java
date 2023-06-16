package com.shift4.provider;

import static com.shift4.validator.CommonValidator.isNotEquals;
import static com.shift4.validator.CommonValidator.validateInputParam;

public class IDE implements Environment{

    @Override
    public String getInputParam(String[] arg) {
        validateInputParam(isNotEquals, arg.length, 6,  "Incorrect number of params");
        return String.join(" ", arg);
    }

    @Override
    public void print(String input) {
        System.out.println(input);
    }
}
