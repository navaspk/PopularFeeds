package com.sample.di.modules.view_models

import androidx.lifecycle.ViewModel
import com.sample.di.key.ViewModelKey
import com.sample.ui.main.activities.MainActivityViewModel
import com.sample.ui.main.fragment.home.PopularItemFragmentViewModel
import com.sample.ui.main.fragment.details.ItemDetailsFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PopularItemFragmentViewModel::class)
    fun bindAllItemFragmentViewModel(viewModel: PopularItemFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ItemDetailsFragmentViewModel::class)
    fun bindItemDetailsFragmentViewModel(viewModel: ItemDetailsFragmentViewModel): ViewModel

}