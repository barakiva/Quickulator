package com.example.quickulator;

import android.content.Context;

import com.example.quickulator.model.Command;
import com.example.quickulator.model.InputHelper;
import com.example.quickulator.model.OperationResponse;
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
        boolean digitAfterDigitFollowingEquals =
                !equation.getResultList().isEmpty() && equation.getOperator() == null;
        if (digitAfterDigitFollowingEquals) {
            restartEquation();
        }
        inputHelper.appendNumber((num));
        updateEquationView();
    }
    public void operatorInputCatcher(Operator operator) throws Exception {
        inputHelper.setOperatorInput(operator);
        operatorResponseHandler(operatorService.operatorHandler());
        updateEquationView();
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
        updateEquationView();
    }
    private void restartEquation() {
        commandService.clearAll();
        updateEquationView();
    }

    //GUI
    public void operatorResponseHandler(OperationResponse response) {
        MainActivity act = (MainActivity) context;
        if (response == OperationResponse.ILLEGAL) {
            act.handleOperatorError();
        } else if (response == OperationResponse.OVERRIDE) {
            act.handleOperatorOverride();
        }
    }

    private void updateEquationView() {
        MainActivity act = (MainActivity) context;
        act.updateEquationView(equation);
    }

}
