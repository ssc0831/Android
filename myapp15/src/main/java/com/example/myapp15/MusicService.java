package com.example.myapp15;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d("서비스테스트", "onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("서비스테스트", "onStart()");
        mediaPlayer = MediaPlayer.create(this,R.raw.song1);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();


        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        Log.d("서비스테스트", "onDestroy()");
        mediaPlayer.stop();
        super.onDestroy();
    }
}
