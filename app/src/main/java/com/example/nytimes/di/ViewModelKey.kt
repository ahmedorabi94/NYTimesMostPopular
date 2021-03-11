package com.example.nytimes.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


// ViewModelKey custom annotation takes Class<out ViewModel> as value for the key in our Map
// to create a map that is need for injecting in our ViewModelFactory class.
@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)