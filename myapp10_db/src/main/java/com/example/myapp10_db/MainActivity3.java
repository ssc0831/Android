package com.example.myapp10_db;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.myapp10_db.databinding.ActivityMain3Binding;

import java.time.Year;
import java.util.Calendar;

public class MainActivity3 extends AppCompatActivity {
    ActivityMain3Binding binding;
    SQLiteDatabase sqLiteDatabase;
    String diaryDate;
    MydbHelper mydbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mydbHelper = new MydbHelper(this);

        sqLiteDatabase = mydbHelper.getWritableDatabase();
        mydbHelper.onUpgrade(sqLiteDatabase,1,2);

        Calendar calendar = Calendar.getInstance();
        int cYear = calendar.get(Calendar.YEAR);
        int cMonth = calendar.get(Calendar.MONTH);
        int cDay = calendar.get(Calendar.DAY_OF_MONTH);
        diaryDate=cYear+"-"+cMonth+"-"+cDay;
        binding.edtDiary.setText(diaryDate);
        binding.btnUpdate.setEnabled(true);
        binding.edtDiary.setText(readDiary(diaryDate));


        binding.dataPicker1.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                diaryDate = i+"-"+i1+"-"+i2;
                binding.edtDiary.setText(readDiary(diaryDate));

            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = "insert into myDiary values('"+diaryDate+"','"+binding.edtDiary.getText().toString()+"')";
                if(binding.btnUpdate.getText().equals("수정하기")){
                    sql = "update myDiary set content='"+binding.edtDiary.getText().toString()+"' where diaryDate='"+diaryDate+"'";
                }
                binding.btnUpdate.setEnabled(true);
                sqLiteDatabase.execSQL(sql);
                sqLiteDatabase.close();
                Toast.makeText(getApplicationContext(),"입력",Toast.LENGTH_SHORT).show();
            }
        });


//        binding.dataPicker1.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//            @Override
//            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
//                String date = i+"."+i1+"."+i2;
//                String todo = "";
//                sqLiteDatabase = mydbHelper.getWritableDatabase();
//                String sql="select content from myDiary where diaryDate="+date;
//                Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
//                if(cursor.moveToNext()){
//                    todo = cursor.getString(0);
//                    binding.edtDiary.setText(todo);
//                }
//                binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });
//            }
//        });

    }
    public static class MydbHelper extends SQLiteOpenHelper{

        public MydbHelper(@Nullable Context context) {
            super(context, "myDB", null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table if not exists myDiary(diaryDate char(10),content varchar(500))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            onCreate(sqLiteDatabase);
        }
    }
    private String readDiary(String diaryDate){
        String strResult ="";
        sqLiteDatabase = mydbHelper.getReadableDatabase();
        String sql = "select content from myDiary where diaryDate ='"+diaryDate+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToNext()){
            strResult =cursor.getString(0);
            binding.btnUpdate.setText("수정하기");
            Toast.makeText(this,"열기조회",Toast.LENGTH_SHORT).show();
        }else{
            binding.btnUpdate.setText("등록하기");
            binding.edtDiary.setText("");
            binding.edtDiary.setHint("할거 없음");
        }
        return strResult;
    }
}