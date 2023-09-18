package com.example.myapp04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapp04.databinding.ActivityMain6Binding;
import com.example.myapp04.databinding.ActivityMainBinding;

public class MainActivity6 extends AppCompatActivity {
    private ActivityMain6Binding binding;
    String num1,num2;
    Integer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        binding = ActivityMain6Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Button[] numButtons = {binding.Btnnum0,binding.Btnnum1,binding.Btnnum2,binding.Btnnum3,binding.Btnnum4,binding.Btnnum5,binding.Btnnum6,binding.Btnnum7,binding.Btnnum8,binding.Btnnum9};
        binding.BtnAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = binding.Edit1.getText().toString();
                num2 = binding.Edit2.getText().toString();

                result = Integer.parseInt(num1) + Integer.parseInt(num2);

                binding.resultText.setText(result.toString());
                return false;
            }
        });
        binding.BtnSub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = binding.Edit1.getText().toString();
                num2 = binding.Edit2.getText().toString();

                result = Integer.parseInt(num1) - Integer.parseInt(num2);

                binding.resultText.setText(result.toString());
                return false;
            }
        });
        binding.BtnMul.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = binding.Edit1.getText().toString();
                num2 = binding.Edit2.getText().toString();

                result = Integer.parseInt(num1) * Integer.parseInt(num2);

                binding.resultText.setText(result.toString());
                return false;
            }
        });
        binding.BtnDiv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                num1 = binding.Edit1.getText().toString();
                num2 = binding.Edit2.getText().toString();

                result = Integer.parseInt(num1) / Integer.parseInt(num2);

                binding.resultText.setText(result.toString());
                return false;
            }
        });

        for(int i=0; i<numButtons.length; i++){
            final int index;
            index = i;
            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(binding.Edit1.isFocused()==true){
                        num1=binding.Edit1.getText().toString()+numButtons[index].getText().toString();
                        binding.Edit1.setText(num1);
                    }else if(binding.Edit2.isFocused()==true){
                        num2=binding.Edit2.getText().toString()+numButtons[index].getText().toString();
                        binding.Edit2.setText(num2);
                    }else{
                        Toast.makeText(getApplicationContext(),"먼저 에디트를 선택하세요",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}