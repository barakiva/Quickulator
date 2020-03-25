package com.example.quickulator;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.quickulator.model.Command;
import com.example.quickulator.model.InputHelper;
import com.example.quickulator.model.Operator;
import com.example.quickulator.model.SimpleEquation;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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
    //Equation
    private SimpleEquation equation;
    private InputHelper inputHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleCalcController = new SimpleCalcController();
        simpleCalcController.init(this);
        equation = SimpleEquation.getInstance();
        inputHelper = InputHelper.getInstance();

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
        resultView = findViewById(R.id.resultView);
        expressionView = findViewById(R.id.expressionView);
        simpleEquationViewModel = new ViewModelProvider(this).get(SimpleEquationViewModel.class);

        final Observer<SimpleEquation> equationObserver = equation -> {
            Log.i(EQUATION, "Observer triggered!");
            displayEquation();
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



    public void displayEquation() {
        if (!equation.getResultList().isEmpty()) {
            resultView.setText(buildResult());
        }
        if (!equation.getArgumentList().isEmpty() || inputHelper.getDigitInput().length() > 0) {
            expressionView.setText(buildExpression());
        }
    }
    //Build Strings
    public String buildResult() {
        List<Double> results = equation.getResultList();
        return Double.toString(results.get(results.size() - 1));
    }
    public String buildExpression() {
        StringBuilder expression = new StringBuilder();
        int dirtyHack = 0;
        if (equation.getOperator() != null) {
            dirtyHack = 1;
        }
        int memberCount = equation.getArgumentList().size() + dirtyHack;
        int listIterator = 0;
        for (int i = 0; i < memberCount; i++) {
            if (i % 2 == 0) {
                expression.append(parseArgument(
                        equation.getArgumentList().get(listIterator)));
                listIterator++;
            } else {
                expression.append(parseOperator());
            }
        }
        //Adds current input that's yet to be built to a number
        if (inputHelper.getDigitInput().length() > 0) {
            expression.append(inputHelper.buildNumber());
        }
        return expression.toString();
    }
    //String build helper functions
    private String parseArgument(Double n) {
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.DOWN);
        return n % 1 == 0 ? df.format(n) : Double.toString(n);
    }
    private String parseOperator() {
        switch (equation.getOperator()) {
            case ADDITION:
                return "+";
            case SUBTRACTION:
                return "-";
            case MULTIPLICATION:
                return "x";
            case DIVISION:
                return "/";
            default:
                return "";
        }
    }
    //Controller Invoked Functions
    //Equation update
    public void updateEquationView() {
        this.equation = SimpleEquation.getInstance();
        simpleEquationViewModel.updateEquation();
    }
    //Errors
    public void handleOperatorError() {
        Toast.makeText(this, "Incorrect operator input", Toast.LENGTH_LONG).show();
    }

    public void handleOperatorOverride() {

    }
}
