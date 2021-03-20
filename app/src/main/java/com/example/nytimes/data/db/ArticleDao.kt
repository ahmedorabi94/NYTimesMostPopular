package com.example.nytimes.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nytimes.data.model.Article


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<Article>)

    @Query("Select * from article")
     fun getAllArticles(): List<Article>


    @Query("Delete from article")
    suspend fun deleteAllArticles()

}