package com.guagua.guapay.ui.di

import com.guagua.guapay.ui.cards.CardsScreenViewModel
import com.guagua.guapay.ui.cards.add.AddCardViewModel
import com.guagua.guapay.ui.cards.detail.CardDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object UiModules {
    val viewModelModules = module {
        viewModelOf(::CardsScreenViewModel)
        viewModel { (cardId: String?) -> CardDetailViewModel(get(), cardId, get()) }
        viewModelOf(::AddCardViewModel)
    }
}