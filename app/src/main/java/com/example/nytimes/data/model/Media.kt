package com.example.nytimes.data.model

import androidx.room.Entity
import androidx.room.TypeConverters
import com.example.nytimes.data.db.ArticleMediaTypeConverter
import com.google.gson.annotations.SerializedName

@Entity
@TypeConverters(ArticleMediaTypeConverter::class)
data class Media(
    var approved_for_syndication: Int = 0,
    var caption: String = "",
    var copyright: String = "",

    @SerializedName("media-metadata")
    // @Ignore
    var mediaMetadata: List<MediaMetadata>? = null,

    var subtype: String = "",
    var type: String = ""
)