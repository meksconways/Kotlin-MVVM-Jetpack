package com.mek.haberlerkotlin.tabfragment.homefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mek.haberlerkotlin.networking.ApiService
import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel
import com.mek.haberlerkotlin.utils.API_KEY
import com.mek.haberlerkotlin.viewmodel.BaseViewModel
import io.reactivex.disposables.Disposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ListNewsVM  : BaseViewModel() {


    private lateinit var subscription: Disposable

    @Inject
    lateinit var apiService:ApiService

    private val data = MutableLiveData<List<ListNewsModel>>()
    private val loading = MutableLiveData<Boolean>()

    fun getData(): LiveData<List<ListNewsModel>> = data


    fun getLoading(): LiveData<Boolean> = loading

    init {
        fetchData()
    }

    private lateinit var callNews: Call<List<ListNewsModel>>

    private fun fetchData() {
        loading.value = true
        callNews = apiService.getAllNews(API_KEY)
        callNews.enqueue(object : Callback<List<ListNewsModel>> {
            override fun onResponse(call: Call<List<ListNewsModel>>, response: Response<List<ListNewsModel>>) {
                if (response.code() in 200..399){
                    loading.value = false
                    data.value = response.body()
                    try {
                        android.util.Log.d("***DATA",response.body().toString())
                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<List<ListNewsModel>>, t: Throwable) {

            }
        })




//        apiRequester.getAllNews()
//            .doOnSubscribe { loading.value = true }
//            .doOnEvent { _, _ -> loading.value = false }
//            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}
