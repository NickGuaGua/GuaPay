package com.guagua.guapay.ui.di

import com.guagua.guapay.ui.cards.CardsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object UiModules {
    val viewModelModules = module {
        viewModelOf(::CardsScreenViewModel)
    }
}