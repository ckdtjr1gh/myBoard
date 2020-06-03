package com.example.myboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myboard.db.Database;
import com.example.myboard.util.Dialog;
import com.example.myboard.vo.BoardVO;

public class UpdateActivity extends AppCompatActivity {

    Database database;

    EditText titleTxt;
    EditText writerNameTxt;
    EditText contentTxt;

    Button backButton;
    Button submitButton;

    String idx;

    Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        database = Database.getInstance(this);

        Intent receivedIntent = getIntent();
        if(receivedIntent != null){

            //업데이트에 사용할 인덱스 받기
            idx = receivedIntent.getStringExtra("idx");

            titleTxt = findViewById(R.id.update_txt_title);
            writerNameTxt = findViewById(R.id.update_txt_writer_name);
            contentTxt = findViewById(R.id.update_txt_content);

            //전달받은 인덱스로 DB에서 가져오기
            BoardVO boardVO = database.selectQuery(idx);

            //이전에 작성된 내용 보여주기
            titleTxt.setHint(boardVO.getTitle());
            writerNameTxt.setHint(boardVO.getWriterName());
            contentTxt.setHint(boardVO.getContent());

            //수정 완료
            submitButton = findViewById(R.id.update_btn_update);
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    BoardVO boardVO = new BoardVO();
                    boardVO.setTitle(String.valueOf(titleTxt.getText()));
                    boardVO.setWriterName(String.valueOf(writerNameTxt.getText()));
                    boardVO.setContent(String.valueOf(contentTxt.getText()));

                    //수정하지 않은 정보는 기존 정보로 세팅
                    if(boardVO.getTitle().equals("")){
                        boardVO.setTitle(String.valueOf(titleTxt.getHint()));
                    }
                    if(boardVO.getWriterName().equals("")){
                        boardVO.setWriterName(String.valueOf(writerNameTxt.getHint()));
                    }
                    if(boardVO.getContent().equals("")){
                        boardVO.setContent(String.valueOf(contentTxt.getHint()));
                    }

                    //다이얼 로그
                    dialog = new Dialog(UpdateActivity.this, "수정", idx, boardVO);
                    dialog.request();
                }
            });

            //뒤로가기
            backButton = findViewById(R.id.update_btn_back_to_detail);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
