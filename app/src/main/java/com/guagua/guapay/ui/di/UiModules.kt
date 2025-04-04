package com.guagua.guapay.ui.di

import com.guagua.guapay.ui.cards.CardsScreenViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object UiModules {
    val viewModelModules = module {
        singleOf(::CardsScreenViewModel)
    }
}