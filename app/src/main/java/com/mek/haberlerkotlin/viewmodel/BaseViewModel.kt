package com.mek.haberlerkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.mek.haberlerkotlin.networking.NetworkModule
import com.mek.haberlerkotlin.tabfragment.homefragment.ListNewsVM

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when(this){
            is ListNewsVM -> injector.inject(this)
        }
    }
}