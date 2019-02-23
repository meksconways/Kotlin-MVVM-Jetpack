package com.mek.haberlerkotlin.newsdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mek.haberlerkotlin.networking.ApiRequester
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NewsDetailVM @Inject constructor(private val apiRequester: ApiRequester) : ViewModel() {


    private val detailData = MutableLiveData<DetailModel>()
    private val compositeDisposable = CompositeDisposable()
    private val newsId = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>(true)

    fun getLoading(): LiveData<Boolean> = loading

    fun setNewsId(id: String) {
        newsId.value = id
    }

    fun getDetailData(): LiveData<DetailModel> = detailData



    fun fetchDetail() {
        compositeDisposable.add(
            apiRequester.getArticleNewsDetail(newsId.value!!)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading.value = true }
                .doOnEvent { _, _ -> loading.value = false }
                .subscribe(
                    { result -> detailData.value = result },
                    { error -> print(error.localizedMessage) }
                )
        )
    }

    override fun onCleared() {
        if (compositeDisposable.size() > 0) {
            compositeDisposable.clear()
        }
    }


}
