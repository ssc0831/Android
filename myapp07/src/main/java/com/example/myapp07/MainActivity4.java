package com.example.myapp07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapp07.databinding.ActivityMain4Binding;

import java.net.URI;

public class MainActivity4 extends AppCompatActivity {
    ActivityMain4Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Life cycle onCreate :", "onCreate()");
        Toast.makeText(this,"onCreate()",Toast.LENGTH_LONG).show();
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel:01012345678");
                Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent);
            }
        });
        binding.btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Life cycle onStart :", "onStart()");
        Toast.makeText(this,"onStart()",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Life cycle onResume :", "onResume()");
        Toast.makeText(this,"onResume()",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Life cycle onPause :", "onPause()");
        Toast.makeText(this,"onPause()",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Life cycle onStop :", "onStop()");
        Toast.makeText(this,"onStop()",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Life cycle onDestroy :", "onDestroy()");
        Toast.makeText(this,"onDestroy()",Toast.LENGTH_LONG).show();
    }
}