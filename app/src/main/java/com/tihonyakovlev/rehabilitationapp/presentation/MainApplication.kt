package com.tihonyakovlev.rehabilitationapp.presentation

import android.app.Application
import com.tihonyakovlev.rehabilitationapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

// RehabilitationApp.kt
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}
