package com.shift4.error;

public class InvalidInputParameterException extends RuntimeException {

    public InvalidInputParameterException(String message) {
        super(message);
    }

    public static InvalidInputParameterException of(String message){
        return new InvalidInputParameterException(message);
    }
}
