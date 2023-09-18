package com.example.myapp15;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BroadActivity2 extends AppCompatActivity {
    ImageView ivBattery;
    TextView edtBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad2);
        setTitle("배터리 상태 체크");

        ivBattery = findViewById(R.id.ivBattery);
        edtBattery = findViewById(R.id.edtBattery);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br,intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        unregisterReceiver(br);
        super.onPause();
    }


    BroadcastReceiver br = new BroadcastReceiver() {
            @Override
           public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(Intent.ACTION_BATTERY_CHANGED)){
                    int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                    edtBattery.setText("현재 충전량 : " +remain+"\n");
                    if(remain>=90) {
                        ivBattery.setImageResource(R.drawable.battery_100);
                    } else if (remain>=70) {
                        ivBattery.setImageResource(R.drawable.battery_80);
                    } else if (remain>=50) {
                        ivBattery.setImageResource(R.drawable.battery_60);
                    } else if (remain>=10) {
                        ivBattery.setImageResource(R.drawable.battery_20);
                    }else {
                        ivBattery.setImageResource(R.drawable.battery_0);
                    }
                }

                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
                switch (plug){
                    case 0 :
                        edtBattery.append("전원 연결 : 안됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        edtBattery.append("전원 연결 : 어댑터 연결됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        edtBattery.append("전원 연결 : USB 연결됨");
                        break;
                }
            }
        };
}