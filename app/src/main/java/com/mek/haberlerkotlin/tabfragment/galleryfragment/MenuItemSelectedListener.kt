package com.mek.haberlerkotlin.tabfragment.galleryfragment

interface MenuItemSelectedListener {

    fun setItemSelected(
        position: Int,
        galleryMenuAdapter: GalleryMenuAdapter
    )

}