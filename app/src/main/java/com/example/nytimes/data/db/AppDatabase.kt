package com.example.nytimes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nytimes.data.model.Article
import com.example.nytimes.data.model.Media
import com.example.nytimes.data.model.MediaMetadata

@Database(entities = [Article::class,Media::class,MediaMetadata::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}