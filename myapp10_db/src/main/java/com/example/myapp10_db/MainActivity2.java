package com.example.myapp10_db;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapp10_db.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MydbHelper mydbHelper=new MydbHelper(this);

        binding.btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = mydbHelper.getWritableDatabase();
                mydbHelper.onUpgrade(sqLiteDatabase,1,2);
                sqLiteDatabase.close();
                binding.btnSelect.callOnClick();
                Toast.makeText(getApplicationContext(),"초기화 완료", Toast.LENGTH_SHORT).show();

            }
        });
        binding.btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = mydbHelper.getWritableDatabase();
                String sql="Insert into groupTBL values(?,?)";
                SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
                statement.bindString(1,binding.editName.getText().toString());
                statement.bindString(2,binding.editNumber.getText().toString());
                statement.execute();

                sqLiteDatabase.close();
                binding.btnSelect.callOnClick();

                Toast.makeText(getApplicationContext(),"입력", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase=mydbHelper.getReadableDatabase();
                String sql = "Select * from groupTBL";
                Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
                String strName = "그룹 이름: \n";
                String strNumber= "그룹 인원 : \n";
                while(cursor.moveToNext()){
                    strName += cursor.getString(0) + "\n";
                    strNumber += cursor.getString(1)+"\n";
                }
                binding.edtNameResult.setText(strName);
                binding.edtNumberResult.setText(strNumber);
                cursor.close();
                sqLiteDatabase.close();

            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = mydbHelper.getWritableDatabase();
                String sql = "update groupTBL set gNumber=? where gName=?";
                SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
                statement.bindString(1,binding.editNumber.getText().toString());
                statement.bindString(2,binding.editName.getText().toString());
                statement.execute();
                sqLiteDatabase.close();
                binding.btnSelect.callOnClick();
                Toast.makeText(getApplicationContext(),"수정완료", Toast.LENGTH_SHORT).show();


            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = mydbHelper.getWritableDatabase();
                String sql = "Delete from groupTBL where gName = '"+binding.editName.getText().toString()+"'";
                sqLiteDatabase.execSQL(sql);
                sqLiteDatabase.close();
                binding.btnSelect.callOnClick();
                Toast.makeText(getApplicationContext(),"삭제완료", Toast.LENGTH_SHORT).show();

            }
        });
        binding.btnInsertAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public class MydbHelper extends SQLiteOpenHelper{

        public MydbHelper(@Nullable Context context) {
            super(context,"groupDB",null,1,null);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table groupTBL(gName char(20) primary key,gNumber integer);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("Drop table if exists groupTBL");
            onCreate(sqLiteDatabase);
        }
    }
}