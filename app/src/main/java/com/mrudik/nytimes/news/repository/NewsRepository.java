package com.mrudik.nytimes.news.repository;

import androidx.annotation.NonNull;

import com.mrudik.nytimes.db.entity.Article;
import com.mrudik.nytimes.news.dagger.NewsScope;
import com.mrudik.nytimes.news.repository.local.Local;
import com.mrudik.nytimes.news.repository.remote.Remote;

import java.util.List;

import javax.inject.Inject;

@NewsScope
public class NewsRepository implements NewsDataSource {
    public static final String TAG = "NewsRepositoryTag";

    private final NewsDataSource localDataSource;
    private final NewsDataSource remoteDataSource;

    @Inject
    public NewsRepository(@Local NewsDataSource localDataSource,
                          @Remote NewsDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void loadArticles(@NonNull Callback callback) {
        localDataSource.loadArticles(new Callback() {
            @Override
            public void success(@NonNull List<Article> articleList) {
                callback.success(articleList);
            }

            @Override
            public void failure() {
                remoteDataSource.loadArticles(callback);
            }

            @Override
            public void empty() {
                remoteDataSource.loadArticles(callback);
            }
        });
    }
}