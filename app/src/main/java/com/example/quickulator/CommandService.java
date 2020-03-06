package com.example.quickulator;

import com.example.quickulator.model.EquationState;
import com.example.quickulator.model.InputHelper;
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
}
