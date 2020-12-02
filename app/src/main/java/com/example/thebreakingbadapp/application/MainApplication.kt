package com.example.thebreakingbadapp.application

import com.example.thebreakingbadapp.di.AppModule
import com.example.thebreakingbadapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(AppModule(this))
    }
}