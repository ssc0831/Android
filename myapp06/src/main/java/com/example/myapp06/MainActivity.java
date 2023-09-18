package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioButton rdoSecond = findViewById(R.id.rdoSecond);
        RadioButton rdoThird = findViewById(R.id.rdoThird);
        RadioButton rdoFour = findViewById(R.id.rdoFour);
        RadioGroup radioGroup = findViewById(R.id.rgAct);
        Button button = findViewById(R.id.btnOpen);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
//                startActivity(intent);
//            }
//        });
        rdoFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FourActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            Intent intent=null;
            @Override
            public void onClick(View view) {
                if(rdoSecond.isChecked()) {
                    intent = new Intent(getApplicationContext(), SecondActivity.class);
                }else if(rdoThird.isChecked()){
                    intent = new Intent(getApplicationContext(), ThirdActivity.class);
                }
                startActivity(intent);
            }
        });
    }
}