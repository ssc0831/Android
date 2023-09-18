package com.example.myapp07;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.myapp07.databinding.DialogBinding;

public class MainActivity7_grid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity7_grid);
        GridView gridView = findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gridView.setAdapter(gAdapter);
    }
    class MyGridAdapter extends BaseAdapter {
        Integer[] posterID={R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,
                R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,R.drawable.mov10,
                R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,R.drawable.mov05,
                R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,R.drawable.mov10};
        Context context;
        public MyGridAdapter(Context c){
            context = c;
        }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(200,300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);

            imageView.setImageResource(posterID[i]);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogBinding binding;
                    binding = DialogBinding.inflate(getLayoutInflater());
                    //View dialogView = View.inflate(MainActivity7_grid.this,R.layout.dialog,null);
                    //ImageView ivPoster = dialogView.findViewById(R.id.ivPoster);
                    binding.ivPoster.setImageResource(posterID[i]);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity7_grid.this);
                    dlg.setTitle("큰 포스터");
                    dlg.setView(binding.getRoot());
                    dlg.setNegativeButton("닫기",null);
                    dlg.show();
                }
            });
            return imageView;
        }
    }
}