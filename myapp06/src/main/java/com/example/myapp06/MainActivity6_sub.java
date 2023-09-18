package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapp06.databinding.ActivityMainActivity5SubBinding;
import com.example.myapp06.databinding.ActivityMainActivity6SubBinding;

public class MainActivity6_sub extends AppCompatActivity {
    ActivityMainActivity6SubBinding sub6Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sub6Binding = ActivityMainActivity6SubBinding.inflate(getLayoutInflater());
        setContentView(sub6Binding.getRoot());
        Intent intent = getIntent();
        int num1 = intent.getIntExtra("num1",0);
        int num2 = intent.getIntExtra("num2",0);
        int sel = intent.getIntExtra("select",0);



        sub6Binding.btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numResult=0;
                if(sel==R.id.rdoAdd){
                    numResult=num1+num2;
                }else if(sel==R.id.rdoSub){
                    numResult=num1-num2;
                }else if(sel==R.id.rdoMul){
                    numResult=num1*num2;
                }else if(sel==R.id.rdoDiv){
                    numResult=num1/num2;
                }
                Intent outintent = new Intent(getApplicationContext(),MainActivity6.class);
                outintent.putExtra("numResult",numResult);
                setResult(RESULT_OK,outintent);
                finish();
            }
        });
    }
}