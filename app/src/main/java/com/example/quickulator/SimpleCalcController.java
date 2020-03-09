package com.example.quickulator;

import android.content.Context;
import android.util.Log;

import com.example.quickulator.model.Command;
import com.example.quickulator.model.EquationState;
import com.example.quickulator.model.InputHelper;
import com.example.quickulator.model.Operator;
import com.example.quickulator.model.SimpleEquation;

public class SimpleCalcController {
    private ArithmeticService arithmeticService;
    private OperatorService operatorService;
    private SimpleEquation equation;
    private InputHelper inputHelper;
    private CommandService commandService;
    private Context context;

    public SimpleCalcController() {
        operatorService = new OperatorService();
        arithmeticService = new ArithmeticService();
        equation = SimpleEquation.getInstance();
        inputHelper = InputHelper.getInstance();
        commandService = new CommandService();
    }

    public void init(Context context) {
        this.context = context;
    }

    public void numberInputHandler(double num) {
        if (equation.getState() == EquationState.RESOLVED) {
            restartEquation();
        }
        inputHelper.appendNumber((num));
    }
    //TODO Replace with listener. Input catching should not also be responsible for event handling
    //based on input
    public void operatorInputCatcher(Operator operator) {
        operatorService.operatorHandler(operator);
    }
    public void resolveEquation() {
        arithmeticService.arithmeticResolver(equation);
    }
    public void commandHandler(Command command) {
        switch (command){
            //TODO shitty hack
            case EQUALS:
//                equalsHandlerHack();
                Log.d("not yet", "coming soon");
                break;
            case UNDO:
                commandService.undoEntry();
                break;
            case CLEAR:
                commandService.clearAll();
                break;
        }
    }

    private void equalsHandlerHack() {
        if(! (equation.getOperator() == null || equation.getOperator() == Operator.CONSUMED)) {
            operatorInputCatcher(equation.getOperator());
        }
        Log.d("error", "cant resolve operation");
    }
    private void restartEquation() {
        commandService.clearAll();
    }
    //GUI
    public void operatorErrorHandler() {
        MainActivity act = (MainActivity) context;
        act.displayOperatorError();
    }

    private void updateResult() {
        MainActivity act = (MainActivity) context;
        act.displayResult(equation);
    }

}
