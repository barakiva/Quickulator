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
    public StringBuilder getDigitInput() {
        return digitInput;
    }
}
