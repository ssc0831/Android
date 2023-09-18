package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setTitle("명화 선호도 투표");
        Button btnResult = findViewById(R.id.btnResult);

        int countvote[] = new int[9];
        ImageView image[] = new ImageView[9];
        Integer imageId[] = {R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9};
        String imgName[] = {"독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀", "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들", "해변에서"};

        for(int i=0; i<image.length; i++){
            final int index = i;
            image[index] = findViewById(imageId[index]);
            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    countvote[index]++;
                    String text = imgName[index]+": 총"+countvote[index]+"표";
                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                }
            });
        }
        //투표종료
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity3_sub.class);
                intent.putExtra("countvote",countvote);
                intent.putExtra("imgName",imgName);
                startActivity(intent);
            }
        });
    }
}