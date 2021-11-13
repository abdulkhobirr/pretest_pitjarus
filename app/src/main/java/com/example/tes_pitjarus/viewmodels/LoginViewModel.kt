package com.example.tes_pitjarus.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tes_pitjarus.data.login.LoginRepository
import com.example.tes_pitjarus.data.model.PostLoginBody
import com.example.tes_pitjarus.data.model.PostLoginResponse
import com.example.tes_pitjarus.data.stores.StoresRepository
import com.example.tes_pitjarus.utils.viewmodel.ResultWrapper
import com.example.tes_pitjarus.utils.viewmodel.addTo
import com.example.tes_pitjarus.utils.viewmodel.genericErrorHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel(
        private val repository: LoginRepository,
        private val db: StoresRepository,
        private val disposable: CompositeDisposable
) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    val loginData = MutableLiveData<ResultWrapper<PostLoginResponse>>()

    fun login(username: String, password: String){
        repository.postLogin(PostLoginBody(username, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("StoresFromDB", it.toString())
                    if (it.storesData == null) {
                        loginData.value = ResultWrapper.failure("Error", "Username / Password Salah")
                    } else {
                        insertStoresData(it)
                    }
                }, {
                    genericErrorHandler(it, loginData)
                }).addTo(disposable)
    }

    private fun insertStoresData(response: PostLoginResponse) {
        launch {
            val data = withContext(Dispatchers.IO) {
                db.addStores(response.toStores())
            }
            loginData.value = ResultWrapper.success(response)
        }
    }

    override fun onCleared() {
        if (!disposable.isDisposed) disposable.dispose()
        super.onCleared()
    }
}