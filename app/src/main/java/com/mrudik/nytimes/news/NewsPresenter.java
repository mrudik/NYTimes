package com.mrudik.nytimes.news;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.mrudik.nytimes.db.entity.Article;
import com.mrudik.nytimes.news.repository.NewsDataSource;
import com.mrudik.nytimes.news.repository.NewsRepository;

import java.util.List;

import javax.inject.Inject;

public class NewsPresenter implements NewsContract.Presenter, LifecycleObserver {
    private final NewsRepository repository;
    private NewsContract.View view;

    @Inject
    public NewsPresenter(NewsRepository repository) {
        this.repository = repository;
    }

    public void loadNews() {
        view.showLoading();
        repository.loadArticles(new NewsDataSource.Callback() {
            @Override
            public void success(@NonNull List<Article> articleList) {
                view.hideLoading();
                view.showNews(articleList);
            }

            @Override
            public void failure() {
                view.hideLoading();
                view.showError("Loading error");
            }

            @Override
            public void empty() {
                view.hideLoading();
                view.showError("Data is empty");
            }
        });
    }

    public void setView(NewsContract.View view) {
        this.view = view;
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void clearView() {
        this.view = null;
    }
}