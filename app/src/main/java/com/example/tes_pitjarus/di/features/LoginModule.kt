package com.example.tes_pitjarus.di.features

import com.example.tes_pitjarus.BuildConfig.BASE_URL
import com.example.tes_pitjarus.data.login.LoginDataStore
import com.example.tes_pitjarus.data.login.LoginRepository
import com.example.tes_pitjarus.data.login.remote.LoginApi
import com.example.tes_pitjarus.data.login.remote.LoginApiClient
import com.example.tes_pitjarus.utils.data.ApiService
import com.example.tes_pitjarus.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val loginModule = module {
    single {
        ApiService.createReactiveService(
                LoginApiClient::class.java,
                get(),
                get(named(BASE_URL))
        )
    }

    single { LoginApi(get()) }

    single<LoginRepository> { LoginDataStore(get()) }

    viewModel { LoginViewModel(get(), get(), get()) }
}