package com.example.myapp04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {
    EditText Edit1, Edit2;
    Button[] numButtons = new Button[10];
    Integer[] numBtnIds = {R.id.Btnnum0, R.id.Btnnum1, R.id.Btnnum2, R.id.Btnnum3, R.id.Btnnum4, R.id.Btnnum5, R.id.Btnnum6, R.id.Btnnum7, R.id.Btnnum8, R.id.Btnnum9,};
    Button BtnAdd, BtnSub, BtnMul, BtnDiv;
    TextView resultText;
    String num1, num2;
    Integer result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        
        setTitle("테이블 레이아웃 계산기");

        Edit1 = findViewById(R.id.Edit1);
        Edit2 = findViewById(R.id.Edit2);
        BtnAdd = findViewById(R.id.BtnAdd);
        BtnSub = findViewById(R.id.BtnSub);
        BtnMul = findViewById(R.id.BtnMul);
        BtnDiv = findViewById(R.id.BtnDiv);
        resultText = findViewById(R.id.resultText);

        BtnAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = Edit1.getText().toString();
                num2 = Edit2.getText().toString();

                result = Integer.parseInt(num1) + Integer.parseInt(num2);

                resultText.setText(result.toString());
                return false;
            }
        });
        BtnSub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = Edit1.getText().toString();
                num2 = Edit2.getText().toString();

                result = Integer.parseInt(num1) - Integer.parseInt(num2);

                resultText.setText(result.toString());
                return false;
            }
        });
        BtnMul.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = Edit1.getText().toString();
                num2 = Edit2.getText().toString();

                result = Integer.parseInt(num1) * Integer.parseInt(num2);

                resultText.setText(result.toString());
                return false;
            }
        });
        BtnDiv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = Edit1.getText().toString();
                num2 = Edit2.getText().toString();

                result = Integer.parseInt(num1) / Integer.parseInt(num2);

                resultText.setText(result.toString());
                return false;
            }
        });
        
        for(int i=0; i<numBtnIds.length; i++){
            numButtons[i] = findViewById(numBtnIds[i]);
        }
        for(int i=0; i<numBtnIds.length; i++ ){
            final int index;
            index = i;
            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Edit1.isFocused()==true){
                        num1=Edit1.getText().toString()+numButtons[index].getText().toString();
                        Edit1.setText(num1);
                    }else if(Edit2.isFocused()==true){
                        num2=Edit2.getText().toString()+numButtons[index].getText().toString();
                        Edit2.setText(num2);
                    }else{
                        Toast.makeText(getApplicationContext(),"먼저 에디트를 선택하세요",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}