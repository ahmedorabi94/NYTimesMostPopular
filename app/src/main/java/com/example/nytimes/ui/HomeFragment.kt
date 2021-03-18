package com.example.nytimes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.nytimes.R
import com.example.nytimes.data.api.Resource
import com.example.nytimes.data.model.Article
import com.example.nytimes.databinding.FragmentHomeBinding
import com.example.nytimes.di.Injectable
import com.example.nytimes.ui.adapter.ArticleAdapter
import com.example.nytimes.ui.adapter.ArticleCallback
import com.example.nytimes.ui.viewmodels.HomeViewModel
import com.example.nytimes.util.EspressoIdlingResource
import timber.log.Timber
import javax.inject.Inject


class HomeFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val articleCallback = object : ArticleCallback {
        override fun onArticleClick(item: Article) {
            Timber.e("$item")

            val bundle = Bundle()
            bundle.putParcelable("article", item)

            Navigation.findNavController(binding.root)
                .navigate(R.id.action_homeFragment_to_detailsFragment, bundle)


        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this

        (activity as AppCompatActivity).supportActionBar?.show()

        EspressoIdlingResource.increment()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)


        viewModel.articleResponse.observe(viewLifecycleOwner, {
            it?.let { resource ->


                when (resource.status) {
                    Resource.Status.SUCCESS -> {

                        Timber.e("SUCCESS")
                        Timber.e("$resource.data")

                        binding.progressbar.visibility = View.GONE
                        binding.recyclerViewMain.visibility = View.VISIBLE

                        val adapter = ArticleAdapter(articleCallback)

                        resource.data?.let {
                            EspressoIdlingResource.decrement()
                            adapter.submitList(resource.data.results)
                            binding.recyclerViewMain.adapter = adapter
                        }

                    }
                    Resource.Status.ERROR -> {
                        binding.progressbar.visibility = View.GONE
                        binding.recyclerViewMain.visibility = View.VISIBLE
                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    }
                    Resource.Status.LOADING -> {
                        binding.progressbar.visibility = View.VISIBLE
                        binding.recyclerViewMain.visibility = View.GONE
                    }
                }
            }
        })


        return binding.root
    }


}