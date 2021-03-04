package com.example.nytimes.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nytimes.viewmodels.HomeViewModel
import com.example.nytimes.viewmodels.NyTimesViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: NyTimesViewModelFactory): ViewModelProvider.Factory
}