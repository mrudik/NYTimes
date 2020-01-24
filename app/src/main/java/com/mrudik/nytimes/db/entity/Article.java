package com.mrudik.nytimes.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Article {
    @PrimaryKey
    @SerializedName("id")
    private long articleId;
    @SerializedName("title")
    private String title;
    @SerializedName("abstract")
    private String description;
    @SerializedName("url")
    private String url;

    public long getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}