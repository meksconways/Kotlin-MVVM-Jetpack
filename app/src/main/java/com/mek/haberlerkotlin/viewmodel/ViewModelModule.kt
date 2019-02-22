package com.mek.haberlerkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.mek.haberlerkotlin.home.MainActivityVM
import com.mek.haberlerkotlin.tabfragment.galleryfragment.main.MainGalleryVM
import com.mek.haberlerkotlin.tabfragment.homefragment.ListNewsVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListNewsVM::class)
    abstract fun bindListNewsVM(viewModel: ListNewsVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainGalleryVM::class)
    abstract fun bindMainGalleryVM(viewModel: MainGalleryVM): ViewModel



}