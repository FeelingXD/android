package com.example.project11_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class CustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        setTitle("커스텀 리스트뷰");

        ListView customlistView = findViewById(R.id.customListView);
        ListViewAdapter adapter = new ListViewAdapter();
        customlistView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this ,R.drawable.rabbit),"토끼","좋은 단백질 공급원");
        adapter.addItem(ContextCompat.getDrawable(this ,R.drawable.dog),"개","좋은 단백질 공급원");

        customlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 선택 시 행동


            }
        });
    }

}