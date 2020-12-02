package com.example.thebreakingbadapp.di

import com.example.thebreakingbadapp.application.MainApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ApiModule::class,
        ViewModelModule::class,
        ActivityBindingModule::class,
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    interface Factory {
        fun create(module: AppModule): AppComponent
    }

}