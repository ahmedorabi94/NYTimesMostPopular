package com.example.nytimes.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.nytimes.data.model.Result


@BindingAdapter("setArticleImage")
fun LoadImage(imageView: ImageView, item: Result) {

    item.let {

        if (it.media.isNotEmpty()) {
            val meta = it.media[0]
            if (meta.mediaMetadata.isNotEmpty()) {
                val metaData = meta.mediaMetadata[0]
                Glide.with(imageView.context).load(metaData.url).into(imageView)

            }
        }
    }


}




