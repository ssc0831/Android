package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("레이팅바");
        RatingBar ratingBar1 = findViewById(R.id.ratingBar1);
        RatingBar ratingBar2 = findViewById(R.id.ratingBar2);
        RatingBar ratingBar3 = findViewById(R.id.ratingBar3);
        Button btnInc = findViewById(R.id.btnInc);
        Button btnDec = findViewById(R.id.btnDec);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ratingBar1.setRating(ratingBar1.getRating()+ratingBar1.getStepSize());
                ratingBar2.setRating(ratingBar2.getRating()+ratingBar2.getStepSize());
                ratingBar3.setRating(ratingBar3.getRating()+ratingBar3.getStepSize());
            }
        });
        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ratingBar1.setRating(ratingBar1.getRating()-ratingBar1.getStepSize());
                ratingBar2.setRating(ratingBar2.getRating()-ratingBar2.getStepSize());
                ratingBar3.setRating(ratingBar3.getRating()-ratingBar3.getStepSize());
            }
        });
    }
}