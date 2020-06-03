package com.example.myboard.util;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myboard.R;
import com.example.myboard.vo.BoardVO;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
        implements OnClickListener {
    ArrayList<BoardVO> items = new ArrayList<>();
    OnClickListener listener;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        BoardVO item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(BoardVO item) {
        items.add(item);
    }

    public void setItems(ArrayList<BoardVO> items) {
        this.items = items;
    }

    public BoardVO getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, BoardVO item) {
        items.set(position, item);
    }

    public void setOnItemClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idxTxt;
        TextView titleTxt;
        TextView writerTxt;
        TextView contentTxt;
        TextView curtime;
        TextView updatetime;


        public ViewHolder(View itemView, final OnClickListener listener) {
            super(itemView);

            idxTxt = itemView.findViewById(R.id.list_txt_idx);
            titleTxt = itemView.findViewById(R.id.list_txt_title);
            writerTxt = itemView.findViewById(R.id.list_txt_writer);
            contentTxt = itemView.findViewById(R.id.list_txt_content);
            curtime = itemView.findViewById(R.id.list_txt_current_time);
            updatetime = itemView.findViewById(R.id.list_txt_update_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(BoardVO item) {
            idxTxt.setText(item.getIdx());
            titleTxt.setText(item.getTitle());
            writerTxt.setText(item.getWriterName());
            contentTxt.setText(item.getContent());
            curtime.setText(item.getCurrentTime());
            updatetime.setText(item.getUpdateTime());
        }
    }
}
