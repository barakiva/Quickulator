package com.example.quickulator;

import android.util.Log;

import com.example.quickulator.model.SimpleEquation;

public class ArithmeticService {
    public void add(SimpleEquation equation){
        printEquation(equation);
        equation.setLeftSide(equation.getLeftSide() + equation.getRightSide());
        printEquation(equation);
    }
    public void subtract(SimpleEquation equation) {
        equation.setLeftSide(equation.getLeftSide() - equation.getRightSide());
    }
    public void multiply(SimpleEquation equation) {
        equation.setLeftSide(equation.getLeftSide() * equation.getRightSide());
    }
    public void divide(SimpleEquation equation) {
        equation.setLeftSide(equation.getLeftSide() / equation.getRightSide());
    }
    private void printEquation(SimpleEquation equation) {
        Log.d("Previous", equation.getLeftSide() + " and  " + equation.getRightSide());
    }
}
