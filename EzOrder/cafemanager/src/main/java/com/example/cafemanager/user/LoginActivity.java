package com.example.cafemanager.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cafemanager.CafeManagerClient;
import com.example.cafemanager.R;
import com.example.cafemanager.SelectActivity;
import com.example.cafemanager.databinding.ActivityLoginBinding;
import com.example.cafemanager.management.ManagementActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //로그인 버튼 클릭
        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(binding.editUserName.getText().toString(),
                        binding.editPassword.getText().toString());
                Call<Integer> call = CafeManagerClient.getInstance().getUserService().login(user);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        //response.body() -1:실패 or shopid
                        if(response.body()!=-1){
                            long shopId = response.body();

                            SharedPreferences preferences = getSharedPreferences("ezorder", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("ezordershopId",Long.toString(shopId));
                            editor.commit();

                            String aaa = preferences.getString("ezordershopId","");
                            long bbb = Long.parseLong(aaa);
                            Log.d(TAG, "onResponse: "+bbb);

                            Log.d(TAG, "onResponse: 로그인성공 id : "+shopId);
                            Intent intent = new Intent(LoginActivity.this, SelectActivity.class);
                            startActivity(intent);
                        }else{
                            Log.d(TAG, "onResponse: 로그인실패");
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.d(TAG, "onFailure: "+t);
                    }
                });
            }
        });
    }
}