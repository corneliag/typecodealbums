package com.cjuca.typecodealbums

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@Application)
            // declare modules
            modules(modules = listOf(daoModule, appModule, appViewModel))
        }
    }
}