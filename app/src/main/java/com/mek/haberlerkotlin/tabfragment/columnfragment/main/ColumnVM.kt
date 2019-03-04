package com.mek.haberlerkotlin.tabfragment.columnfragment.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mek.haberlerkotlin.networking.ApiRequester
import com.mek.haberlerkotlin.tabfragment.columnfragment.main.ColumnModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ColumnVM @Inject constructor(private val apiRequester: ApiRequester) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val data = MutableLiveData<List<ColumnModel>>()
    private val loading = MutableLiveData<Boolean>(true)

    fun getData(): LiveData<List<ColumnModel>> = data
    fun getLoading(): LiveData<Boolean> = loading

    init {
        fetchData()
    }

    private fun fetchData() {
        compositeDisposable.add(
            apiRequester.getColumns()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loading.value = true }
                .doOnEvent { _, _ -> loading.value = false }
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
