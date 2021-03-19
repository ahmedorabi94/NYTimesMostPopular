package com.example.nytimes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MediaMetadata(

    @PrimaryKey(autoGenerate = true)
    var mediaMetaId: Int = 0,

    var format: String = "",
    var height: Int = 0,
    var url: String = "",
    var width: Int = 0
)