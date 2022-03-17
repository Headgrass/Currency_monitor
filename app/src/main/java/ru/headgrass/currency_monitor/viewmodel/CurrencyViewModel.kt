package ru.headgrass.currency_monitor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.headgrass.currency_monitor.model.Repository
import ru.headgrass.currency_monitor.model.RepositoryImpl

class CurrencyViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repo: Repository = RepositoryImpl()

    fun getData(): LiveData<AppState> = liveDataToObserve

    fun getCurrencyFromLocalStorage(){

        liveDataToObserve.value = AppState.Loading

        Thread {
            Thread.sleep(1000)
            val currency = repo.getCurrencyFromLocalStorage()
            liveDataToObserve.postValue(AppState.Success(currency))
        }.start()
    }
}