package com.example.myboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myboard.db.Database;
import com.example.myboard.util.ListAdapter;
import com.example.myboard.util.OnClickListener;
import com.example.myboard.vo.BoardVO;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private static String Tag = "ListActivity";
    RecyclerView recyclerView;
    ListAdapter adapter;

    Button addButton;

    Database database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.d(Tag, "BoardList coCreate호출");

        ArrayList<BoardVO> listInfo = null;

        database = Database.getInstance(this);

        listInfo = database.allListQuery(); // 테이블에서 데이터 조회

        for (int i = 0 ; i < listInfo.size() ; i++){
            BoardVO rowInfo;
            rowInfo = listInfo.get(i);
            Log.d(Tag, "rowInfo 확인 : " + rowInfo.toString());
        }

        /*리사이클러뷰 세팅*/
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setItems(listInfo);

        //리스트에서 작성된 글 클릭
        adapter.setOnItemClickListener(new OnClickListener() {
            @Override
            public void onItemClick(ListAdapter.ViewHolder holder, View view, int position) {

                BoardVO item = adapter.getItem(position);
                Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                intent.putExtra("item", item);
                startActivity(intent);
            }
        });

        //새글쓰기
        addButton = findViewById(R.id.list_btn_add_write);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getBaseContext() 현재 활성화된 엑티비티의 context를 반환한다
                Intent intent = new Intent(getBaseContext() , AddActivity.class);
                startActivity(intent);
            }
        });
    }
}