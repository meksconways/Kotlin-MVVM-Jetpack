package com.mek.haberlerkotlin.networking

import com.mek.haberlerkotlin.tabfragment.galleryfragment.GalleryNewsModel
import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {


    @GET("articles?\$select=Title,Files,Path,Id,CreatedDate&\$expand=Files")
    fun getAllNews(@Header("apikey") apikey: String): Single<List<ListNewsModel>>


    @GET("articles?\$select=Title,Files,Id,Path,CreatedDate&\$expand=Files&\$top=10")
    fun getPathNews(
        @Header("apikey") apikey: String,
        @Query("\$filter") filter: String
    ): Single<List<ListNewsModel>>

    @GET("newsphotogalleries?\$top=15")
    fun getAllGalleryNews(@Header("apikey") apikey: String): Single<List<GalleryNewsModel>>

    @GET("newsphotogalleries?\$top=15")
    fun getPathGalleryNews(
        @Header("apikey") apikey: String,
        @Query("\$filter") filter: String
    ): Single<List<GalleryNewsModel>>


}