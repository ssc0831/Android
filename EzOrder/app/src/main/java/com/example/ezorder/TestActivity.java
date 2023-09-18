package com.example.ezorder;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ezorder.databinding.ActivityTestBinding;
import com.example.ezorder.fcm.FcmClient;
import com.example.ezorder.fcm.MyFcmPostService;
import com.example.ezorder.fcm.NotificationBody;
import com.example.ezorder.order.OrderActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {
    private ActivityTestBinding binding;
    private static final String TAG = "TestActivity";
    //알림 권한 요청 저장
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Toast.makeText(this, "Notifications permission granted",Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(this, "FCM can't post notifications without POST_NOTIFICATIONS permission",
                            Toast.LENGTH_LONG).show();
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences preferences = getSharedPreferences("MemberInfo", MODE_PRIVATE);
        String memberName = preferences.getString("memberName","");

        if(memberName.equals("")){
            Log.d(TAG, "아이디 없음 아이디 생성 :");
            SharedPreferences.Editor editor = preferences.edit();
            UUID uniqueId = UUID.randomUUID();

            String newId = uniqueId.toString() + "_memberName";
            editor.putString("memberName",newId);
            editor.commit();
            memberName = preferences.getString("memberName","");
            Log.d(TAG, "onCreate member: " + memberName);

            //memberName db입력
            Call<Void> call = EzOrderClient.getInstance().getMemberService().saveMember(memberName);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });

        }

        //알림 채널 생성
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }
        //알림 정보
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }

        //가게1 이동
        binding.testgobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long shopId = 1;
                Intent mainIntent = new Intent(TestActivity.this, OrderActivity.class);
                mainIntent.putExtra("shopId",shopId);
                startActivity(mainIntent);
            }
        });
        //가게2 이동
        binding.testgo2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long shopId = 2;
                Intent mainIntent = new Intent(TestActivity.this, OrderActivity.class);
                mainIntent.putExtra("shopId",shopId);
                startActivity(mainIntent);
            }
        });

        //테스트토큰받아오기
        binding.tokenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().getToken()
                        .addOnCompleteListener(new OnCompleteListener<String>() {
                            @Override
                            public void onComplete(@NonNull Task<String> task) {
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                    return;
                                }

                                // Get new FCM registration token
                                String token = task.getResult();

                                // Log and toast
                                String msg = getString(R.string.msg_token_fmt, token);
                                Log.d(TAG, msg);
                                Toast.makeText(TestActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        //fcm전송테스트
        binding.fcmtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyFcmPostService myFcmPostService = FcmClient.getInstance().getMyFcmPostService();
                String token = "fJTuuDiIQlWSUv_XmcobHy:APA91bGESlZBCqaiDKpzC5UwscDKF5yQk0X_nzxZrcqhEAG_r5gRoR-vOyk0ZUDB_i8XE1ZeVApchGYqQt86TROPPMcVMx35GKrUa2HdSAmlhGrZKbuIbs_bepNhc3Pm_wc-5O6ZWbYC";
                NotificationBody notificationBody = new NotificationBody(token,
                        "high",new NotificationBody.NotificationData("test01","test01"),
                        new NotificationBody.NotificationData("test01","test01"));
                Call<ResponseBody> call = myFcmPostService.postNotification(notificationBody);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d(TAG, "onResponse: " + response.body());
                        Toast.makeText(getApplicationContext(),"메시지전송",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                    }
                });

            }
        });

        //알림 허가
        askNotificationPermission();

    }//[oncreate 종료]

    private void askNotificationPermission() {
        // This is only necessary for API Level > 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "들어옴: "+intent.getExtras());
    }

}
