package com.mrudik.nytimes.news.repository;

import androidx.annotation.NonNull;

import com.mrudik.nytimes.db.entity.Article;

import java.util.List;

public interface NewsDataSource {
    void loadArticles(@NonNull Callback callback);

    interface Callback {
        void success(@NonNull List<Article> articleList);
        void failure();
        void empty();
    }
}