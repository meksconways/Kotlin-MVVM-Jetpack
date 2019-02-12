package com.mek.haberlerkotlin.base

import android.content.Context
import com.mek.haberlerkotlin.home.MainActivity
import com.mek.haberlerkotlin.networking.NetworkModule
import com.mek.haberlerkotlin.tabfragment.homefragment.ListNewsFragment
import com.mek.haberlerkotlin.viewmodel.BaseViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [NetworkModule::class])
interface AppComponent {

    fun inject(application: MyApplication)
    fun inject(application: MainActivity)
    fun inject(listNewsFragment: ListNewsFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindAppContext(application: Context) : Builder

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