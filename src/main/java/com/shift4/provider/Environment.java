package com.shift4.provider;

public interface Environment {
    String CLI = "CLI";
    String DOCKER = "DOCKER";
    String IDE = "IDE";

    String getInputParam(String[] arg);
    void print(String input);
}
