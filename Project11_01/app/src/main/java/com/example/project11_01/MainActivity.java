package com.example.project11_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("리스트뷰");

        // 1. 데이터 생성
        String array[] = {"리스트 동적 추가", "커스텀 리스트 뷰", "이순신","유관순","강아지"};

        // 2. 리스트뷰 참조

        ListView listView = findViewById(R.id.listview1);

        // 3. Array Adapter 생성 (엑티비트이름, 리스트모양 , 데이터)

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice , array);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // 4. 리스트에 어댐터를 붙인다.

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent;

                switch (position) {

                    case 0:
                        intent = new Intent(MainActivity.this , DynamicActivity.class);
                        startActivity(intent);
                        break;

                    case 1:
                        intent = new Intent(MainActivity.this , CustomActivity.class);
                        startActivity(intent);
                        break;

                }

            }
        });

    }
}