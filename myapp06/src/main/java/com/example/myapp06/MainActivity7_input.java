package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity7_input extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity7_input);
        EditText editName = findViewById(R.id.editName);
        EditText editAge = findViewById(R.id.editAge);
        EditText editPhone = findViewById(R.id.editPhone);
        Button btnInput = findViewById(R.id.btnInput);

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outIntent = new Intent(getApplicationContext(),MainActivity7.class);
                outIntent.putExtra("editName",editName.getText().toString());
                outIntent.putExtra("editAge",editAge.getText().toString());
                outIntent.putExtra("editPhone",editPhone.getText().toString());
                setResult(RESULT_OK,outIntent);
                finish();
            }
        });
    }
}