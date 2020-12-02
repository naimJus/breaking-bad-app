package com.example.thebreakingbadapp.ui.list

import androidx.lifecycle.ViewModel
import com.example.thebreakingbadapp.di.ViewModelKey
import com.example.thebreakingbadapp.di.scope.PerFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ListModule {
    @PerFragment
    @ContributesAndroidInjector
    abstract fun contributeListFragment(): ListFragment


    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(viewModel: ListViewModel): ViewModel
}