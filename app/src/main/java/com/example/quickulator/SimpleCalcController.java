package com.example.quickulator;

import com.example.quickulator.model.InputHelper;
import com.example.quickulator.model.Operator;
import com.example.quickulator.model.SimpleEquation;

public class SimpleCalcController {

    private ArithmeticService arithmeticService;
    public void arithmeticResolver(SimpleEquation equation) {
         switch (equation.getOperator()){
             case ADDITION:
                 arithmeticService.add(equation);
                 break;
             case SUBTRACTION:
                 arithmeticService.subtract(equation);
                 break;
             case MULTIPLICATION:
                 arithmeticService.multiply(equation);
                 break;
             case DIVISION:
                 arithmeticService.divide(equation);
                 break;
         }
    }
    public void numberInputHandler(int num) {
        InputHelper.getInstance().getDigitInput().add(num);
    }
    public void operatorInputHandler(Operator operator) {
        SimpleEquation.getInstance().setOperator(operator);
    }
}
