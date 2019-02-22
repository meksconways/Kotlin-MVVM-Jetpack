package com.mek.haberlerkotlin.tabfragment.galleryfragment.main

import androidx.recyclerview.widget.DiffUtil
import com.mek.haberlerkotlin.tabfragment.galleryfragment.GalleryNewsModel

class MainGalleryDiffCallback constructor(
    private val oldList: List<GalleryNewsModel>,
    private val newList: List<GalleryNewsModel>
): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }


}