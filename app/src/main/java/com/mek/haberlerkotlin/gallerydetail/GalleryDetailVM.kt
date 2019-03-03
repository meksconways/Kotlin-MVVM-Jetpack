package com.mek.haberlerkotlin.gallerydetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mek.haberlerkotlin.tabfragment.galleryfragment.GalleryNewsModel
import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel

class GalleryDetailVM : ViewModel() {

    private val data = MutableLiveData<GalleryNewsModel>()

    fun getData(): LiveData<GalleryNewsModel> = data
    fun setData(data: GalleryNewsModel){
        this.data.value = data
    }

}
