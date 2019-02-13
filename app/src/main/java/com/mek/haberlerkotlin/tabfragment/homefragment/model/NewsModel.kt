package com.mek.haberlerkotlin.tabfragment.homefragment.model

import com.squareup.moshi.Json

data class ListNewsModel(

    @Json(name = "Id")
    val id: String,
    @Json(name = "Title")
    val title: String,
    @Json(name = "Files")
    val files: List<Files>
)

data class Files(

    @Json(name = "FileUrl")
    val fileUrl: String

)