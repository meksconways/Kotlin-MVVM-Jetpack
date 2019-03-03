package com.mek.haberlerkotlin.tabfragment.galleryfragment.main

import com.mek.haberlerkotlin.tabfragment.galleryfragment.GalleryNewsModel
import java.text.FieldPosition

interface GalleryNewsSelectedListener {

    fun setSelectedNews(model: GalleryNewsModel)

    fun setSelectedImage(position: Int)

}