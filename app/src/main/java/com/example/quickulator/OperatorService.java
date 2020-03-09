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
    private SimpleCalcController simpleCalcController;

    public OperatorService() {
        equation = SimpleEquation.getInstance();
        inputHelper = InputHelper.getInstance();
        simpleCalcController = new SimpleCalcController();
    }

    public int operatorHandler(Operator operator) {
        int response = 1;
        switch (equation.getArgumentList().size()) {
            case 0:
                response = -1;
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
