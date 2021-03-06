package com.mek.haberlerkotlin.networking

import com.mek.haberlerkotlin.newsdetail.DetailModel
import com.mek.haberlerkotlin.tabfragment.columnfragment.columndetail.ColumnDetailModel
import com.mek.haberlerkotlin.tabfragment.columnfragment.main.ColumnModel
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

    fun getArticleNewsDetail(newsID: String): Single<DetailModel> =
        apiService.getArticleNewsDetail(API_KEY, newsID).subscribeOn(Schedulers.io())

    fun getPathNews(path: String): Single<List<ListNewsModel>> {
        return apiService.getPathNews(API_KEY, "Path eq '$path'")
            .subscribeOn(Schedulers.io())
    }

    fun getAllGalleryNews(): Single<List<GalleryNewsModel>> {
        return apiService.getAllGalleryNews(API_KEY)
            .subscribeOn(Schedulers.io())
    }

    fun getColumns(): Single<List<ColumnModel>> = apiService.getColumns(API_KEY).subscribeOn(Schedulers.io())

    fun getColumnDetail(columnId: String): Single<ColumnDetailModel> =
            apiService.getColumnDetail(API_KEY,columnId).subscribeOn(Schedulers.io())

    fun getPathGalleryNews(path: String): Single<List<GalleryNewsModel>> {
        return apiService.getPathGalleryNews(API_KEY, "Path eq '$path'")
            .subscribeOn(Schedulers.io())
    }

}