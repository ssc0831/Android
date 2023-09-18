package com.example.myapp15;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private PhoneAdapter phoneAdapter;
    private RecyclerView recyclerView;
    private EditText etName;
    private EditText etTel;
    private Button btnInsert;
    private Button btnUpdate;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etTel = findViewById(R.id.etTel);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        // 리사이클러뷰 초기화
        recyclerView = findViewById(R.id.recyclerView);
        phoneAdapter = new PhoneAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(phoneAdapter);

        // 추가
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String tel = etTel.getText().toString();
                Phone newPhone = new Phone(name, tel);
                phoneAdapter.addItem(newPhone);
                clearInputFields(); // 입력 필드 초기화
            }
        });

        // 수정
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getSelectedPosition(); // 선택된 아이템의 인덱스를 가져옴
                if (position != RecyclerView.NO_POSITION) {
                    String name = etName.getText().toString();
                    String tel = etTel.getText().toString();
                    Phone updatedPhone = new Phone(name, tel);
                    phoneAdapter.updateItem(position, updatedPhone);
                    clearInputFields(); // 입력 필드 초기화
                }
            }
        });

        // 삭제
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getSelectedPosition(); // 선택된 아이템의 인덱스를 가져옴
                if (position != RecyclerView.NO_POSITION) {
                    phoneAdapter.removeItem(position);
                    clearInputFields(); // 입력 필드 초기화
                }
            }
        });
    }

    // 선택된 아이템의 인덱스를 가져오는 메서드
    private int getSelectedPosition() {
        if (recyclerView.getAdapter() instanceof PhoneAdapter) {
            PhoneAdapter adapter = (PhoneAdapter) recyclerView.getAdapter();
            return adapter.getSelectedPosition();
        }
        return RecyclerView.NO_POSITION;
    }

    // 입력 필드 초기화 메서드
    private void clearInputFields() {
        etName.setText("");
        etTel.setText("");
        recyclerView.clearFocus(); // 입력 필드에 포커스 해제
        recyclerView.requestFocus(); // 리사이클러뷰에 포커스 설정 (선택 해제)
    }
}
