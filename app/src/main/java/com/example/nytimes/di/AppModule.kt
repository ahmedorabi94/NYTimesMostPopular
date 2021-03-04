package com.example.nytimes.di

import com.example.nytimes.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    fun provideGson(): Gson {


        return GsonBuilder().create()
    }


    @Provides
    fun provideHttpClient(): OkHttpClient {

//        File httpCacheDirectory = new File(application.getCacheDir(), "http-cache");
//        int cacheSize = 10 * 1024 * 1024; // 10 MiB
//        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            //    .addConverterFactory(MoshiConverterFactory.create())
            //  .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("https://api.nytimes.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}