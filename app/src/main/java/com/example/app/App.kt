package com.example.app

import android.app.Application
import com.example.app.di.DaggerAppComponent

class App : Application() {

    val appComponent = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}