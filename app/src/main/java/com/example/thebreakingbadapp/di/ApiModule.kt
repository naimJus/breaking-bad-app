package com.example.thebreakingbadapp.di

import com.example.thebreakingbadapp.BuildConfig
import com.example.thebreakingbadapp.remote.ApiInterface
import com.example.thebreakingbadapp.remote.Repository
import com.example.thebreakingbadapp.remote.RepositoryImpl
import com.example.thebreakingbadapp.util.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class ApiModule {

    companion object {
        @Provides
        @Singleton
        fun provideOkHttp() =
            OkHttpClient.Builder().apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                }
            }.build()

        @Provides
        @Singleton
        fun provideGson() = GsonBuilder().create()

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson) = Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create(gson))
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        }.build()

        @Provides
        @Singleton
        fun provideApiInterface(retrofit: Retrofit) = retrofit.create(ApiInterface::class.java)
    }

    @Binds
    @Singleton
    abstract fun provideRepository(repository: RepositoryImpl): Repository
}