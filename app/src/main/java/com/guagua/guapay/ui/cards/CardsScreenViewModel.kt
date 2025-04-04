package com.guagua.guapay.ui.cards

import androidx.lifecycle.ViewModel
import com.guagua.guapay.ui.common.delegate.StateDelegator
import com.guagua.guapay.ui.common.delegate.StateDelegatorImpl

class CardsScreenViewModel : ViewModel(),
    StateDelegator<CardsScreenUiState> by StateDelegatorImpl(CardsScreenUiState()) {

    init {
        setState {
            it.copy(cards = emptyList())
        }
    }
}