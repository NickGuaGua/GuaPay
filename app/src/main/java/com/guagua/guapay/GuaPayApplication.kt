package com.guagua.guapay

import android.app.Application
import com.guagua.data.di.DataModuleProvider
import com.guagua.guapay.ui.di.UiModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GuaPayApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GuaPayApplication)
            modules(
                listOf(
                    UiModules.viewModelModules,
                    DataModuleProvider.dataModule,
                )
            )
        }
    }
}