package com.mek.haberlerkotlin.tabfragment.galleryfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mek.haberlerkotlin.networking.ApiRequester
import javax.inject.Inject

class GalleryVM @Inject constructor(apiRequester: ApiRequester): ViewModel() {

    private val newsType = MutableLiveData<String>("tümü")



    fun getNewsType(): LiveData<String> = newsType

    fun setNewsType(news: String){
        newsType.value = news
    }





}
