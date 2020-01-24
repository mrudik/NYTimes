package com.mrudik.nytimes.news.dagger;

import com.mrudik.nytimes.api.APIService;
import com.mrudik.nytimes.db.dao.ArticleDao;
import com.mrudik.nytimes.news.NewsPresenter;
import com.mrudik.nytimes.news.repository.NewsDataSource;
import com.mrudik.nytimes.news.repository.NewsRepository;
import com.mrudik.nytimes.news.repository.local.Local;
import com.mrudik.nytimes.news.repository.local.LocalNewsDataSource;
import com.mrudik.nytimes.news.repository.remote.Remote;
import com.mrudik.nytimes.news.repository.remote.RemoteNewsDataSource;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsModule {

    @Provides
    @NewsScope
    NewsPresenter provideNewsPresenter(NewsRepository repository) {
        return new NewsPresenter(repository);
    }

    @Provides
    @NewsScope
    @Local
    NewsDataSource provideLocalNewsDataSource(ArticleDao articleDao) {
        return new LocalNewsDataSource(articleDao);
    }

    @Provides
    @NewsScope
    @Remote
    NewsDataSource provideRemoteNewsDataSource(APIService apiService, ArticleDao articleDao) {
        return new RemoteNewsDataSource(apiService, articleDao);
    }

    @Provides
    @NewsScope
    NewsRepository provideRepository(@Local NewsDataSource localDataSource,
                                     @Remote NewsDataSource remoteDataSource) {
        return new NewsRepository(localDataSource, remoteDataSource);
    }
}