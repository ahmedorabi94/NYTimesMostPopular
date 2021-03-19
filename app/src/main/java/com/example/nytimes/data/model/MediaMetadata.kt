package com.example.nytimes.data.model

import androidx.room.Entity

@Entity
data class MediaMetadata(
    var format: String = "",
    var height: Int = 0,
    var url: String = "",
    var width: Int = 0
)