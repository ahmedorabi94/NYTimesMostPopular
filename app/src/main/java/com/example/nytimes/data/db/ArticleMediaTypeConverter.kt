package com.example.nytimes.data.db

import androidx.room.TypeConverter
import com.example.nytimes.data.model.MediaMetadata
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ArticleMediaTypeConverter {

    @TypeConverter
    fun fromListToString(media: List<MediaMetadata>?): String? {

        if (media == null) {
            return null
        }

        val gson = Gson()

        return gson.toJson(media)

    }


    @TypeConverter
    fun fromString(value: String): List<MediaMetadata> {
        val listType = object : TypeToken<List<MediaMetadata>>() {}.type
        return Gson().fromJson(value, listType)
    }

}