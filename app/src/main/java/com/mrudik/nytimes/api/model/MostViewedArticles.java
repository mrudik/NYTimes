package com.mrudik.nytimes.api.model;

import com.google.gson.annotations.SerializedName;
import com.mrudik.nytimes.db.entity.Article;

import java.util.List;

public class MostViewedArticles {
    @SerializedName("copyright")
    private String copyright;
    @SerializedName("results")
    private List<Article> articleList;

    public String getCopyright() {
        return copyright;
    }

    public List<Article> getArticleList() {
        return articleList;
    }
}