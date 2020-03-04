package com.example.quickulator;

import android.util.Log;

import com.example.quickulator.model.EquationState;
import com.example.quickulator.model.InputHelper;
import com.example.quickulator.model.Operator;
import com.example.quickulator.model.SimpleEquation;

public class SimpleCalcController {

    private ArithmeticService arithmeticService;
    private SimpleEquation equation = SimpleEquation.getInstance();
    private InputHelper inputHelper = InputHelper.getInstance();

    public void numberInputHandler(int num) {
        inputHelper.getDigitInput().append(num);
    }
    public void operatorInputHandler(Operator operator) {
        switch(equation.getState()){
            case VIRGIN:
                leftSideConclude(operator);
                break;
            case LEFT_ASSEMBLE:
                Log.i("info", "left ass");
                break;
            case RIGHT_ASSEMBLE:
            case RESOLVED:
                rightSideConclude(operator);
                break;
        }
    }
    private void leftSideConclude(Operator operator) {
        equation.setLeftSide(Double.parseDouble(inputHelper.toString()));
        equation.setOperator(operator);
        equation.setState(EquationState.RIGHT_ASSEMBLE);
    }
    private void rightSideConclude(Operator operator) {
        equation.setRightSide(Double.parseDouble(inputHelper.toString()));
        equation.setOperator(operator);
        arithmeticService.arithmeticResolver(equation);
    }


}
