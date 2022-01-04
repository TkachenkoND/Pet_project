package com.example.pet_project.cleancode.app

import android.app.Application
import com.example.pet_project.cleancode.di.appModule
import com.example.pet_project.cleancode.di.dataModule
import com.example.pet_project.cleancode.di.domainModule
import com.example.pet_project.cleancode.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(
                networkModule,
                dataModule,
                appModule,
                domainModule,
            ))
        }
    }
}