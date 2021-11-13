package com.example.tes_pitjarus.di

import com.example.tes_pitjarus.BuildConfig
import com.example.tes_pitjarus.BuildConfig.BASE_URL
import com.example.tes_pitjarus.utils.data.OkHttpClientFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module

val apiModule = module {

    single {
        return@single OkHttpClientFactory.create(
            showDebugLog = BuildConfig.DEBUG
        )
    }

    single(named(BASE_URL)) { BASE_URL }
}