package com.example.quickulator;

import android.util.Log;

import com.example.quickulator.model.EquationState;
import com.example.quickulator.model.SimpleEquation;

import static com.example.quickulator.model.Operator.CONSUMED;

public class ArithmeticService {
    public void add(SimpleEquation equation) {
        equation.getResultList().add(
                equation.getArgumentList().get(0) + equation.getArgumentList().get(1)
        );
    }

    public void subtract(SimpleEquation equation) {
        equation.getResultList().add(
                equation.getArgumentList().get(0) - equation.getArgumentList().get(1)
        );    }

    public void multiply(SimpleEquation equation) {
        equation.getResultList().add(
                equation.getArgumentList().get(0) * equation.getArgumentList().get(1)
        );
    }

    public void divide(SimpleEquation equation) {
        equation.getResultList().add(
                equation.getArgumentList().get(0) / equation.getArgumentList().get(1)
        );
    }

    public void arithmeticResolver(SimpleEquation equation) {
        printEquation(equation);
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
        equation.setOperator(CONSUMED);
        equation.setState(EquationState.RESOLVED);
        equation.getArgumentList().clear();
    }

    private void printEquation(SimpleEquation equation) {
        Log.d("Left side is  :", equation.getArgumentList().get(0) +
                   " right side is :  " + equation.getArgumentList().get(1));

    }
}
