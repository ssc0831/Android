package com.example.myapp12;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Thread");
        Switch switch1 = findViewById(R.id.switch1);
        seekBar = findViewById(R.id.pbMP3);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch1.isChecked()==true){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.song1);
                    mediaPlayer.start();
                    //thread
                    makeThread();
                }else{
                    mediaPlayer.stop();
                }
            }
        });
    }

    void makeThread(){
        new Thread(){
            @Override
            public void run() {
                while(mediaPlayer.isPlaying()){
                    seekBar.setMax(mediaPlayer.getDuration());
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SystemClock.sleep(100);
                }
                seekBar.setProgress(0);
            }
        }.start();
    }
}