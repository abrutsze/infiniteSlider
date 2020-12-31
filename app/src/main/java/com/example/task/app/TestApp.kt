package com.example.task.app

import android.app.Application
import com.example.data.di.apiModule
import com.example.data.di.databaseModule
import com.example.data.di.repositoryModule
import com.example.domian.di.interactorModule
import com.example.task.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TestApp)
            modules(modules)
        }
    }

    val modules = listOf(
        apiModule,
        databaseModule,
        interactorModule,
        repositoryModule,
        viewModelModule
    )
}