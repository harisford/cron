package com.shift4.provider;

import java.util.Scanner;

public class Docker implements Environment {

    @Override
    public String getInputParam(String[] arg) {
        System.out.println("Please add cron input parameters: ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    @Override
    public void print(String input) {
        System.out.println(input);
    }
}
