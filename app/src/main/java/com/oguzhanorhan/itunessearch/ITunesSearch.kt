package com.oguzhanorhan.itunessearch

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ITunesSearch: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@ITunesSearch) }
    }
}