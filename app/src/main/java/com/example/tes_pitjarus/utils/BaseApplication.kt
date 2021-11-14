package com.example.tes_pitjarus.utils

import android.app.Application
import android.content.Context
import com.example.tes_pitjarus.di.apiModule
import com.example.tes_pitjarus.di.features.loginModule
import com.example.tes_pitjarus.di.preferenceModule
import com.example.tes_pitjarus.di.rxModule
import com.example.tes_pitjarus.di.storesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class BaseApplication: Application() {
    companion object {
        var context: Context? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()

        context = this

        startKoin {
            androidContext(this@BaseApplication)
            modules(getDefinedModules())
        }
    }

    private fun getDefinedModules(): List<Module> {
        return listOf(
                apiModule,
                rxModule,
                storesModule,
                loginModule,
                preferenceModule
        )
    }
}