package com.example.quickulator;

import android.graphics.Path;

import com.example.quickulator.model.InputHelper;
import com.example.quickulator.model.Operator;
import com.example.quickulator.model.SimpleEquation;

public class OperatorService {
    //Lessons
    // services should not know or call controllers. They should only return the output necessary for the controllers to do whatever
    //needs to be done
    private SimpleEquation equation;
    private InputHelper inputHelper;
    private ArithmeticService arithmeticService;

    public OperatorService() {
        equation = SimpleEquation.getInstance();
        inputHelper = InputHelper.getInstance();
        arithmeticService = ArithmeticService.getInstance();
    }

    public int operatorHandler(Operator operator) {
        int response = 1;
        if (operator == Operator.EQUALS)     {

        }
        whereToAssign(operator);
        return response;
    }
    public int isLegalOperation(Operator operator) {
        final int OPERATOR_ERROR = -1;
        final int LEGAL_OPERATION = 1;

        if (!anyPreviousDigitInput()) {
            return OPERATOR_ERROR;
        }
        whereToAssign(operator);
        return LEGAL_OPERATION;

        switch (equation.getArgumentList().size()) {
            case 0:
                if(anyPreviousDigitInput()) {
                    loadLeftSide(operator);
                } else {
                }
                break;
            case 1:
                loadRightSide(operator);
                break;
        }
        return response;
    }
    private void whereToAssign(Operator operator) {

    }
    private boolean anyPreviousDigitInput() {
        return inputHelper.getDigitInput().length() > 0;
    }
    private void loadLeftSide(Operator operator) {
        equation.setOperator(operator);
        equation.getArgumentList().add(inputHelper.buildNumber());
    }
    private void loadRightSide(Operator operator) {
        equation.getArgumentList().add(inputHelper.buildNumber());
        arithmeticService.arithmeticResolver(equation);
        equation.setOperator(operator);;
    }
}
