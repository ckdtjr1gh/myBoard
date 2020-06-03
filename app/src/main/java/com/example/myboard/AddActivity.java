package com.example.myboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myboard.db.Database;
import com.example.myboard.vo.BoardVO;

public class AddActivity extends AppCompatActivity {

    Database database;

    EditText titleTxt;
    EditText writerNameTxt;
    EditText contentTxt;

    Button backButton;
    Button addButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //디비 객체 가져오기
        database = Database.getInstance(this);

        titleTxt = findViewById(R.id.add_txt_title);
        writerNameTxt = findViewById(R.id.add_txt_writer_name);
        contentTxt = findViewById(R.id.add_txt_content);

        //새글쓰기 완료
        addButton = findViewById(R.id.add_btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //VO 작성
                BoardVO boardVO = new BoardVO();
                boardVO.setTitle(String.valueOf(titleTxt.getText()));
                boardVO.setWriterName(String.valueOf(writerNameTxt.getText()));
                boardVO.setContent(String.valueOf(contentTxt.getText()));

                //DB insert
                database.insertQuery(boardVO);

                //리스트로 이동
                Intent intent = new Intent(getBaseContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        //뒤로가기
        backButton = findViewById(R.id.add_btn_back_to_list);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    //뒤로가기 재정의
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
