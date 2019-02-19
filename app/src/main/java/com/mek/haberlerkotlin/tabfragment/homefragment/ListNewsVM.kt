package com.mek.haberlerkotlin.tabfragment.homefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mek.haberlerkotlin.networking.ApiRequester
import com.mek.haberlerkotlin.tabfragment.homefragment.model.ListNewsModel
import com.mek.haberlerkotlin.utils.DUNYA_PATH
import com.mek.haberlerkotlin.utils.EKONOMI_PATH
import com.mek.haberlerkotlin.utils.GUNDEM_PATH
import com.mek.haberlerkotlin.utils.SPOR_PATH
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ListNewsVM @Inject constructor(
    private val apiRequester: ApiRequester
) : ViewModel() {


    private val compositeDisposable = CompositeDisposable()

    private val data = MutableLiveData<List<ListNewsModel>>()
    private val loading = MutableLiveData<Boolean>()
    private val sportsData = MutableLiveData<List<ListNewsModel>>()
    private val countriesData = MutableLiveData<List<ListNewsModel>>()
    private val journalData = MutableLiveData<List<ListNewsModel>>()
    private val economyData = MutableLiveData<List<ListNewsModel>>()
    private val executionCount = MutableLiveData(0)

    fun getExecutionCount(): LiveData<Int> = executionCount
    fun getData(): LiveData<List<ListNewsModel>> = data
    fun getSportsData(): LiveData<List<ListNewsModel>> = sportsData
    fun getCountryData(): LiveData<List<ListNewsModel>> = countriesData
    fun getJournalData(): LiveData<List<ListNewsModel>> = journalData
    fun getEconomyData(): LiveData<List<ListNewsModel>> = economyData

    private fun setLoading(v: Boolean) {
        loading.value = v
    }

    fun getLoading(): LiveData<Boolean> = loading

    init {

        fetchTopic()
        fetchSports()
        fetchCountry()
        fetchEconomyNews()
        fetchJournalNews()


    }

    private fun fetchEconomyNews() {
        compositeDisposable.add(apiRequester.getPathNews(EKONOMI_PATH)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                economyData.value = result
                executionCount.value = executionCount.value?.plus(1)
            })
    }

    private fun fetchJournalNews() {
        compositeDisposable.add(apiRequester.getPathNews(GUNDEM_PATH)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                journalData.value = result
                executionCount.value = executionCount.value?.plus(1)
            })
    }

    private fun fetchCountry() {
        compositeDisposable.add(
            apiRequester.getPathNews(DUNYA_PATH)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        countriesData.value = result
                        executionCount.value = executionCount.value?.plus(1)
                    },
                    { error -> print(error.message) })
        )
    }

    private fun fetchSports() {
        compositeDisposable.add(apiRequester.getPathNews(SPOR_PATH)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                sportsData.value = result
                executionCount.value = executionCount.value?.plus(1)
            })
    }


    private fun fetchTopic() {
        compositeDisposable.add(apiRequester.getAllNews()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { setLoading(true) }
            .doOnEvent { _, _ -> setLoading(false) }
            .subscribe(
                { result ->
                    data.value = result
                    executionCount.value = executionCount.value?.plus(1)
                },
                { error -> print(error.localizedMessage) }
            ))
    }


    override fun onCleared() {
        super.onCleared()
        if (compositeDisposable.size() > 0) {
            compositeDisposable.clear()
        }


    }

}
