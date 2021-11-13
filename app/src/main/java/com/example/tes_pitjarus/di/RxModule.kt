package com.example.tes_pitjarus.di

import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module

val rxModule = module {
    factory { CompositeDisposable() }
}