package com.mek.haberlerkotlin.tabfragment.homefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mek.haberlerkotlin.networking.ApiRequester
import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ListNewsVM @Inject constructor(
    private val apiRequester: ApiRequester
) : ViewModel() {


    private lateinit var subscription: Disposable


    private val data = MutableLiveData<List<ListNewsModel>>()
    private val loading = MutableLiveData<Boolean>()

    fun getData(): LiveData<List<ListNewsModel>> = data

    private fun setLoading(v: Boolean) {
        loading.value = v
    }

    fun getLoading(): LiveData<Boolean> = loading

    init {

        fetchData()
    }


    private fun fetchData() {

        subscription = apiRequester.getAllNews()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { setLoading(true) }
            .doOnEvent { _, _ -> setLoading(false) }
            .subscribe(
                { result -> data.value = result },
                { error -> print(error.localizedMessage) }
            )
    }


    override fun onCleared() {
        super.onCleared()
        if (!subscription.isDisposed) {
            subscription.dispose()
        }

    }

}
