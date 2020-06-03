package com.example.myboard.util;

import android.view.View;

import com.example.myboard.util.ListAdapter;

public interface OnClickListener {
    public void onItemClick(ListAdapter.ViewHolder holder, View view, int position);
}
