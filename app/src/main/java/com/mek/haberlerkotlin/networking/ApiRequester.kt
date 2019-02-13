package com.mek.haberlerkotlin.networking

import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel
import com.mek.haberlerkotlin.utils.API_KEY
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApiRequester @Inject constructor(private val apiService: ApiService) {


//    fun getAllNews(): Single<List<ListNewsModel>> {
//        return apiService.getAllNews(API_KEY)
//            .map { news: List<ListNewsModel> -> news }
//            .subscribeOn(Schedulers.io())
//    }

}