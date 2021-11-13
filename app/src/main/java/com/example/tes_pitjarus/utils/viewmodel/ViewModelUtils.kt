package com.example.tes_pitjarus.utils.viewmodel

import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonSyntaxException
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

fun Disposable.addTo(disposable: CompositeDisposable) {
    disposable.add(this)
}

fun <T> genericErrorHandler(e: Throwable, result: MutableLiveData<ResultWrapper<T>>) {
    when (e) {
        is SocketTimeoutException -> result.value =
            ResultWrapper.fail(e, "Koneksi Gagal", "Gagal menghubungkan ke server, silahkan coba lagi.")
        is IOException -> result.value =
            ResultWrapper.fail(e, "Koneksi Gagal", "Gagal menghubungkan ke server, silahkan coba lagi.")
        is JsonSyntaxException -> result.value = ResultWrapper.fail(
            e,
            "Terjadi kesalahan pada data",
            "Data error atau tidak ditemukan."
        )
        is HttpException -> result.value =
            when {
                HttpException(e.response()!!).response()?.code() == 500 -> ResultWrapper.fail(
                    e,
                    title = "Terjadi kesalahan pada server",
                    message = "Silahkan coba lagi."
                )
                HttpException(e.response()!!).response()?.code() == 404 -> ResultWrapper.fail(
                    e,
                    "Data tidak ditemukan",
                    e.response()?.errorBody().toString()
                )
                HttpException(e.response()!!).response()?.code() == 401 -> ResultWrapper.fail(
                    e,
                    "Unauthorized", "")

                HttpException(e.response()!!).response()?.code() == 403 -> ResultWrapper.fail(
                    e,
                    "Forbidden", "")
                else -> ResultWrapper.fail(
                    e,
                    title = "Terjadi kesalahan",
                    message = "Error tidak diketahui"
                )
            }
        is NoSuchElementException -> result.value = ResultWrapper.empty()
        else -> result.value =
            ResultWrapper.fail(e, title = "Terjadi kesalahan", message = "Error tidak diketahui")
    }
}