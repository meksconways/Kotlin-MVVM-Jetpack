package com.mek.haberlerkotlin.networking

import com.mek.haberlerkotlin.tabfragment.galleryfragment.GalleryNewsModel
import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel
import com.mek.haberlerkotlin.utils.API_KEY
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApiRequester @Inject constructor(private val apiService: ApiService) {


    fun getAllNews(): Single<List<ListNewsModel>> {
        return apiService.getAllNews(API_KEY)
            .subscribeOn(Schedulers.io())
    }

    fun getPathNews(path: String): Single<List<ListNewsModel>> {
        return apiService.getPathNews(API_KEY, "Path eq '$path'")
            .subscribeOn(Schedulers.io())
    }

    fun getAllGalleryNews(): Single<List<GalleryNewsModel>> {
        return apiService.getAllGalleryNews(API_KEY)
            .subscribeOn(Schedulers.io())
    }

    fun getPathGalleryNews(path: String): Single<List<GalleryNewsModel>> {
        return apiService.getPathGalleryNews(API_KEY, "Path eq '$path'")
            .subscribeOn(Schedulers.io())
    }

}