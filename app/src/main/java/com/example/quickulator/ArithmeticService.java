package com.example.quickulator;

import android.util.Log;

import com.example.quickulator.model.EquationState;
import com.example.quickulator.model.SimpleEquation;

import static com.example.quickulator.model.Operator.CONSUMED;

public class ArithmeticService {
    private static ArithmeticService instance;
    private final static String TAG = "ArtService";
    private ArithmeticService() { }
    public static ArithmeticService getInstance() {
        if (instance == null) {
           instance = new ArithmeticService();
        }
        return instance;
    }
    private double add(SimpleEquation equation) {
            return equation.getArgumentList().get(0) + equation.getArgumentList().get(1);
    }

    private double subtract(SimpleEquation equation) {
           return equation.getArgumentList().get(0) - equation.getArgumentList().get(1);
    }

    private double multiply(SimpleEquation equation) {
           return equation.getArgumentList().get(0) * equation.getArgumentList().get(1);
    }

    private double divide(SimpleEquation equation) {
           return equation.getArgumentList().get(0) / equation.getArgumentList().get(1);
    }

    public void arithmeticResolver(SimpleEquation equation) {
        double result = 0;
        printEquationArguments(equation);
        switch (equation.getOperator()) {
            case ADDITION:
                result = add(equation);
                break;
            case SUBTRACTION:
                result = subtract(equation);
                break;
            case MULTIPLICATION:
                result = multiply(equation);
                break;
            case DIVISION:
                result = divide(equation);
                break;
        }
        updateEquation(equation, result);
    }
    private void updateEquation(SimpleEquation equation , double result) {
        equation.setOperator(CONSUMED);
        equation.setState(EquationState.RESOLVED);

        equation.getArgumentList().clear();
        equation.getResultList().add(result);
        equation.getArgumentList().add(result);
        printEquationResult(equation);
    }

    private void printEquationArguments(SimpleEquation equation) {
        Log.d(TAG ,"Left side is  :" + equation.getArgumentList().get(0) +
                   " right side is :  " + equation.getArgumentList().get(1));

    }
    private void printEquationResult(SimpleEquation equation) {
        Log.d(TAG , "Result is..." + equation.getResultList().get(
                equation.getResultList().size() - 1));
    }
}
