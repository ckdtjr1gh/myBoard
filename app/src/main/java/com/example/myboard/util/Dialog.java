package com.example.myboard.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.example.myboard.vo.BoardVO;
import com.example.myboard.db.Database;
import com.example.myboard.ListActivity;

public class Dialog {
    private static String Tag = "Dialog";

    AlertDialog dialog;     //다이얼 로그 객체
    Context context;        //컨택스트
    String keyword;         //삭제인지 수정인지
    String idx;             //처리할 인덱스
    BoardVO boardVO;        //VO
    Database database;      //데이터베이스 객체

    //삭제시 사용되는 생성자
    public Dialog(Context context, String keyword, String idx){
        this.context = context;
        this.keyword = keyword;
        this.idx = idx;
        this.database = Database.getInstance(context);
    }

    //수정시 사용되는 생성자
    public Dialog(Context context, String keyword, String idx, BoardVO vo){
        this.context = context;
        this.keyword = keyword;
        this.idx = idx;
        this.boardVO = vo;
        this.database = Database.getInstance(context);
    }

    //다이얼 로그
    public void request() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //다이얼로그 문구
        builder.setTitle("정말" + keyword + " 하시겠습니까?");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(keyword.equals("삭제")){
                    sendDeleteDB();
                }else if(keyword.equals("수정")){
                    sendUpdateDB();
                }
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    //DB에서 삭제
    private void sendDeleteDB() {
        database.deleteQuery(idx);

        Intent intent = new Intent(context, ListActivity.class);
        context.startActivity(intent);
    }

    //DB에서 수정
    private void sendUpdateDB() {
        database.updateQuery(idx, boardVO);

        Intent intent = new Intent(context, ListActivity.class);
        context.startActivity(intent);
    }
}
