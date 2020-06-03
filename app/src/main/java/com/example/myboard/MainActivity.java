package com.example.myboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myboard.db.Database;

public class MainActivity extends AppCompatActivity {
    Button clearTableBtn;
    Button listBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clearTableBtn = findViewById(R.id.clear_table);
        clearTableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database database = Database.getInstance(getBaseContext());
                database.clearTable();
            }
        });

        listBtn = findViewById(R.id.go_to_list);
        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ListActivity.class);
                startActivity(intent);
            }
        });


    } // end onCreate()
} // end MainActivity
