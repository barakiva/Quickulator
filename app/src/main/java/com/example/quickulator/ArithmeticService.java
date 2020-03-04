package com.example.quickulator;

import android.util.Log;

import com.example.quickulator.model.EquationState;
import com.example.quickulator.model.SimpleEquation;

public class ArithmeticService {
    public void add(SimpleEquation equation) {
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

    public void arithmeticResolver(SimpleEquation equation) {
        switch (equation.getOperator()) {
            case ADDITION:
                add(equation);
                break;
            case SUBTRACTION:
                subtract(equation);
                break;
            case MULTIPLICATION:
                multiply(equation);
                break;
            case DIVISION:
                divide(equation);
                break;
        }
        equation.setOperator(null);
        equation.setState(EquationState.RESOLVED);
    }

    private void printEquation(SimpleEquation equation) {
        Log.d("Previous", equation.getLeftSide() + " and  " + equation.getRightSide());

    }
}
