package com.example.quickulator.model;

import android.util.Log;

public class InputHelper {
    private static InputHelper instance = null;
    private StringBuilder digitInput;
    private Operator operatorInput;
    private Command commandInput;

    private final static String TAG = "InputHelper";
    private InputHelper() {digitInput = new StringBuilder();}

    public static InputHelper getInstance() {
        if (instance == null) {
            instance = new InputHelper();
        }
        return instance;
    }

    public void appendNumber(double num) {
        digitInput.append((int) num);
    }
    public double buildNumber() {
        double num = Double.parseDouble(digitInput.toString());
        clearEntry();
        Log.i(TAG, "Built number is : " + num);
        return num;
    }
    public void clearEntry() {
        digitInput.setLength(0);
    }

    public StringBuilder getDigitInput() {
        return digitInput;
    }

    public Operator getOperatorInput() {
        return operatorInput;
    }

    public void setOperatorInput(Operator operatorInput) {
        this.operatorInput = operatorInput;
    }

    public Command getCommandInput() {
        return commandInput;
    }

    public void setCommandInput(Command commandInput) {
        this.commandInput = commandInput;
    }
}
