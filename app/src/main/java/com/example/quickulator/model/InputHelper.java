package com.example.quickulator.model;

public class InputHelper {
    private static InputHelper instance = null;
    private StringBuilder digitInput;

    private InputHelper() {digitInput = new StringBuilder();}

    public static InputHelper getInstance() {
        if (instance == null) {
            instance = new InputHelper();
        }
        return instance;
    }

    public void appendNumber(double num) {
        digitInput.append(num);
    }
    public double buildNumber() {
        double num = Double.parseDouble(digitInput.toString());
        clearEntry();
        return num;
    }
    public void clearEntry() {
        digitInput.setLength(0);
    }
}
