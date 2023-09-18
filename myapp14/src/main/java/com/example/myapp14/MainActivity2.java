package com.example.myapp14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapp14.databinding.ActivityMain2Binding;
import com.example.myapp14.person.Person;
import com.example.myapp14.person.PersonAdapter;
import com.example.myapp14.person.PersonClient;
import com.example.myapp14.person.PersonService;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding;
    private RecyclerView recyclerView;
    private ArrayList<Person> personList = new ArrayList<>();
    private PersonService personService;
    private PersonAdapter personAdapter;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("spring서버+binding");

        personService = PersonClient.getInstance().getPersonService();

        recyclerView = binding.recyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity2.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        personAdapter = new PersonAdapter(personList);
        recyclerView.setAdapter(personAdapter);

        //전체보기
        showList();

        //추가 버튼 클릭
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContext();
            }
        });

        //수정 버튼 클릭
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person p = new Person(binding.editName.getText().toString(),binding.editTel.getText().toString());
                long id = personAdapter.getItem(position).getId();
                Call<Person> call = personService.update(id,p);
                call.enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        personAdapter.updateItem(position,response.body());
                        binding.editName.setText("");
                        binding.editTel.setText("");
                        binding.btnUpdate.setEnabled(false);
                        binding.btnDelete.setEnabled(false);
                    }
                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {

                    }
                });
            }
        });
        //삭제 버튼 클릭
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePerson(personAdapter.getItem(position).getId());
            }
        });

        //recyclerview 클릭
        personAdapter.setSetOnItemClickListener(new PersonAdapter.SetOnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                position = pos;
                Person p = personAdapter.getItem(pos);
                binding.btnUpdate.setEnabled(true);
                binding.btnDelete.setEnabled(true);
                binding.editName.setText(p.getName());
                binding.editTel.setText(p.getTel());

            }
        });
        //recyclerview long 클릭
        personAdapter.setSetOnItemLongClickListener(new PersonAdapter.SetOnItemLongClickListener() {
            @Override
            public void onItemLongClick(int pos) {
                deletePerson(personAdapter.getItem(position).getId());
            }
        });
    }
    
    //추가
    public void addContext(){
        Person p = new Person(
                binding.editName.getText().toString(),
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
    
    //전체보기
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

    //삭제
    public void deletePerson(long id){
        Call<Void> call =personService.deleteById(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                personAdapter.removeItem(position);
                binding.editName.setText("");
                binding.editTel.setText("");
                binding.btnUpdate.setEnabled(false);
                binding.btnDelete.setEnabled(false);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}