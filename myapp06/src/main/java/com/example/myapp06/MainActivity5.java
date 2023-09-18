package com.example.myapp06;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapp06.databinding.ActivityMain5Binding;

public class MainActivity5 extends AppCompatActivity {
    ActivityMain5Binding main5Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main5Binding = ActivityMain5Binding.inflate(getLayoutInflater());
        setContentView(main5Binding.getRoot());

        main5Binding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity5_sub.class);
                intent.putExtra("num1",Integer.parseInt(main5Binding.editNum1.getText().toString()));
                intent.putExtra("num2",Integer.parseInt(main5Binding.editNum2.getText().toString()));
                startActivityForResult(intent,0);
                //startActivityResult.launch(intent);

            }
        });
    }
//    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if(result.getResultCode()==RESULT_OK){
//                        Intent intent = result.getData();
//                        int addResult = intent.getIntExtra("addResult",0);
//                        Toast.makeText(getApplicationContext(),"합계 :" +addResult,Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//    );


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK){
            int result = data.getIntExtra("addResult",0);
            Toast.makeText(getApplicationContext(),"합계 : "+result,Toast.LENGTH_SHORT).show();
        }
    }
}