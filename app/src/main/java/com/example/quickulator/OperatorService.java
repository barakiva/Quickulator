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
        equation.getArgumentList().add(inputHelper.buildNumber());
        equation.setOperator(operatorInput);
        return OperationResponse.LEGAL;
    }
    private OperationResponse resolveEquation() {
        arithmeticService.arithmeticResolver(equation);
        return OperationResponse.LEGAL;
    }
    private OperationResponse resolveOperator() {
        equation.getArgumentList().add(inputHelper.buildNumber());
        resolveEquation();
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

    private InputContext determineInputState() throws Exception {
        // Conditional components
        boolean operatorAfterNumber = !isEquationEmpty() && !isAfterOperator();
        boolean fullEquation = equation.getArgumentList().size() == 2;
        // Conditional end results
        boolean firstNumberBuilt = equation.getArgumentList().size() == 1 && operatorAfterNumber;
        boolean secondNumberBuilt = fullEquation && operatorAfterNumber;
        boolean equalsAfterFullEquation = fullEquation && operatorInput == Operator.EQUALS;
        boolean illegalEqualsOperation = isAfterOperator() && operatorInput == Operator.EQUALS;

        if (firstNumberBuilt)
            return InputContext.OPERATOR_ASSIGNABLE;
        if (secondNumberBuilt)
            return operatorInput == Operator.EQUALS ?
                    InputContext.EQUALS_RESOLVE : InputContext.OPERATOR_RESOLVE;
        if (equalsAfterFullEquation && canEquationBeResolved())
            return InputContext.EQUALS_RESOLVE;
        if (isEquationEmpty())
            return anyPreviousDigitInput() ?
                    InputContext.OPERATOR_ASSIGNABLE : InputContext.OPERATOR_ILLEGAL;
        if (illegalEqualsOperation)
            return InputContext.OPERATOR_ILLEGAL;

        throw new Exception();
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
        boolean isEquationResolved = equation.getResultList().isEmpty();
        return equation.getOperator() != null && !isEquationResolved;
    }
}
