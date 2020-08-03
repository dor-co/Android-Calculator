package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text_window;
    int lastNum;
    String lastAction;
    boolean actionPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_window = findViewById(R.id.textWindow);

        Button Btn_1 = findViewById(R.id.Btn1);
        Button Btn_2 = findViewById(R.id.Btn2);
        Button Btn_3 = findViewById(R.id.Btn3);
        Button Btn_mul = findViewById(R.id.BtnMul);
        Button Btn_4 = findViewById(R.id.Btn4);
        Button Btn_5 = findViewById(R.id.Btn5);
        Button Btn_6 = findViewById(R.id.Btn6);
        Button Btn_sub = findViewById(R.id.BtnSub);
        Button Btn_7 = findViewById(R.id.Btn7);
        Button Btn_8 = findViewById(R.id.Btn8);
        Button Btn_9 = findViewById(R.id.Btn9);
        Button Btn_0 = findViewById(R.id.Btn0);
        Button Btn_div = findViewById(R.id.BtnDiv);
        Button Btn_add = findViewById(R.id.BtnAdd);
        Button Btn_eq = findViewById(R.id.BtnEq);
        Button Btn_clr = findViewById(R.id.BtnClr);

        NumsBtnListener NBL = new NumsBtnListener();

        Btn_0.setOnClickListener(NBL);
        Btn_1.setOnClickListener(NBL);
        Btn_2.setOnClickListener(NBL);
        Btn_3.setOnClickListener(NBL);
        Btn_4.setOnClickListener(NBL);
        Btn_5.setOnClickListener(NBL);
        Btn_6.setOnClickListener(NBL);
        Btn_7.setOnClickListener(NBL);
        Btn_8.setOnClickListener(NBL);
        Btn_9.setOnClickListener(NBL);

        ActionsBtnListener ABL = new ActionsBtnListener();

        Btn_add.setOnClickListener(ABL);
        Btn_sub.setOnClickListener(ABL);
        Btn_mul.setOnClickListener(ABL);
        Btn_div.setOnClickListener(ABL);

        Btn_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_window.setText("");
                //lastNum = 0;
            }
        });

        Btn_eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluate();
            }
        });
    }

    private void evaluate(){
        if(lastAction == null){
            return;
        }
        String numStr = text_window.getText().toString();
        int newNum = Integer.parseInt(numStr);

        switch (lastAction) {
            case "+":
                text_window.setText(lastNum + newNum + "");
                break;
            case "-":
                text_window.setText(lastNum-newNum+"");
                break;
            case "X":
                text_window.setText(lastNum*newNum+"");
                break;
            case "/":
                if(newNum == 0){
                    return;
                }
                text_window.setText(lastNum/newNum+"");
                break;
        }
        actionPress = true;
        lastAction = null;
    }

    private class NumsBtnListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(actionPress == true){
                text_window.setText(((Button)view).getText().toString());
                actionPress = false;
            }
            else {
                String numStr = ((Button) view).getText().toString();
                text_window.setText(text_window.getText() + numStr);
            }
        }
    }

    private class ActionsBtnListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (lastAction != null ) evaluate();
            String numStr = text_window.getText().toString();
            lastNum = Integer.parseInt(numStr);

            lastAction = ((Button)view).getText().toString();
            actionPress = true;
        }
    }
}