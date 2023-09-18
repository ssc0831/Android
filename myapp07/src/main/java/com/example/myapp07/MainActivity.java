package com.example.myapp07;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editNum1 = findViewById(R.id.editNum1);
        EditText editNum2 = findViewById(R.id.editNum2);
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK){
                    Log.d("ActivityResult : ", result.toString());
                    Intent intent = result.getData();
                    int sum = intent.getIntExtra("sum",0);
                    Toast.makeText(getApplicationContext(),"합계 : " + sum, Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_sub.class);
                intent.putExtra("num1",Integer.parseInt(editNum1.getText().toString()));
                intent.putExtra("num2",Integer.parseInt(editNum2.getText().toString()));
                launcher.launch(intent);
            }
        });
    }
}