package com.mek.haberlerkotlin.tabfragment.galleryfragment.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mek.haberlerkotlin.networking.ApiRequester
import com.mek.haberlerkotlin.tabfragment.galleryfragment.GalleryNewsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainGalleryVM @Inject constructor(private val apiRequester: ApiRequester): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val data = MutableLiveData<List<GalleryNewsModel>>()
    private val loading = MutableLiveData<Int>(View.VISIBLE)

    fun getLoading(): LiveData<Int> = loading
    fun getData(): LiveData<List<GalleryNewsModel>> = data

    init {
        fetchGalleryNews()
    }

    private fun fetchGalleryNews() {
        compositeDisposable.add(
            apiRequester.getAllGalleryNews()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEvent { dt1, dt2 -> loading.value = View.GONE }
                .doOnSubscribe { loading.value = View.VISIBLE }
                .subscribe(
                    { result -> data.value = result},
                    { error -> print(error.localizedMessage)}
                )
        )
    }

    override fun onCleared() {
        if (compositeDisposable.size() > 0){
            compositeDisposable.clear()
        }
    }

}
