package com.mek.haberlerkotlin.tabfragment.galleryfragment

import android.graphics.drawable.Drawable
import com.squareup.moshi.Json

data class GalleryMenuModel(
    var color: Int,
    var photo: Drawable,
    var name: String,
    var isSelected: Boolean
) {

    class Builder {

        private lateinit var name: String
        private lateinit var photo: Drawable
        private var color = 0
        private var isSelected = false

        fun name(name: String) = apply { this.name = name }
        fun photo(photo: Drawable) = apply { this.photo = photo }
        fun color(color: Int) = apply { this.color = color }
        fun isSelected(isSelected: Boolean) = apply { this.isSelected = isSelected }

        fun build() = GalleryMenuModel(
            color,
            photo,
            name,
            isSelected
        )
    }

}

data class GalleryNewsModel(

    @field:Json(name = "Id")
    val id: String,
    @field:Json(name = "CreatedDate")
    val date: String,
    @field:Json(name = "Title")
    val title: String,
    @field:Json(name = "Path")
    val path: String,
    @field:Json(name = "Files")
    val files: List<Files>

)

data class Files(

    @field:Json(name = "FileUrl")
    val fileUrl: String,
    @field:Json(name = "Description")
    val description: String

)

