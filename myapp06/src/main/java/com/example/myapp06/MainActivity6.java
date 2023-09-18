package com.example.myapp06;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapp06.databinding.ActivityMain5Binding;
import com.example.myapp06.databinding.ActivityMain6Binding;

public class MainActivity6 extends AppCompatActivity {
    private ActivityMain6Binding main6Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main6Binding = ActivityMain6Binding.inflate(getLayoutInflater());
        setContentView(main6Binding.getRoot());
        
        setTitle("양방향 액티비티");

        main6Binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity6_sub.class);
                intent.putExtra("num1",Integer.parseInt(main6Binding.editNum1.getText().toString()));
                intent.putExtra("num2",Integer.parseInt(main6Binding.editNum2.getText().toString()));
                intent.putExtra("select",main6Binding.rg.getCheckedRadioButtonId());
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            int resultNum = data.getIntExtra("numResult",0);
            main6Binding.resultText.setText(Integer.toString(resultNum));
        }
    }
}