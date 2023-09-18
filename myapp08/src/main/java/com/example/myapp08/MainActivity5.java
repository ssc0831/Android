package com.example.myapp08;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity5 extends AppCompatActivity {
    private FriendAdapter friendAdapter = new FriendAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(friendAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        for(int i=0; i<20; i++){
            friendAdapter.addItem(new Friend("홍길동"+i,"메시지"+i));
        }
        friendAdapter.setOnItemClickListener(new FriendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Toast.makeText(getApplicationContext(),pos+"",Toast.LENGTH_SHORT).show();
            }
        });
        friendAdapter.setOnItemLongClickListener(new FriendAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int pos) {
                friendAdapter.deleteItem(pos);
            }
        });
    }
}