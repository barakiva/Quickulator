package com.example.quickulator;

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
        arithmeticService = new ArithmeticService();
    }

    public int operatorHandler(Operator operator) {
        int OPERATOR_ERROR = -1;
        int response = 1;
        switch (equation.getArgumentList().size()) {
            case 0:
                if(anyPreviousDigitInput()) {
                    loadLeftSide(operator);
                } else {
                    response = OPERATOR_ERROR;
                }
                break;
            case 1:
                loadLeftSide(operator);
                break;
            case 2:
                loadRightSide(operator);
                break;
        }
        return response;
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
