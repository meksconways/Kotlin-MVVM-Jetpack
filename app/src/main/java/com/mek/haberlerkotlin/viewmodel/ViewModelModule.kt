package com.mek.haberlerkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.mek.haberlerkotlin.newsdetail.NewsDetailVM
import com.mek.haberlerkotlin.tabfragment.columnfragment.columndetail.ColumnDetailViewModel
import com.mek.haberlerkotlin.tabfragment.columnfragment.main.ColumnVM
import com.mek.haberlerkotlin.tabfragment.galleryfragment.GalleryVM
import com.mek.haberlerkotlin.tabfragment.galleryfragment.main.MainGalleryVM
import com.mek.haberlerkotlin.tabfragment.homefragment.ListNewsVM
import com.mek.haberlerkotlin.viewallfragment.AllPathNewsVM
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

    @Binds
    @IntoMap
    @ViewModelKey(GalleryVM::class)
    abstract fun bindGalleryVM(viewModel: GalleryVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailVM::class)
    abstract fun bindNewsDetailVM(viewModel: NewsDetailVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AllPathNewsVM::class)
    abstract fun bindAllPathNewsVM(viewModel: AllPathNewsVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ColumnVM::class)
    abstract fun bindColumnVM(viewmodel: ColumnVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ColumnDetailViewModel::class)
    abstract fun bindColumnDetailViewModel(viewmodel: ColumnDetailViewModel): ViewModel

}