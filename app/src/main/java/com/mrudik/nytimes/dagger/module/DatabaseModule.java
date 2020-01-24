package com.mrudik.nytimes.dagger.module;

import android.content.Context;

import androidx.room.Room;

import com.mrudik.nytimes.Constants;
import com.mrudik.nytimes.dagger.AppScope;
import com.mrudik.nytimes.db.NYTimesDatabase;
import com.mrudik.nytimes.db.dao.ArticleDao;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @Provides
    @AppScope
    NYTimesDatabase provideDatabase(Context appContext) {
        return Room.databaseBuilder(
                appContext,
                NYTimesDatabase.class,
                Constants.DATABASE_NAME
        ).build();
    }

    @Provides
    @AppScope
    ArticleDao provideArticleDao(NYTimesDatabase database) {
        return database.articleDao();
    }
}