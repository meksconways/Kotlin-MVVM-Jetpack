package com.mek.haberlerkotlin.networking

import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {



    @GET("/posts")
    fun getPosts(): Observable<List<Post>>


}