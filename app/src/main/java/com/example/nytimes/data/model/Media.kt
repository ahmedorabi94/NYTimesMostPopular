package com.example.nytimes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.nytimes.data.db.ArticleMediaTypeConverter
import com.google.gson.annotations.SerializedName

@Entity
@TypeConverters(ArticleMediaTypeConverter::class)
data class Media(

    @PrimaryKey(autoGenerate = true)
    var mediaId: Int = 0,

    var approved_for_syndication: Int = 0,
    var caption: String = "",
    var copyright: String = "",

    @SerializedName("media-metadata")
    var mediaMetadata: List<MediaMetadata>? = null,

    var subtype: String = "",
    var type: String = ""
)