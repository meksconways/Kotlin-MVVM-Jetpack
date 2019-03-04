package com.mek.haberlerkotlin.tabfragment.columnfragment.columndetail

import com.mek.haberlerkotlin.tabfragment.galleryfragment.Files
import com.squareup.moshi.Json

data class ColumnDetailModel(
    @field:Json(name = "Id")
    val id: String,
    @field:Json(name = "Fullname")
    val name: String,
    @field:Json(name = "CreatedDate")
    val createdDate: String,
    @field:Json(name = "Description")
    val desc: String,
    @field:Json(name = "Text")
    val newsText: String,
    @field:Json(name = "Title")
    val title: String,
    @field:Json(name = "Files")
    val files: List<Files>
)