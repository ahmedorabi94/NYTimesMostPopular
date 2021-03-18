package com.example.nytimes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nytimes.data.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}