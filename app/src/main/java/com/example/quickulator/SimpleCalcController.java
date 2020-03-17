package com.example.quickulator;

import android.content.Context;

import com.example.quickulator.model.Command;
import com.example.quickulator.model.InputHelper;
import com.example.quickulator.model.Operator;
import com.example.quickulator.model.SimpleEquation;

public class SimpleCalcController {
    private OperatorService operatorService;
    private SimpleEquation equation;
    private InputHelper inputHelper;
    private CommandService commandService;
    private Context context;

    public SimpleCalcController() {
        operatorService = new OperatorService();
        equation = SimpleEquation.getInstance();
        inputHelper = InputHelper.getInstance();
        commandService = new CommandService();
    }

    public void init(Context context) {
        this.context = context;
    }

    //TODO Replace with listener. Input catching should not also be responsible for event handling
    //based on input
    //**Input Handlers
    public void numberInputHandler(double num) {
        boolean isEquationResolved = equation.getResultList().isEmpty();
        if (isEquationResolved) {
            restartEquation();
        }
        inputHelper.appendNumber((num));
    }
    public void operatorInputCatcher(Operator operator) {
        MainActivity mainActivity = (MainActivity) context;
        inputHelper.setOperatorInput(operator);
        int handlerResponse = operatorService.operatorHandler();
        if (handlerResponse == -1) {
            mainActivity.handleOperatorError();
        }
    }
    public void commandHandler(Command command) {
        switch (command){
            case UNDO:
                commandService.undoEntry();
                break;
            case CLEAR:
                commandService.clearAll();
                break;
        }
    }
    private void restartEquation() {
        commandService.clearAll();
    }

    //GUI
    public void operatorErrorHandler() {
        MainActivity act = (MainActivity) context;
        act.handleOperatorError();
    }

    private void updateResult() {
        MainActivity act = (MainActivity) context;
        act.displayResult(equation);
    }

}
