package com.example.quickulator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickulator.databinding.ActivityMainBinding;
import com.example.quickulator.model.Operator;

import java.util.HashSet;
import java.util.Set;

//import com.example.quickulator.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
//    numpad features
    private ImageButton clearAllBtn;
    private ImageButton undoBtn;
    private ImageButton bracketsBtn;
    private ImageButton historyBtn;
//    numpad numbers
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
//  private r
//    private ImageButton additionBtn;
//    private ImageButton subtractionBtn;
//    private ImageButton multiplicationBtn;
//    private ImageButton divisionBtn;
//    private ImageButton equalsBtn;

    private Set<ImageButton> operatorSet = new HashSet<>();
    private Set<Button> numPad = new HashSet<>();

    private CalculatorLogic calcLogic = CalculatorLogic.getInstance();
    private SimpleCalcController simpleCalcController = new SimpleCalcController();

    private final String TAG = "DEBUG";
    private ActivityMainBinding binding;
    private Button btn0Test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind operators
        operatorSet.add(findViewById(R.id.additionBtn));
        operatorSet.add(findViewById(R.id.subtractionBtn));
        operatorSet.add(findViewById(R.id.multiplicationBtn));
        operatorSet.add(findViewById(R.id.divisionBtn));
        operatorSet.add(findViewById(R.id.equalsBtn));
        for (ImageButton btn : operatorSet) {
            btn.setOnClickListener(v -> {
                Operator operator = Operator.valueOf(btn.getTag().toString());
                simpleCalcController.operatorInputHandler(operator);
            });
        }
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

        for (Button btn : numPad) {
            btn.setOnClickListener(v -> {
                int num = Integer.parseInt(btn.getText().toString());
                simpleCalcController.numberInputHandler(num);
            });
        }

    }

    private void operatorBtnHandler(Operator op) {


    }

}
