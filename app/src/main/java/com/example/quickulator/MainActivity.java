package com.example.quickulator;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.quickulator.model.Command;
import com.example.quickulator.model.Operator;
import com.example.quickulator.model.SimpleEquation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    // Binding
    private Set<ImageButton> operatorSet = new HashSet<>();
    private Set<ImageButton> commandSet = new HashSet<>();
    private Set<Button> numPad = new HashSet<>();
    //Equation Screen
   private TextView resultView;
   private TextView expressionView;
    //Controller
    private SimpleCalcController simpleCalcController;
    //MVVM
    SimpleEquationViewModel simpleEquationViewModel;
    //Debug Tags
    private static final String NUM_PAD = "NumPad";
    private static final String OPERATOR = "Operator";
    private static final String COMMAND = "Command";
    private final String EQUATION = "EQUATION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleCalcController = new SimpleCalcController();
        simpleCalcController.init(this);

        //bind commands
        commandSet.add(findViewById(R.id.clearAllBtn));
        commandSet.add(findViewById(R.id.undoBtn));
        commandBinder();
        //bind operators
        operatorSet.add(findViewById(R.id.additionBtn));
        operatorSet.add(findViewById(R.id.subtractionBtn));
        operatorSet.add(findViewById(R.id.multiplicationBtn));
        operatorSet.add(findViewById(R.id.divisionBtn));
        operatorSet.add(findViewById(R.id.equalsBtn));
        operatorBinder();
        //bind numpad
        numPad.add(findViewById(R.id.btn0));
        numPad.add(findViewById(R.id.btn1));
        numPad.add(findViewById(R.id.btn2));
        numPad.add(findViewById(R.id.btn3));
        numPad.add(findViewById(R.id.btn4));
        numPad.add(findViewById(R.id.btn5));
        numPad.add(findViewById(R.id.btn6));
        numPad.add(findViewById(R.id.btn7));
        numPad.add(findViewById(R.id.btn8));
        numPad.add(findViewById(R.id.btn9));
        numpadBinder();
        //Equation screen
        resultView = findViewById(R.id.expressionView);
        expressionView = findViewById(R.id.expressionView);
        //MVVM
        simpleEquationViewModel = new ViewModelProvider(this).get(SimpleEquationViewModel.class);

        final Observer<SimpleEquation> equationObserver = equation -> {
            Log.i(EQUATION, "Equation has changed!");
            List<Double> results = equation.getResultList();
            if (!results.isEmpty()) {
                resultView.setText(Double.toString(results.get(results.size() - 1)));
            }
        };

        simpleEquationViewModel.getEquation().observe(this,equationObserver);


    }
    private  void commandBinder() {
        for (ImageButton btn : commandSet) {
            btn.setOnClickListener(v -> {
                Command command = Command.valueOf(btn.getTag().toString());
                Log.i(COMMAND, command.name());
                simpleCalcController.commandHandler(command);
            });
        }
    }
    private void numpadBinder() {
        for (Button btn : numPad) {
            btn.setOnClickListener(v -> {
                double num = Integer.parseInt(btn.getText().toString());
                Log.i(NUM_PAD, String.valueOf(num));
                simpleCalcController.numberInputHandler(num);
            });
        }
    }
    private void operatorBinder() {
        for (ImageButton btn : operatorSet) {
            btn.setOnClickListener(v -> {
                Operator operator = Operator.valueOf(btn.getTag().toString());
                Log.i(OPERATOR, operator.name());
                try {
                    simpleCalcController.operatorInputCatcher(operator);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }

//    private String buildEquationText(SimpleEquation equation) {
//        if (equation.getResultList().isEmpty()) {
//
//        }
//    }
//    private String buildExpression(SimpleEquation equation)  {
//        final int HAS_OPERATOR = 1;
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < equation.getArgumentList().size(); i++) {
//            if (i == HAS_OPERATOR) {
//                builder.append(equation.getOperator().toString());
//            }
//            builder.append(equation.getArgumentList().get(i));
//        }
//    }

    public void updateEquationView(SimpleEquation equation) {
        Log.i(EQUATION, "Updated equation");
        simpleEquationViewModel.updateEquation(equation);
//        List<Double> resList = equation.getResultList();
//        String txt = String.valueOf(resList.get( resList.size() - 1));
//        Log.d("equation result is", txt);
//        resultView.setText(txt);
    }
    //Errors
    public void handleOperatorError() {
        Toast.makeText(this, "Incorrect operator input", Toast.LENGTH_LONG).show();
    }

    public void handleOperatorOverride() {

    }
}
