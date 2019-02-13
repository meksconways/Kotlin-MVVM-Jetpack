package com.mek.haberlerkotlin.tabfragment.homefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.navOptions
import com.mek.haberlerkotlin.networking.ApiService
import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel
import com.mek.haberlerkotlin.utils.API_KEY
import com.mek.haberlerkotlin.viewmodel.BaseViewModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListNewsVM : BaseViewModel() {


    private lateinit var subscription: Disposable

    @Inject lateinit var apiService: ApiService

    private val data = MutableLiveData<List<ListNewsModel>>()
    private val loading = MutableLiveData<Boolean>()

    fun getData() : LiveData<List<ListNewsModel>> = data
    fun setLoading(l: Boolean) {
        loading.value = l
    }
    fun getLoading() : LiveData<Boolean> = loading

    init {
        fetchData()
    }

    private fun fetchData(){
        subscription = apiService
            .getAllNews(API_KEY)
           // .map { data.value }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loading.value = true }
            .doOnEvent { _, _ -> loading.value = false }
            .subscribe { result -> data.value = result }

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}
