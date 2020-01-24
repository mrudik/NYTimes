package com.mrudik.nytimes.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mrudik.nytimes.db.entity.Article;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ArticleDao {
    @Query("SELECT * FROM article")
    Flowable<List<Article>> getArticles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Article> articles);
}