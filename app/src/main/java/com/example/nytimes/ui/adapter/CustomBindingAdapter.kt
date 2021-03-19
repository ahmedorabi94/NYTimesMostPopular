package com.example.nytimes.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.nytimes.data.model.Article


@BindingAdapter("setArticleImage")
fun LoadImage(imageView: ImageView, item: Article) {

    item.let {

        if (it.media != null){
            if (it.media!!.isNotEmpty()) {
                val meta = it.media!![0]
//                if (meta.mediaMetadata.isNotEmpty()) {
//                    val metaData = meta.mediaMetadata[0]
//                    Glide.with(imageView.context).load(metaData.url).into(imageView)
//
//                }
            }
        }


    }


}




