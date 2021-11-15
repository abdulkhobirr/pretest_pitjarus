package com.example.tes_pitjarus.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tes_pitjarus.data.login.LoginRepository
import com.example.tes_pitjarus.data.model.PostLoginBody
import com.example.tes_pitjarus.data.model.PostLoginResponse
import com.example.tes_pitjarus.data.stores.StoresRepository
import com.example.tes_pitjarus.utils.viewmodel.ResultWrapper
import com.example.tes_pitjarus.utils.viewmodel.addTo
import com.example.tes_pitjarus.utils.viewmodel.genericErrorHandler
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
        private val repository: LoginRepository,
        private val db: StoresRepository,
        private val disposable: CompositeDisposable
): ViewModel() {

    val loginData = MutableLiveData<ResultWrapper<PostLoginResponse>>()

    fun login(username: String, password: String){
        loginData.value = ResultWrapper.loading()
        repository.postLogin(PostLoginBody(username, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
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
        Completable.fromAction {db.addStores(response.toStores())}
            .subscribeOn(Schedulers.io())
            .subscribe{
                loginData.postValue(ResultWrapper.success(response))
            }
            .addTo(disposable)
        }

    override fun onCleared() {
        if (!disposable.isDisposed) disposable.dispose()
        super.onCleared()
    }
}