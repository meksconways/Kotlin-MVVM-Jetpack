package com.mek.haberlerkotlin.newsdetail

import com.squareup.moshi.Json

data class DetailModel(

    @field:Json(name = "Id")
    val name: String,
    @field:Json(name = "CreatedDate")
    val createdDate: String,
    @field:Json(name = "Description")
    val desc: String,
    @field:Json(name = "Editor")
    val editor: String,
    @field:Json(name = "Path")
    val path: String,
    @field:Json(name = "Title")
    val title: String,
    @field:Json(name = "Text")
    val newsText: String,
    @field:Json(name = "Files")
    val files: List<File>
)

data class File(
    @field:Json(name = "FileUrl")
    val fileUrl: String
)