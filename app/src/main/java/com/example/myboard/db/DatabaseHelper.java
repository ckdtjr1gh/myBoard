package com.example.myboard.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


// SQLite는 에뮬레이터에 저장되는 DB(앱을 삭제해도 남아있음)
public class DatabaseHelper extends SQLiteOpenHelper {
    public static String NAME = "board.db"; // DB 이름
    public static int VERSION = 1; // DB 버전

    private String tableName;

    public DatabaseHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    // DB가 처음 생성되면 호출됨
    @Override
    public void onCreate(SQLiteDatabase db) { // SQLiteDatabase : 전달받은 DB 객체
        println("onCreate 호출됨. ");

        // test라는 테이블이 존재하지 않으면 생성
        String sql = "create table if not exists T_BOARD("
                + "IDX_BOARD_PK INTEGER PRIMARY KEY autoincrement, "
                + "TITLE TEXT, "
                + "WRITER_NM TEXT, "
                + "CONTENT TEXT, "
                + "CRT_DT TEXT, "
                + "UDT_DT TEXT, "
                + "DEL_YN TEXT)";
        db.execSQL(sql);

    }

    // sqliteDatabase 객체가 참조되면 호출
    @Override
    public void onOpen(SQLiteDatabase db) {
        println("onOpen 호출됨.");
    }

    // onUpgrade() : 현재 데이터베이스 버전이 이전에 사용하고 있는 버전과 다를 경우 호출됨
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        println("onUpgrade 호출됨 : " + oldVersion + " -> " + newVersion);

        if (newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS T_BOARD"); //  테이블이 존재하면 삭제
        }
    }
    //private

    public void println(String data){ Log.d("DatabaseHelper", data); }
}




