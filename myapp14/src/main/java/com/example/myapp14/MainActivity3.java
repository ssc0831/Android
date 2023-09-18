package com.example.myapp14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapp14.databinding.ActivityMain3Binding;
import com.example.myapp14.join.User;
import com.example.myapp14.join.UserClient;
import com.example.myapp14.join.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity3 extends AppCompatActivity {
    ActivityMain3Binding binding;
    UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("회원가입");

        userService = UserClient.getInstance().getUserService();

        binding.btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(
                        binding.editUserId.getText().toString(),
                        binding.editPwd.getText().toString(),
                        binding.editUserName.getText().toString(),
                        binding.editEmail.getText().toString(),
                        binding.editAddr.getText().toString()
                );

                Call<User> call = userService.join(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"아이디 중복",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}