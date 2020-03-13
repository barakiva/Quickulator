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
    private Operator operatorInput;

    private int ILLEGAL_OPERATION = -1;
    private int LEGAL_OPERATION = 1;
    private int OVERRIDE_OPERATOR = 2;

    public OperatorService() {
        equation = SimpleEquation.getInstance();
        inputHelper = InputHelper.getInstance();
        arithmeticService = ArithmeticService.getInstance();
    }

    public int operatorHandler() {
        operatorInput = inputHelper.getOperatorInput();
        int response = 0;
        response = operatorContextResolver();
        return response;
    }
    private int operatorContextResolver() {
        int response = 0;
        if (isEquationEmpty()) {
            if (anyPreviousDigitInput()) {
                loadLeftSide();
                response = LEGAL_OPERATION;
            } else {
                response = ILLEGAL_OPERATION;
            }
        } else {
            if (isAfterOperator()) {
                if (operatorInput == Operator.EQUALS) {
                    response = ILLEGAL_OPERATION;
                } else {
                    response = OVERRIDE_OPERATOR;
                }
            } else {
                //Operator is legal. Decide where to assign
                if (equation.getArgumentList().size() == 1) {
                    loadLeftSide();
                    response = LEGAL_OPERATION;
                } else {
                    //Operator came after second argument. Is it equals?
                    if (operatorInput == Operator.EQUALS) {
                        resolveEquation();
                        response = LEGAL_OPERATION;
                    } else {
                        loadRightSide();
                        equation.setOperator(operatorInput);
                        response = LEGAL_OPERATION;
                    }
                }
            }
        }
        return response;
    }
    private int operatorResolver() {
        int response = 0;
        switch (equation.getOperator()) {
            case EQUALS:
                response = equalsResolver();
                break;
            case ADDITION:
            case DIVISION:
            case SUBTRACTION:
            case MULTIPLICATION:
                if (isLegalOperation()) {
                    howToAssign();
                } else {
                    response = ILLEGAL_OPERATION;
                }
                break;
        }
        return response;
    }
    private int equalsResolver() {
        if (canEquationBeResolved()) {
            resolveEquation();
            return LEGAL_OPERATION;
        } else {
            return ILLEGAL_OPERATION;
        }
    }
    private void howToAssign() {
        if (operatorInput == Operator.EQUALS) {
            resolveEquation();

        }
        if (equation.getArgumentList().size() == 0) {
            loadLeftSide();
        } else {
            loadRightSide();
        }
    }


    //Resolution
    private void loadLeftSide() {
        equation.setOperator(operatorInput);
        equation.getArgumentList().add(inputHelper.buildNumber());
    }
    private void loadRightSide() {
        equation.getArgumentList().add(inputHelper.buildNumber());
        resolveEquation();
        equation.setOperator(operatorInput);;
    }
    private void resolveEquation() {
        arithmeticService.arithmeticResolver(equation);
    }

    // Sanitization
    private boolean canEquationBeResolved() {
        return equation.getArgumentList().size() == 2;
    }
    private boolean isLegalOperation() {
        return anyPreviousDigitInput();
    }
    private boolean anyPreviousDigitInput() {
        return inputHelper.getDigitInput().length() > 0;
    }
    private boolean isEquationEmpty() {
        return equation.getArgumentList().size() == 0;
    }
    private boolean isAfterOperator() {
        return !(equation.getOperator() != null && equation.getOperator() != Operator.CONSUMED);
    }
}
