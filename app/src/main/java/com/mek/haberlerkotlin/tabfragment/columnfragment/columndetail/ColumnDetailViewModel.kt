package com.mek.haberlerkotlin.tabfragment.columnfragment.columndetail

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mek.haberlerkotlin.networking.ApiRequester
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ColumnDetailViewModel @Inject constructor(private val apiRequester: ApiRequester) : ViewModel() {


    private val data = MutableLiveData<ColumnDetailModel>()
    private val loading = MutableLiveData<Int>(View.VISIBLE)
    val newsId = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()

    fun setNewsId(id: String) {
        newsId.value = id
    }

    fun getData(): LiveData<ColumnDetailModel> = data
    fun getLoading(): LiveData<Int> = loading

    fun fetchData() {
        compositeDisposable.add(
            apiRequester.getColumnDetail(newsId.value!!)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading.value = View.VISIBLE }
                .doOnEvent { _, _ -> loading.value = View.GONE }
                .subscribe(
                    {
                        data.value = it
                    },
                    {
                        it.printStackTrace()
                    }
                )
        )
    }


    override fun onCleared() {
        super.onCleared()
        if (compositeDisposable.size() > 0){
            compositeDisposable.dispose()
        }

    }

}
