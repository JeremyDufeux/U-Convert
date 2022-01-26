package com.jeremydufeux.u_convert

import android.app.Application
import timber.log.Timber

class UConvertApp: Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}