package com.example.quickulator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quickulator.model.SimpleEquation;

public class SimpleEquationViewModel extends ViewModel {
    private MutableLiveData<SimpleEquation> equation;

    public LiveData getEquation() {
        if (equation == null) {
            equation = new MutableLiveData<>();
            equation.setValue(SimpleEquation.getInstance());
        }
        return equation;
    }
    public void updateEquation(SimpleEquation equation) {
        this.equation.setValue(equation);
    }
}
