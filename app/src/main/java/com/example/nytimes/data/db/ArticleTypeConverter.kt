package com.example.nytimes.data.db

import androidx.room.TypeConverter
import com.example.nytimes.data.model.Media
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ArticleTypeConverter {

    @TypeConverter
    fun fromListToString(media: List<Media>?): String? {

        if (media == null) {
            return null
        }

        val gson = Gson()

        return gson.toJson(media)

    }


    @TypeConverter
    fun fromString(value: String): List<Media> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}

