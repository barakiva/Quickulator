package com.example.quickulator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
//    numpad features
    @BindView(R.id.clearAllBtn) ImageButton clearAllBtn;
    @BindView(R.id.undoBtn) ImageButton undoBtn;
    @BindView(R.id.bracketsBtn) ImageButton bracketsBtn;
    @BindView(R.id.btnHistory) ImageButton historyBtn;
//    numpad numbers
    @BindView(R2.id.btn0) Button btn0;
    @BindView(R2.id.btn1) Button btn1;
    @BindView(R.id.btn2) Button btn2;
    @BindView(R.id.btn3) Button btn3;
    @BindView(R.id.btn4) Button btn4;
    @BindView(R.id.btn5) Button btn5;
    @BindView(R.id.btn6) Button btn6;
    @BindView(R.id.btn7) Button btn7;
    @BindView(R.id.btn8) Button btn8;
    @BindView(R.id.btn9) Button btn9;
//    Operatoions Toolbar
    @BindView(R.id.additionBtn) ImageButton additionBtn;
    @BindView(R.id.subtractionBtn) ImageButton subtractionBtn;
    @BindView(R.id.multiplicationBtn) ImageButton multiplicationBtn;
    @BindView(R.id.divisionBtn) ImageButton divisionBtn;
    @BindView(R.id.equalsBtn) ImageButton equalsBtn;
    private CalculatorLogic calcLogic = CalculatorLogic.getInstance();
    private final String TAG = "DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick({R2.id.btn0,R2.id.btn1,R2.id.btn2,R2.id.btn3,R2.id.btn4,
              R2.id.btn5,R2.id.btn6,R2.id.btn7,R2.id.btn8,R2.id.btn9})
    private void getBtnNum(Button v){
        calcLogic.digitCatcher(Double.parseDouble
                (v.getText().toString()));
    }


}
