package com.guagua.guapay

import android.app.Application
import com.guagua.data.di.DataModuleProvider
import com.guagua.guapay.domain.di.DomainModuleProvider
import com.guagua.guapay.ui.di.UiModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GuaPayApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GuaPayApplication)
            modules(
                buildList {
                    add(DataModuleProvider.dataModule)
                    add(UiModules.viewModelModules)
                    addAll(DomainModuleProvider.modules)
                }
            )
        }
    }
}