package com.mek.haberlerkotlin.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityVM : ViewModel() {

    private val title = MutableLiveData<String>("Haberler Kotlin")
    private val hasBackButton = MutableLiveData<Boolean>(false)
    private val bottomBarBehavior = MutableLiveData<Boolean>(false)

    fun getBottomBarBehavior(): LiveData<Boolean> = bottomBarBehavior

    fun setBottomBarBehavior(v: Boolean){
        bottomBarBehavior.value = v
    }

    fun getTitle(): LiveData<String> {
        return title
    }

    fun getHasBackButton(): LiveData<Boolean> = hasBackButton

    fun setTitle(tit: String) {
        title.value = tit
    }

    fun setHasBackButton(vals: Boolean) {
        hasBackButton.value = vals
    }

}