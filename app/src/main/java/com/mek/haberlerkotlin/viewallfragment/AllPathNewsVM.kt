package com.mek.haberlerkotlin.viewallfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mek.haberlerkotlin.networking.ApiRequester
import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AllPathNewsVM @Inject constructor(private val apiRequester: ApiRequester) : ViewModel() {

    private val data = MutableLiveData<List<ListNewsModel>>()
    private val loading = MutableLiveData<Boolean>(true)
    private val compositeDisposable = CompositeDisposable()
    private val pathData = MutableLiveData<String>()

    fun getLoading(): LiveData<Boolean> = loading
    fun getData(): LiveData<List<ListNewsModel>> = data
    fun getPathData(): LiveData<String> = pathData

    fun setPathData(v: String){
        pathData.value = v
    }

    var isFirst:Boolean = true

    fun fetchData() {
        if (isFirst){
            compositeDisposable.add(
                apiRequester.getPathNews(pathData.value!!)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { loading.value = true }
                    .doOnEvent { _, _ -> loading.value = false }
                    .subscribe(
                        { result -> data.value = result },
                        { error -> print(error.localizedMessage) }

                    )
            )
        }

        isFirst = false
    }

    override fun onCleared() {
        if (compositeDisposable.size() > 0){
            compositeDisposable.dispose()
        }
    }


}
