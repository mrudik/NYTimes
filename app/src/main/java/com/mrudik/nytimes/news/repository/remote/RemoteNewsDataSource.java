package com.mrudik.nytimes.news.repository.remote;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.mrudik.nytimes.api.APIService;
import com.mrudik.nytimes.db.dao.ArticleDao;
import com.mrudik.nytimes.db.entity.Article;
import com.mrudik.nytimes.news.dagger.NewsScope;
import com.mrudik.nytimes.news.repository.NewsDataSource;
import com.mrudik.nytimes.news.repository.NewsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@NewsScope
public class RemoteNewsDataSource implements NewsDataSource {
    private final APIService apiService;
    private final ArticleDao articleDao;

    @Inject
    public RemoteNewsDataSource(APIService apiService, ArticleDao articleDao) {
        this.apiService = apiService;
        this.articleDao = articleDao;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    @Override
    public void loadArticles(@NonNull Callback callback) {
        Log.d(NewsRepository.TAG, "Load from: RemoteNewsDataSource");
        apiService.getMostViewedArticles(7)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mostViewedArticles -> {
                            final List<Article> articleList = mostViewedArticles.getArticleList();
                            int size = articleList != null
                                    ? articleList.size()
                                    : 0;

                            if (size > 0) {
                                Log.d(NewsRepository.TAG, "RemoteNewsDataSource: success");
                                insertArticlesToDatabase(articleList);
                                // No need to call success() here as we fetch our data from Database
                                // As Flowable - hence it will be automatically updated
                            } else {
                                Log.d(NewsRepository.TAG, "RemoteNewsDataSource: empty");
                                callback.empty();
                            }
                        },
                        throwable -> {
                            Log.d(NewsRepository.TAG, "RemoteNewsDataSource: failure");
                            callback.failure();
                        }
                );
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    private void insertArticlesToDatabase(@NonNull List<Article> articles) {
        Single.just(articles)
                .subscribeOn(Schedulers.io())
                .subscribe(articleDao::insert);
    }
}