package com.example.quickulator;

import android.content.Context;

import com.example.quickulator.model.InputHelper;
import com.example.quickulator.model.Operator;
import com.example.quickulator.model.SimpleEquation;

public class OperatorService {
    private SimpleEquation equation;
    private InputHelper inputHelper;
    private SimpleCalcController simpleCalcController;

    private Context context;
    public OperatorService(Context context) {
        this.context = context;
        equation = SimpleEquation.getInstance();
        inputHelper = InputHelper.getInstance();
        simpleCalcController = SimpleCalcController.getInstance();
    }
    public void operatorHandler(Operator operator) {
        switch (equation.getArgumentList().size()) {
            case 0:
                operatorErrorHandler(operator);
                break;
            case 1:
                loadLeftSide(operator);
                break;
            case 2:
                loadRightSide(operator);
                break;
        }
    }
    public void operatorErrorHandler(Operator operator) {
        MainActivity main = (MainActivity) context;
        main.displayOperatorError();
    }
    private void loadLeftSide(Operator operator) {
        equation.setOperator(operator);
        equation.getArgumentList().add(inputHelper.buildNumber());
    }
    private void loadRightSide(Operator operator) {
        equation.getArgumentList().add(inputHelper.buildNumber());
        simpleCalcController.resolveEquation();
        equation.setOperator(operator);;
    }
}
