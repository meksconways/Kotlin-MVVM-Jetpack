package com.mek.haberlerkotlin.viewmodel

import com.mek.haberlerkotlin.networking.NetworkModule
import com.mek.haberlerkotlin.tabfragment.homefragment.ListNewsVM
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component (modules = [NetworkModule::class])
interface ViewModelInjector {



    fun inject(listNewsVM: ListNewsVM)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }

}