package com.example.nytimes.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.nytimes.data.db.ArticleTypeConverter
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Entity(tableName = "article")
@TypeConverters(ArticleTypeConverter::class)
@Parcelize
data class Article(

    @PrimaryKey(autoGenerate = true)
    var articleId: Int = 0,

    @SerializedName("id")
    var artId: Long = 0,

    var byline: String = "",

    @ColumnInfo(name = "summary")
    @SerializedName("abstract")
    var summary: String = "",

    // @Ignore
    var media: @RawValue List<Media>? = null,

    var published_date: String = "",
    var source: String = "",
    var title: String = "",


    //  val adx_keywords: String,
    //  val asset_id: Long,

    // val column: @RawValue Any,
    //  val des_facet: List<String>,
    //  val eta_id: Int,
    //  val geo_facet: List<String>,
    //  val nytdsection: String,
    //  val org_facet: List<String>,
    //  val per_facet: List<String>,
//    var section: String,
//    var subsection: String,
//    var type: String,
//    var updated: String,
//    var uri: String,
//    var url: String
) : Parcelable


