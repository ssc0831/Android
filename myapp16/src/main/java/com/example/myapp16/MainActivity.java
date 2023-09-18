package com.example.myapp16;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapp16.databinding.ActivityMainBinding;
import com.example.myapp16.databinding.AddLayoutBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PersonAdapter personAdapter;
    private ArrayList<Person> personList = new ArrayList<>();
    private PersonService personService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("retrofit_Person");

        personAdapter = new PersonAdapter(personList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL,false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(personAdapter);

        personService=PersonClient.getInstance().getPersonService();
        showList();

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person p = new Person(binding.editName.getText().toString(),
                        binding.editTel.getText().toString());
                Call<Person> call = personService.save(p);
                call.enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        personAdapter.addItem(response.body());
                        binding.editName.setText("");
                        binding.editTel.setText("");
                    }

                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {
                    }
                });
            }
        });
        binding.floatBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddLayoutBinding dialogbinding = AddLayoutBinding.inflate(getLayoutInflater());

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialogbinding.getRoot());
                builder.setTitle("추가");
                builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Person p = new Person(dialogbinding.etName.getText().toString(),
                                dialogbinding.etTel.getText().toString());
                        Call<Person> call = personService.save(p);
                        call.enqueue(new Callback<Person>() {
                            @Override
                            public void onResponse(Call<Person> call, Response<Person> response) {
                                personAdapter.addItem(response.body());
                                binding.editName.setText("");
                                binding.editTel.setText("");
                            }

                            @Override
                            public void onFailure(Call<Person> call, Throwable t) {

                            }
                        });

                    }
                });
                builder.setNegativeButton("취소",null);
                builder.show();

            }
        });
    }
    public void showList(){
        Call<List<Person>> call = personService.findAll();
        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                for(Person p : response.body()){
                    personList.add(p);
                }
                personAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {

            }
        });
    }
}