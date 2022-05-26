package com.example.project11_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class DynamicActivity extends AppCompatActivity {

    EditText edtName;
    ListView dynamicListView;
    Button insert , delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        setTitle("동적리스트뷰");

        edtName = findViewById(R.id.edtName);
        insert = findViewById(R.id.btnInsert);
        delete = findViewById(R.id.btnDelete);

        // tmp arraylist

        ArrayList<String> item = new ArrayList<String>();

        // 2. 리스트뷰 객체 참조

        dynamicListView = findViewById(R.id.dynamicListView);

        // 3. array adapter

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,  item);

        dynamicListView.setAdapter(adapter);
        dynamicListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.add(edtName.getText().toString());
                edtName.setText("");
                edtName.setHint("이름을 입력하세요");
                adapter.notifyDataSetChanged();
            }
        });


    }
}