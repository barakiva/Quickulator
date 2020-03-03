package com.example.quickulator.model;

import java.util.ArrayList;
import java.util.List;

public class InputHelper {
    private static InputHelper instance = null;
    private List<Integer> digitInput;
    private InputHelper() {digitInput = new ArrayList<>();}
    public static InputHelper getInstance() {
        if (instance == null) {
            instance = new InputHelper();
        }
        return instance;
    }
    public List<Integer> getDigitInput() {
        return digitInput;
    }

    public void setDigitInput(List<Integer> digitInput) {
        this.digitInput = digitInput;
    }
}
