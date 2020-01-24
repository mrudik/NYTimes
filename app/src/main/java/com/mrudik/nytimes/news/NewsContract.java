package com.mrudik.nytimes.news;

import com.mrudik.nytimes.db.entity.Article;

import java.util.List;

public interface NewsContract {
    interface View {
        void showLoading();
        void hideLoading();
        void showNews(final List<Article> newsList);
        void showError(final String errorMessage);
    }

    interface Presenter {
        void setView(View view);
        void clearView();
        void loadNews();
    }
}