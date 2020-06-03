package com.example.myboard.vo;

import android.widget.TextView;

import java.io.Serializable;

public class BoardVO implements Serializable {
    private String idx;
    private String title;
    private String writerName;
    private String content;
    private String currentTime;
    private String updateTime;
    private String delCheck;


    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelCheck() {
        return delCheck;
    }

    public void setDelCheck(String delCheck) {
        this.delCheck = delCheck;
    }

    @Override
    public String toString() {
        return "BoardVO{" +
                "idx='" + idx + '\'' +
                ", title='" + title + '\'' +
                ", writerName='" + writerName + '\'' +
                ", content='" + content + '\'' +
                ", currentTime='" + currentTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", delCheck='" + delCheck + '\'' +
                '}';
    }
}
