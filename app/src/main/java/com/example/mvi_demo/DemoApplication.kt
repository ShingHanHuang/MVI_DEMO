package com.example.mvi_demo

import android.app.Application
import com.example.mvi_demo.koin.adapterModule
import com.example.mvi_demo.koin.networkModule
import com.example.mvi_demo.koin.repositoryModule
import com.example.mvi_demo.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DemoApplication)
            modules(listOf(viewModelModule, repositoryModule, networkModule, adapterModule))
        }
    }
}