package com.example.myboard.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myboard.vo.BoardVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Database {
    private static String Tag = "Database";

    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    private static Context context = null;

    // 싱글톤 객체
    private static Database instance;

    //싱글톤
    private Database(Context context){
        this.context = context;
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase(); // 헬퍼에서 sqliteDatabase 객체 참조
    }
    public static Database getInstance(Context context){
        Log.d(Tag, "Database getInstance 호출");
        if(instance == null){
            instance = new Database(context);
        }else {
            context = context;
        }
        return instance;
    }
    //테이블 클리어
    public void clearTable(){
        database.execSQL("delete from T_BOARD");
    }


    //모두조회
    public ArrayList allListQuery() {
        println("listQuery 호출됨.");

        // Cursor : 테이블 데이터를 raw(행)형태로 컨트롤
        // SQL 실행하고 Cursor 객체 반환받기
        Cursor cursor = database.rawQuery("select IDX_BOARD_PK, TITLE, WRITER_NM, CONTENT, CRT_DT, UDT_DT, DEL_YN from T_BOARD where DEL_YN = 'N' ", null);
        int recordCount = cursor.getCount(); // 데이터 행의 개수
        println("레코드 개수 : " + recordCount);


        ArrayList<BoardVO> list = new ArrayList<>();
        for (int i = 0; i < recordCount; i++){
            cursor.moveToNext(); // 다음 레코드로 넘기기

            BoardVO boardVO = new BoardVO();
            boardVO.setIdx(Integer.toString(cursor.getInt(0)));
            boardVO.setTitle(cursor.getString(1));
            boardVO.setWriterName(cursor.getString(2));
            boardVO.setContent(cursor.getString(3));
            boardVO.setCurrentTime(cursor.getString(4));
            boardVO.setUpdateTime(cursor.getString(5));
            boardVO.setDelCheck(cursor.getString(6));

            list.add(boardVO);
        }
        return list;
    }

    //선택조회
    public BoardVO selectQuery(String idx) {
        println("selectQuery 호출됨.");
        int index = Integer.parseInt(idx);

        // Cursor : 테이블 데이터를 raw(행)형태로 컨트롤
        // SQL 실행하고 Cursor 객체 반환받기
        Cursor cursor = database.rawQuery("select IDX_BOARD_PK, TITLE, WRITER_NM, CONTENT, CRT_DT, UDT_DT, DEL_YN from T_BOARD where DEL_YN = 'N' AND IDX_BOARD_PK = '"+index+"'", null);
        int recordCount = cursor.getCount(); // 데이터 행의 개수
        println("레코드 개수 : " + recordCount);

        cursor.moveToNext();

        BoardVO boardVO = new BoardVO();
        boardVO.setIdx(Integer.toString(cursor.getInt(0)));
        boardVO.setTitle(cursor.getString(1));
        boardVO.setWriterName(cursor.getString(2));
        boardVO.setContent(cursor.getString(3));
        boardVO.setCurrentTime(cursor.getString(4));
        boardVO.setUpdateTime(cursor.getString(5));
        boardVO.setDelCheck(cursor.getString(6));

        return boardVO;
    }



    //추가
    public void insertQuery(BoardVO vo) {
        println("insertRecord 호출됨.");

        //날짜 시간
        SimpleDateFormat dateformat = new SimpleDateFormat("y.MM.dd  H:m");
        String date = dateformat.format(Calendar.getInstance().getTime());

        database.execSQL("insert into T_BOARD(TITLE, WRITER_NM, CONTENT, CRT_DT, UDT_DT, DEL_YN)"
                + " values "
                + "('"+vo.getTitle()+"', '"+ vo.getWriterName()+"', '"+ vo.getContent()+"', '"+ date+"', '', 'N')");
    }

    //삭제
    public void deleteQuery(String idx){
        int index = Integer.parseInt(idx);
        database.execSQL("update T_BOARD set DEL_YN = 'Y' where IDX_BOARD_PK = '"+index+"'");
    }

    //업데이트
    public void updateQuery(String idx, BoardVO vo){
        SimpleDateFormat dateformat = new SimpleDateFormat("y.MM.dd  H:m");
        String date = dateformat.format(Calendar.getInstance().getTime());
        Log.d(Tag, "updateQuery 호출 idx 확인 : " + idx);
        Log.d(Tag, "updateQuery 호출 vo 확인 : " + vo.toString());
        int index = Integer.parseInt(idx);
        database.execSQL("update T_BOARD set " +
                " TITLE = '"+vo.getTitle()+"', " +
                " WRITER_NM = '"+vo.getWriterName()+"', " +
                " CONTENT = '"+vo.getContent()+"', " +
                " UDT_DT = '"+date+"'" +
                " where IDX_BOARD_PK = '"+index+"'");
    }

    public void println(String str) {
        Log.d(Tag, "str 확인 : " + str);
    }

}
