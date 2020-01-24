package com.mrudik.nytimes.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mrudik.nytimes.Constants;
import com.mrudik.nytimes.db.dao.ArticleDao;
import com.mrudik.nytimes.db.entity.Article;

@Database(entities = {Article.class}, version = Constants.DATABASE_VERSION, exportSchema = false)
public abstract class NYTimesDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}