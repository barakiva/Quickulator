package com.example.quickulator;

import android.util.Log;

import com.example.quickulator.model.EquationState;
import com.example.quickulator.model.InputHelper;
import com.example.quickulator.model.Operator;
import com.example.quickulator.model.SimpleEquation;

public class CommandService {
    private SimpleEquation equation;
    public CommandService() {
        equation = SimpleEquation.getInstance();
    }

    public void resolveEquation() {

    }
    public void clearAll() {
        equation.getArgumentList().clear();
        equation.getResultList().clear();
        equation.setState(EquationState.VIRGIN);
        equation.setOperator(null);
    }
    public void undoEntry() {
        InputHelper.getInstance().clearEntry();
    }

//    private void equalsHandlerHack() {
//        if(! (equation.getOperator() == null || equation.getOperator() == Operator.CONSUMED)) {
//            operatorInputCatcher(equation.getOperator());
//        }
//        Log.d("error", "cant resolve operation");
//    }
}
