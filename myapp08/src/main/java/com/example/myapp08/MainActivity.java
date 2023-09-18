package com.example.myapp08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Integer[] posterId = {
                R.drawable.mov01,R.drawable.mov02,
                R.drawable.mov03,R.drawable.mov04,
                R.drawable.mov05,R.drawable.mov06,
                R.drawable.mov07,R.drawable.mov08,
                R.drawable.mov09,R.drawable.mov10};
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ArrayList<MovieItem> movieList = new ArrayList<>();
        for(int i=0; i<posterId.length; i++){
            MovieItem movie = new MovieItem();
            movie.setPosterId(posterId[i]);
            movie.setTitle("타이틀 : "+i);
            movieList.add(movie);
        }

        movieAdapter = new MovieAdapter();
        movieAdapter.setMovieList(movieList);
        //뷰 어뎁터 연결
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

    }
}