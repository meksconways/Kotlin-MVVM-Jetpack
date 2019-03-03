package com.mek.haberlerkotlin.base

import android.content.Context
import com.mek.haberlerkotlin.home.MainActivity
import com.mek.haberlerkotlin.networking.NetworkModule
import com.mek.haberlerkotlin.newsdetail.NewsDetail
import com.mek.haberlerkotlin.tabfragment.galleryfragment.GalleryFragment
import com.mek.haberlerkotlin.tabfragment.galleryfragment.main.MainGalleryFragment
import com.mek.haberlerkotlin.tabfragment.homefragment.ListNewsFragment
import com.mek.haberlerkotlin.ui.NavigationModule
import com.mek.haberlerkotlin.viewallfragment.AllPathNewsFragment
import com.mek.haberlerkotlin.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class, ViewModelModule::class,
        NavigationModule::class]
)
interface AppComponent {

    fun inject(application: MyApplication)
    fun inject(mainActivity: MainActivity)
    fun inject(listNewsFragment: ListNewsFragment)
    fun inject(mainGalleryFragment: MainGalleryFragment)
    fun inject(galleryFragment: GalleryFragment)
    fun inject(newsDetail: NewsDetail)
    fun inject(allPathNewsFragment: AllPathNewsFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindAppContext(application: Context): Builder

        fun build(): AppComponent

    }

}

fun MyApplication.inject(): AppComponent {
    val component = DaggerAppComponent.builder()
        .bindAppContext(this)
        .build()
    component.inject(this)
    return component
}