package com.shift4.provider;

import static com.shift4.validator.CommonValidator.*;

public class LocalMachine implements Environment {
    @Override
    public String getInputParam(String[] arg) {
        validateInputParam(isLessThan, arg.length, 1,  "Incorrect number of params");
        return String.join(" ", arg);
    }

    @Override
    public void print(String input) {
        System.out.println(input);
    }
}
