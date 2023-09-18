package com.example.myapp05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp05.databinding.ActivityMainUserBinding;
import com.example.myapp05.databinding.Dialog1Binding;
import com.example.myapp05.databinding.Toast1Binding;

public class MainActivity_user extends AppCompatActivity {
    private ActivityMainUserBinding binding;
    private Dialog1Binding dialogbinding;
    private Toast1Binding toast1Binding;
    private View toastView;
    private TextView toastText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        binding = ActivityMainUserBinding.inflate(getLayoutInflater());
        dialogbinding = Dialog1Binding.inflate(getLayoutInflater());
        toast1Binding = Toast1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("사용자 정보 입력");

        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity_user.this);
                dlg.setView(dialogbinding.getRoot());
                dlg.setTitle("사용자 정보 입력");
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        binding.userName.setText(dialogbinding.dlgName.getText().toString());
                        binding.userEmail.setText(dialogbinding.dlgEmail.getText().toString());
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = new Toast(MainActivity_user.this);
                        toast.setView(toast1Binding.getRoot());
                        toast1Binding.toastText1.setText("취소했습니다");
                        toast.show();
                    }
                });
                dlg.show();
            }
        });
    }
}