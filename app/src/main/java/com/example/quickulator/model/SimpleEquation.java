package com.example.quickulator.model;

import java.util.ArrayList;
import java.util.List;

public class SimpleEquation {
    private static SimpleEquation instance = null;
    private List<Double> argumentList;
    private List<Double> resultList;
    private Operator operator;
    private Command command;
    private EquationState state;
    private SimpleEquation(){
        resultList = new ArrayList<>();
        argumentList = new ArrayList<>();
    }

    public static SimpleEquation getInstance() {
        if(instance == null){
            instance = new SimpleEquation();
        }
        return instance;
    }

    public List<Double> getArgumentList() {
        return argumentList;
    }

    public void setArgumentList(List<Double> argumentList) {
        this.argumentList = argumentList;
    }

    public List<Double> getResultList() {
        return resultList;
    }

    public void setResultList(List<Double> resultList) {
        this.resultList = resultList;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) { this.operator = operator;
    }
    public EquationState getState() {
        return state;
    }

    public void setState(EquationState state) {
        this.state = state;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
