package com.friendsflix.application

import android.app.Application
import com.friendsflix.di.dataModule
import com.friendsflix.di.networkModule
import com.friendsflix.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)

            modules(
                listOf(
                    dataModule,
                    presentationModule,
                    networkModule
                )
            )
        }
    }
}