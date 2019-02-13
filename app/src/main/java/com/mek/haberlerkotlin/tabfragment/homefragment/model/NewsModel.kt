package com.mek.haberlerkotlin.tabfragment.homefragment.model

import com.squareup.moshi.Json

data class ListNewsModel(

    @field:Json(name = "Id")
    val id: String,
    @field:Json(name = "Title")
    val title: String,
    @field:Json(name = "Files")
    val files: List<Files>
)

data class Files(

    @field:Json(name = "FileUrl")
    val fileUrl: String

)