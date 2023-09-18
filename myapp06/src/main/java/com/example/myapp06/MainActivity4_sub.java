package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myapp06.databinding.ActivityMainActivity4SubBinding;

public class MainActivity4_sub extends AppCompatActivity {
    ActivityMainActivity4SubBinding subbinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subbinding = ActivityMainActivity4SubBinding.inflate(getLayoutInflater());
        setContentView(subbinding.getRoot());
        Intent intent = getIntent();
        setTitle("투표결과");
        int[] countvoteResult = intent.getIntArrayExtra("countvote");
        String[] imageResult = intent.getStringArrayExtra("imgName");
        Integer imageFieldId[] = {  R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,
                R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,
                R.drawable.pic7,R.drawable.pic8,R.drawable.pic9};
        RatingBar ratingBar[]={subbinding.rating1,subbinding.rating2,subbinding.rating3,subbinding.rating4,subbinding.rating5,subbinding.rating6,subbinding.rating7,subbinding.rating8,subbinding.rating9,};
        TextView txt[]={subbinding.txt1,subbinding.txt2,subbinding.txt3,subbinding.txt4,subbinding.txt5,subbinding.txt6,subbinding.txt7,subbinding.txt8,subbinding.txt9};
        int maxPos = 0;
        for(int i=0; i<countvoteResult.length; i++){
            if(countvoteResult[maxPos]<countvoteResult[i]){
                maxPos=i;
            }
        }
        subbinding.txtTop.setText(imageResult[maxPos] + "("+countvoteResult[maxPos]+")");
        subbinding.imgTop.setImageResource(imageFieldId[maxPos]);


        for(int i=0; i<ratingBar.length; i++){
            final int index = i;
            txt[index].setText(imageResult[index]);
            for(int j=0; j<countvoteResult[index]; j++){
                ratingBar[index].setRating(ratingBar[index].getRating()+ratingBar[index].getStepSize());
            }
        }
        subbinding.btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}