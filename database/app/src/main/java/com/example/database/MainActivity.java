package com.example.database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    myDBHelper myDBHelper;

    EditText edtName, edtNumber, edtNameResult,edtNumberResult;
    Button btnInit, btnInsert, btnSelect, btnUpdate, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtNameResult = findViewById(R.id.edtNameResult);
        edtNumberResult = findViewById(R.id.edtNumberResult);
        btnInit = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSelect);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);


        myDBHelper myDBHelper = new myDBHelper(this);
//        db = myDBHelper.getWritableDatabase();
//        db.close();

 /*       btnInsert.setOnClickListener(new View.OnClickListener() { // Insert with Query
            @Override
            public void onClick(View view) {
                db= myDBHelper.getWritableDatabase();
                db.execSQL("insert into groupTBL values ('"
                        + edtName.getText().toString() +"' , "
                        +edtNumber.getText().toString() +");");
                db.close();
                Toast.makeText(getApplicationContext(),"입력완료", Toast.LENGTH_SHORT).show();
                edtName.setFocusable(true);
                edtNumber.setText("");


                InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(edtName.getWindowToken(),0);
                edtName.requestFocus();
            }
        });*/

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = myDBHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("gName",edtName.getText().toString());
                values.put("gNumber",edtNumber.getText().toString());
                db.insert("groupTBL",null,values);
                db.close();
                Toast.makeText(getApplicationContext(),"입력완료", Toast.LENGTH_SHORT).show();
                edtName.setFocusable(true);
                edtNumber.setText("");


                InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(edtName.getWindowToken(),0);
                edtName.requestFocus();

            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db= myDBHelper.getReadableDatabase();
                Cursor cursor;
                cursor = db.rawQuery("select * from groupTBL;",null);

                String strNames = "그룹이름" +"\r\n" +"------------" + "\r\n";
                String strNumbers = "그룹인원" +"\r\n" +"------------" + "\r\n";
                while(cursor.moveToNext()){
                    strNames += cursor.getString(0)+ "\r\n";
                    strNumbers += cursor.getString(1)+ "\r\n";
                }
                edtNameResult.setText(strNames);
                edtNumberResult.setText(strNumbers);

                cursor.close();
                db.close();
            }
        });
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(db,1,2);
                db.close();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = myDBHelper.getWritableDatabase();
                String sql = "Delete from  groupTBL where gName ='"
                        + edtName.getText().toString() +"'; ";
                if(edtName.getText().toString()!="") {
                    db.execSQL(sql);
                }
                db.close();
                btnSelect.callOnClick();
            }
        });

    }

    public class myDBHelper extends SQLiteOpenHelper{ 
        myDBHelper myDBHelper;
        SQLiteDatabase db;
        
        //db 생성
        public myDBHelper(@Nullable Context context) {
            super(context,"groupDB.db",null,1);
            
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table groupTBL(gName char(20) primary key, gNumber integer);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int old_version, int new_version) {
            db.execSQL("drop table if exists groupTBL");
            onCreate(db);
        }
    }

}