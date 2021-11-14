package com.example.tes_pitjarus.di

import com.example.tes_pitjarus.utils.pref.PrefManager
import com.example.tes_pitjarus.utils.pref.PreferenceHelper
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val PREFERENCE_NAME = "preference_name"

val preferenceModule = module {

    single(named(PREFERENCE_NAME)) { "Pref Name" }

    single<PrefManager> { PreferenceHelper(get(), get(named(PREFERENCE_NAME))) }
}