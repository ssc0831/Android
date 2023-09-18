package com.example.myapp14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Phone> phoneList;
    private PhoneAdapter phoneAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("android+springboot");

        recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton floatingBtnAdd = findViewById(R.id.floatingBtnAdd);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false);
        phoneList = new ArrayList<>();
        phoneAdapter = new PhoneAdapter(phoneList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(phoneAdapter);
        ////전체보기
        PhoneService phoneService = PhoneClient.getInstance().getPhoneService();
        Call<List<Phone>> call = phoneService.findAll();
        call.enqueue(new Callback<List<Phone>>() {
            @Override
            public void onResponse(Call<List<Phone>> call, Response<List<Phone>> response) {
                for(Phone phone:response.body()){
                    phoneList.add(phone);
                }
                phoneAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Phone>> call, Throwable t) {

            }
        });
        //하나씩 클릭
        phoneAdapter.setOnItemClickListener(new PhoneAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Phone phone = phoneAdapter.getItem(pos);
                View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.add_layout,null);

                EditText editName = dialogView.findViewById(R.id.editName);
                EditText editTel = dialogView.findViewById(R.id.editTel);

                editName.setText(phone.getName().toString());
                editTel.setText(phone.getTel().toString());

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("수정하기");
                alertDialog.setView(dialogView);

                long id = phone.getId();

                alertDialog.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Phone updatePhone = new Phone();
                        updatePhone.setName(editName.getText().toString());
                        updatePhone.setTel(editTel.getText().toString());
                        Call<Phone> call = phoneService.updatebyId(id,updatePhone);
                        call.enqueue(new Callback<Phone>() {
                            @Override
                            public void onResponse(Call<Phone> call, Response<Phone> response) {
                                phoneAdapter.updateItem(pos,response.body());
                            }

                            @Override
                            public void onFailure(Call<Phone> call, Throwable t) {

                            }
                        });
                    }
                });
                alertDialog.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Call<Void> call = phoneService.deleteById(id);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                phoneAdapter.deleteItem(pos);
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
                    }
                });

                alertDialog.show();
            }
        });
        //길게 클릭
        phoneAdapter.setOnItemLongClickListener(new PhoneAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int pos) {
                long id = phoneAdapter.getItem(pos).getId();
                Call<Void> call = phoneService.deleteById(id);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        phoneAdapter.deleteItem(pos);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });

        floatingBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContext();
            }
        });

    }
    private void addContext(){
        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.add_layout,null);
        EditText editName = dialogView.findViewById(R.id.editName);
        EditText editTel = dialogView.findViewById(R.id.editTel);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("메시지 등록");
        alertDialog.setView(dialogView);
        alertDialog.setPositiveButton("등록", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Phone phone = new Phone();
                phone.setName(editName.getText().toString());
                phone.setTel(editTel.getText().toString());
                Log.d("phonelog", "등록시 값 확인 "+phone);
                PhoneService phoneService = PhoneClient.getInstance().getPhoneService();
                Call<Phone> call = phoneService.save(phone);
                call.enqueue(new Callback<Phone>() {
                    @Override
                    public void onResponse(Call<Phone> call, Response<Phone> response) {
                        phoneAdapter.addItem(response.body());

                    }

                    @Override
                    public void onFailure(Call<Phone> call, Throwable t) {

                    }
                });
            }
        });
        alertDialog.setNegativeButton("취소",null);
        alertDialog.show();
    }
}