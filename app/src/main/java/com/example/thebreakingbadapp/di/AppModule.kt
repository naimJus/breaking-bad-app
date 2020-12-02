package com.example.thebreakingbadapp.di

import com.example.thebreakingbadapp.application.MainApplication
import dagger.Module
import dagger.Provides

/**
 * Defines all the classes that need to be provided in the scope of the app, like SharedPreferences,
 * navigators or others.
 */
@Module
class AppModule(private val application: MainApplication) {

    @Provides
    fun provideApplication(): MainApplication {
        return application
    }

}