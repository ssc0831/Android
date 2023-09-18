package com.example.myapp04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rg;
    ImageView ImgPet;
    Button BtnOk;
    TextView text2;
    CheckBox chkAgree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("애완동물 사진 보기");

        BtnOk = findViewById(R.id.BtnOk);
        ImgPet = findViewById(R.id.ImgPet);
        rg = findViewById(R.id.rg);
        chkAgree = findViewById(R.id.chkAgree);
        text2 = findViewById(R.id.text2);

        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chkAgree.isChecked()==true){
                    text2.setVisibility(View.VISIBLE);
                    rg.setVisibility(View.VISIBLE);
                    BtnOk.setVisibility(View.VISIBLE);
                    ImgPet.setVisibility(View.VISIBLE);
                }else{
                    text2.setVisibility(View.INVISIBLE);
                    rg.setVisibility(View.INVISIBLE);
                    BtnOk.setVisibility(View.INVISIBLE);
                    ImgPet.setVisibility(View.INVISIBLE);
                }
            }
        });
        BtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = rg.getCheckedRadioButtonId();
                if(i==R.id.rdoDog){
                    ImgPet.setImageResource(R.drawable.dog);
                }else if(i==R.id.rdoCat) {
                    ImgPet.setImageResource(R.drawable.cat);
                }else if(i==R.id.rdoRabbit){
                    ImgPet.setImageResource(R.drawable.rabbit);
                }else{
                    Toast.makeText(getApplicationContext(),"동물 먼저 선택하세요",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}