package com.example.quickulator;

import com.example.quickulator.model.Equation;
import com.example.quickulator.model.Operator;

public class CalculatorLogic {
    private static CalculatorLogic instance;
    private CalculatorLogic(){

    }
    public static CalculatorLogic getInstance(){
        if (instance!= null) {
            instance = new CalculatorLogic();
        }
        return  instance;
    }
    public void digitCatcher(double input) {
        Equation.numberList.add(input);
    }
    public void operatorCatcher(Operator operator) {
        Equation.operatorList.add(operator);
    }
//    public double resolveEquation(){
//
//    }
}
