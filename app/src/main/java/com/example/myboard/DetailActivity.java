package com.example.myboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myboard.util.Dialog;
import com.example.myboard.vo.BoardVO;

public class DetailActivity extends AppCompatActivity {
    private static String Tag = "DetailActivity";

    TextView idxTxt;
    TextView titleTxt;
    TextView writerNameTxt;
    TextView contentTxt;
    TextView currentTimeTxt;
    TextView updateTimeTxt;

    Button backButton;
    Button updateButton;
    Button deleteButton;

    Dialog dialog;

    BoardVO boardVO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d(Tag,"DetailActivity 호출");

        Intent receivedIntent = getIntent();
        if(receivedIntent != null){

            idxTxt = findViewById(R.id.detail_txt_index);
            titleTxt = findViewById(R.id.detail_txt_title);
            writerNameTxt = findViewById(R.id.detail_txt_writer_name);
            contentTxt = findViewById(R.id.detail_txt_content);
            currentTimeTxt = findViewById(R.id.detail_txt_current_time);
            updateTimeTxt = findViewById(R.id.detail_txt_update_time);

            //인텐트로 전달받은 VO 객체를 전역변수로 만든다.
            this.boardVO = (BoardVO) receivedIntent.getSerializableExtra("item");

            idxTxt.setText(boardVO.getIdx());
            titleTxt.setText(boardVO.getTitle());
            writerNameTxt.setText(boardVO.getWriterName());
            contentTxt.setText(boardVO.getContent());
            currentTimeTxt.setText(boardVO.getCurrentTime());
            updateTimeTxt.setText(boardVO.getUpdateTime());

            //삭제하기
            deleteButton = findViewById(R.id.detail_btn_delete);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //다이얼로그
                    dialog = new Dialog(DetailActivity.this, "삭제", boardVO.getIdx());
                    dialog.request();
                }
            });

            //수정 페이지로 이동 하기
            updateButton = findViewById(R.id.detail_btn_update);
            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), UpdateActivity.class);
                    intent.putExtra("idx", boardVO.getIdx());
                    startActivity(intent);
                }
            });

            //뒤로가기
            backButton = findViewById(R.id.detail_btn_back_to_list);
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










