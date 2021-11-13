package com.example.tes_pitjarus.di

import com.example.tes_pitjarus.data.stores.StoresDataStore
import com.example.tes_pitjarus.data.stores.StoresRepository
import com.example.tes_pitjarus.utils.database.AppDatabase
import com.example.tes_pitjarus.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val storesModule = module {
    single {
        AppDatabase.getDatabase(get()).storesDao()
    }

    single<StoresRepository> { StoresDataStore(get()) }

    viewModel { MainViewModel(get(), get()) }
}