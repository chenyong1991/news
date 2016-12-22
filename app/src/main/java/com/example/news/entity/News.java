package com.example.news.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by c8469 on 2016/12/22.
 */
@DatabaseTable(tableName = "news_database.db")
public class News {
    @DatabaseField(columnName = "_id",generatedId = true)
    private long _id;
    @DatabaseField(columnName = "img")
    private String img;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "date")
    private String date;
    @DatabaseField(columnName = "url")
    private String url;

    public News() {
    }

    public News(String img, String title, String date, String url) {
        this.img = img;
        this.title = title;
        this.date = date;
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
