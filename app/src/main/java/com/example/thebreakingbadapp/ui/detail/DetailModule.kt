package com.example.thebreakingbadapp.ui.detail

import androidx.lifecycle.ViewModel
import com.example.thebreakingbadapp.di.ViewModelKey
import com.example.thebreakingbadapp.di.scope.PerFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DetailModule {
    @PerFragment
    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment


    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel
}