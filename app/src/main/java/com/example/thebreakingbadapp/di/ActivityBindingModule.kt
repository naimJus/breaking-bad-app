package com.example.thebreakingbadapp.di


import com.example.thebreakingbadapp.ui.MainActivity
import com.example.thebreakingbadapp.di.scope.PerActivity
import com.example.thebreakingbadapp.ui.detail.DetailModule
import com.example.thebreakingbadapp.ui.list.ListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module
 * ActivityBindingModule is on, in our case that will be [AppComponent]. You never
 * need to tell [AppComponent] that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that [AppComponent] exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the
 * specified modules and be aware of a scope annotation [PerActivity].
 * When Dagger.Android annotation processor runs it will create 2 subcomponents for us.
 */
@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [DetailModule::class, ListModule::class])
    abstract fun contributeMainActivity(): MainActivity
}