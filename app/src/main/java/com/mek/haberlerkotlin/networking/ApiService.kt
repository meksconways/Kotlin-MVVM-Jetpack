package com.mek.haberlerkotlin.networking

import com.mek.haberlerkotlin.newsdetail.DetailModel
import com.mek.haberlerkotlin.tabfragment.columnfragment.ColumnModel
import com.mek.haberlerkotlin.tabfragment.galleryfragment.GalleryNewsModel
import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("articles?\$select=Title,Files,Path,Id,CreatedDate&\$expand=Files")
    fun getAllNews(@Header("apikey") apikey: String): Single<List<ListNewsModel>>


    @GET("articles?\$select=Title,Files,Id,Path,CreatedDate&\$expand=Files&\$top=10")
    fun getPathNews(
        @Header("apikey") apikey: String,
        @Query("\$filter") filter: String
    ): Single<List<ListNewsModel>>

    @GET("newsphotogalleries?\$top=14")
    fun getAllGalleryNews(@Header("apikey") apikey: String): Single<List<GalleryNewsModel>>

    @GET("newsphotogalleries?\$top=14")
    fun getPathGalleryNews(
        @Header("apikey") apikey: String,
        @Query("\$filter") filter: String
    ): Single<List<GalleryNewsModel>>

    @GET("articles/{id}")
    fun getArticleNewsDetail(@Header("apikey") apikey: String,
                             @Path("id") id: String) : Single<DetailModel>

    @GET("columns?\ntop=20")
    fun getColumns(@Header("apikey") apikey: String): Single<List<ColumnModel>>

}