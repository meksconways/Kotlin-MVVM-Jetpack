package com.mek.haberlerkotlin.gallerydetail.galleryslider

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mek.haberlerkotlin.tabfragment.galleryfragment.Files

class GallerySliderVM : ViewModel() {

    private val data = MutableLiveData<List<Files>>()
    private val position = MutableLiveData(0)

    fun getPosition(): LiveData<Int> = position
    fun setPosition(pos: Int){
        position.value = pos
    }

    fun getData(): LiveData<List<Files>> = data

    fun setData(data: List<Files>) {
        this.data.value = data
    }


}
