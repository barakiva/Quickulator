package com.example.quickulator.model;

public class SimpleEquation {
    private static SimpleEquation instance = null;
    private double leftSide;
    private double rightSide;
    private Operator operator;
    private EquationState state;
    private SimpleEquation(){
        state = EquationState.VIRGIN;
    }

    public static SimpleEquation getInstance() {
        if(instance == null){
            instance = new SimpleEquation();
        }
        return instance;
    }

    public double getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(double leftSide) {
        this.leftSide = leftSide;
    }

    public double getRightSide() {
        return rightSide;
    }

    public void setRightSide(double rightSide) {
        this.rightSide = rightSide;
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
}
