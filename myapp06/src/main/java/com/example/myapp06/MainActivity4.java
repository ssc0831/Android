package com.example.myapp06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapp06.databinding.ActivityMain4Binding;

public class MainActivity4 extends AppCompatActivity {
    private ActivityMain4Binding main4Binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main4Binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(main4Binding.getRoot());
        setTitle("명화 선호도 투표(binding)");

        int countvote[] = new int[9];
        ImageView image[]={main4Binding.tv1,main4Binding.tv2,main4Binding.tv3,main4Binding.tv4,main4Binding.tv5,main4Binding.tv6,main4Binding.tv7,main4Binding.tv8,main4Binding.tv9};
        String imgName[] = {"독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀", "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들", "해변에서"};

        for(int i=0; i<image.length; i++){
            final int index = i;

            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    countvote[index]++;
                    String text = imgName[index]+": 총"+countvote[index]+"표";
                    Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                }
            });
        }
        main4Binding.btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity4_sub.class);
                intent.putExtra("countvote",countvote);
                intent.putExtra("imgName",imgName);
                startActivity(intent);
            }
        });
    }
}