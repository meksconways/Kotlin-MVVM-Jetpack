package com.mek.haberlerkotlin.ui

import androidx.navigation.NavController

interface ScreenNavigator {

    fun initWithRouter(controller: NavController)

    fun pop(): Boolean

    fun clear()

    fun switchTab(tabId: Int)

    fun clearTabStack(tabId: Int)


}