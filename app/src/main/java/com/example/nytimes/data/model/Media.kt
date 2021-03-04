package com.example.nytimes.data.model

import com.google.gson.annotations.SerializedName

data class Media(
    val approved_for_syndication: Int,
    val caption: String,
    val copyright: String,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadata>,
    val subtype: String,
    val type: String
)