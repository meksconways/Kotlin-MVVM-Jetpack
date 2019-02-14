package com.mek.haberlerkotlin.base

import android.app.Application
import android.content.Context


class MyApplication : Application() {


    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = inject()
    }

    companion object {
        @JvmStatic
        fun getAppComponent(context: Context): AppComponent {
            return (context.applicationContext as MyApplication).component
        }
    }


}