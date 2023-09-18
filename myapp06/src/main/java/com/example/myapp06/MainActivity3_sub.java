package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity3_sub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3_sub);
        setTitle("투표결과");

        Intent intent = getIntent();
        int[] countvoteResult = intent.getIntArrayExtra("countvote");
        String[] imageResult = intent.getStringArrayExtra("imgName");
        Integer imageFieldId[] = {  R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,
                                    R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,
                                    R.drawable.pic7,R.drawable.pic8,R.drawable.pic9};
        Integer ratingBarId[] = {R.id.rating1,R.id.rating2,R.id.rating3,R.id.rating4,R.id.rating5,R.id.rating6,R.id.rating7,R.id.rating8,R.id.rating9};
        RatingBar ratingBar[] = new RatingBar[9];
        TextView txt[] = new TextView[9];
        Integer txtId[] = {R.id.txt1,R.id.txt2,R.id.txt3,R.id.txt4,R.id.txt5,R.id.txt6,R.id.txt7,R.id.txt8,R.id.txt9};
        int maxPos = 0;
        for(int i=0; i<countvoteResult.length; i++){
            if(countvoteResult[maxPos]<countvoteResult[i]){
                maxPos=i;
            }
        }
        TextView txtTop = findViewById(R.id.txtTop);
        ImageView imgTop = findViewById(R.id.imgTop);
        txtTop.setText(imageResult[maxPos] + "("+countvoteResult[maxPos]+")");
        imgTop.setImageResource(imageFieldId[maxPos]);

        for(int i=0; i<ratingBar.length; i++){
            final int index = i;
            ratingBar[index] = findViewById(ratingBarId[index]);
            txt[index] = findViewById(txtId[index]);
            txt[index].setText(imageResult[index]);
            for(int j=0; j<countvoteResult[index]; j++){
                ratingBar[index].setRating(ratingBar[index].getRating()+ratingBar[index].getStepSize());
            }
        }

        Button button = findViewById(R.id.btnReturn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}