package com.guagua.guapay.ui.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guagua.guapay.domain.card.GetCardsUseCase
import com.guagua.guapay.ui.common.card.CardUiState
import com.guagua.guapay.ui.common.delegate.StateDelegator
import com.guagua.guapay.ui.common.delegate.StateDelegatorImpl
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class CardsScreenViewModel(
    private val getCardsUseCase: GetCardsUseCase,
) : ViewModel(),
    StateDelegator<CardsScreenUiState> by StateDelegatorImpl(CardsScreenUiState()) {

    init {
        getCardsUseCase.flow()
            .onStart {
                getCardsUseCase.invoke()
            }
            .onEach { cards ->
                setState {
                    it.copy(cards = cards.map { card ->
                        CardUiState.from(card)
                    })
                }
            }
            .launchIn(viewModelScope)
    }
}