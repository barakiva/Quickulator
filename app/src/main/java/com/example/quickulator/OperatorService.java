package com.example.quickulator;

import com.example.quickulator.model.InputHelper;
import com.example.quickulator.model.InputContext;
import com.example.quickulator.model.OperationResponse;
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

    public OperatorService() {
        equation = SimpleEquation.getInstance();
        inputHelper = InputHelper.getInstance();
        arithmeticService = ArithmeticService.getInstance();
    }

    public OperationResponse operatorHandler() throws Exception {
        operatorInput = inputHelper.getOperatorInput();
        return operatorContextResolver();
    }


    private InputContext determineInputState() throws Exception {
        // Conditional components
        boolean operatorAfterNumber = !isEquationEmpty() && !isAfterOperator();
        boolean fullEquation = equation.getArgumentList().size() == 1 && anyDigitInput();
        // Conditional end results
        boolean firstNumberBuilt = equation.getArgumentList().size() == 1 && !anyDigitInput() && operatorAfterNumber;
        boolean secondNumberBuilt = fullEquation && operatorAfterNumber;
        boolean equalsAfterFullEquation = operatorInput == Operator.EQUALS && fullEquation;
        boolean illegalEqualsOperation = isAfterOperator() && operatorInput == Operator.EQUALS;
        boolean emptyEquationAndNoInput = equation.getArgumentList().isEmpty() && !anyDigitInput();

        if (firstNumberBuilt)
            return InputContext.OPERATOR_ASSIGNABLE;
        if (secondNumberBuilt)
            return operatorInput == Operator.EQUALS ?
                    InputContext.EQUALS_RESOLVE : InputContext.OPERATOR_RESOLVE;
        if (equalsAfterFullEquation && canEquationBeResolved())
            return InputContext.EQUALS_RESOLVE;
        if (illegalEqualsOperation)
            return InputContext.OPERATOR_ILLEGAL;

        return emptyEquationAndNoInput ? InputContext.OPERATOR_ILLEGAL : InputContext.OPERATOR_ASSIGNABLE;
    }

    private OperationResponse operatorContextResolver() throws Exception {
        OperationResponse response = null;
        switch (determineInputState()) {
            case OPERATOR_ASSIGNABLE:
                response  = assignOperator();
                break;
            case EQUALS_RESOLVE:
                response = resolveEquation();
                break;
            case OPERATOR_RESOLVE:
                response = resolveOperator();
                break;
            case OPERATOR_OVERRIDABLE:
                response = overrideOperator();
                break;
            case OPERATOR_ILLEGAL:
                response = operatorNotLegal();
                break;
        }
        return response;
    }
    private OperationResponse assignOperator() {
        if (isNovelEquation()) {
            equation.getArgumentList().add(inputHelper.buildNumber());
        }
        equation.setOperator(operatorInput);
        return OperationResponse.LEGAL;
    }
    private OperationResponse resolveEquation() {
        equation.getArgumentList().add(inputHelper.buildNumber());
        arithmeticService.resolveEquation(equation);
        return OperationResponse.LEGAL;
    }
    private OperationResponse resolveOperator() {
        equation.getArgumentList().add(inputHelper.buildNumber());
        arithmeticService.resolveEquation(equation);
        equation.setOperator(operatorInput);
        return OperationResponse.LEGAL;
    }
    private OperationResponse overrideOperator() {
        equation.setOperator(operatorInput);
        return OperationResponse.OVERRIDE;
    }
    private OperationResponse operatorNotLegal() {
        return OperationResponse.ILLEGAL;
    }
    // Sanitization
    private boolean canEquationBeResolved() {
        return equation.getArgumentList().size() == 2;
    }
    private boolean anyDigitInput() {
        return inputHelper.getDigitInput().length() > 0;
    }
    private boolean isEquationEmpty() {
        return equation.getArgumentList().isEmpty() && !anyDigitInput();
    }
    private boolean isAfterOperator() {
        boolean isEquationResolved = equation.getResultList().isEmpty();
        return equation.getOperator() != null && !isEquationResolved;
    }
    private boolean isNovelEquation() {
        return equation.getArgumentList().isEmpty();
    }
}
