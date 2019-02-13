package com.mek.haberlerkotlin.networking

import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {


//    @GET("articles?\$select=Title,Files,Id&\$expand=Files")
//    fun getAllNews(@Header("apikey") apikey: String): Single<List<ListNewsModel>>


    @GET("articles?\$select=Title,Files,Id&\$expand=Files")
    fun getAllNews(@Header("apikey") apikey: String): Call<List<ListNewsModel>>



}