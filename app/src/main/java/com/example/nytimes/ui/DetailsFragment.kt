package com.example.nytimes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.nytimes.R
import com.example.nytimes.data.model.Result
import com.example.nytimes.databinding.FragmentDetailsBinding
import com.example.nytimes.di.Injectable


class DetailsFragment : Fragment(), Injectable {

    private var article: Result? = null
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            article = it.getParcelable("article")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_details, container, false)

        updateUI(article)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
    }


    private fun updateUI(article: Result?) {

        article?.let { item ->
            binding.source.text = item.source
            binding.title.text = item.title
            binding.abstractTv.text = item.abstract
            binding.lineTv.text = item.byline
            binding.dateTv.text = "Publish Date : ${item.published_date}"


            if (item.media.isNotEmpty()) {
                val meta = item.media[0]
                binding.captionTv.text = meta.caption
                binding.copyRightTv.text = "Copyright : ${meta.copyright}"

                if (meta.mediaMetadata.isNotEmpty()) {
                    val metaData = meta.mediaMetadata[2]
                    Glide.with(this).load(metaData.url).into(binding.imageView)

                }
            }

        }
    }
}