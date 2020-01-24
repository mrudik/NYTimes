package com.mrudik.nytimes.news.repository.local;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.mrudik.nytimes.db.dao.ArticleDao;
import com.mrudik.nytimes.news.dagger.NewsScope;
import com.mrudik.nytimes.news.repository.NewsDataSource;
import com.mrudik.nytimes.news.repository.NewsRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@NewsScope
public class LocalNewsDataSource implements NewsDataSource {
    private final ArticleDao articleDao;

    @Inject
    public LocalNewsDataSource(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    @Override
    public void loadArticles(@NonNull Callback callback) {
        Log.d(NewsRepository.TAG, "Load from: LocalNewsDataSource");

        articleDao.getArticles()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articles -> {
                    if (articles.size() > 0) {
                        Log.d(NewsRepository.TAG, "LocalNewsDataSource: success");
                        callback.success(articles);
                    } else {
                        Log.d(NewsRepository.TAG, "LocalNewsDataSource: empty");
                        callback.empty();
                    }
                });
    }
}