package com.mek.haberlerkotlin.tabfragment.homefragment.model

import com.squareup.moshi.Json

data class ListNewsModel(

    @field:Json(name = "Id")
    val id: String,
    @field:Json(name = "Title")
    val title: String,
    @field:Json(name = "Files")
    val files: List<Files>,
    @field:Json(name = "Path")
    val path: String,
    @field:Json(name = "CreatedDate")
    val date: String
)

data class Files(

    @field:Json(name = "FileUrl")
    val fileUrl: String

)