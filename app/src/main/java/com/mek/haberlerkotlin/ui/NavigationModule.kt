package com.mek.haberlerkotlin.ui

import dagger.Binds
import dagger.Module

@Module
abstract class NavigationModule{

    @Binds
    abstract fun provideScreenNavigator(defaultScreenNavigator: DefaultScreenNavigator): ScreenNavigator

}