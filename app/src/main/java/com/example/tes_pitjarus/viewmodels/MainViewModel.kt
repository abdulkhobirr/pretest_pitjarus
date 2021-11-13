package com.example.tes_pitjarus.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tes_pitjarus.data.stores.StoresRepository
import com.example.tes_pitjarus.data.stores.local.entity.Stores
import com.example.tes_pitjarus.utils.viewmodel.ResultWrapper
import com.example.tes_pitjarus.utils.viewmodel.addTo
import com.example.tes_pitjarus.utils.viewmodel.genericErrorHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: StoresRepository,
    private val disposable: CompositeDisposable
): ViewModel() {

    val storeData = MutableLiveData<ResultWrapper<List<Stores>>>()

    fun getStores(){
        repository.getStores()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("StoresFromDB", it.toString())
                    if (it.isNotEmpty()) storeData.value = ResultWrapper.success(it)
                    else storeData.value = ResultWrapper.empty()
                }, {
                    genericErrorHandler(it, storeData)
                }).addTo(disposable)
    }

    override fun onCleared() {
        if (!disposable.isDisposed) disposable.dispose()
        super.onCleared()
    }
}