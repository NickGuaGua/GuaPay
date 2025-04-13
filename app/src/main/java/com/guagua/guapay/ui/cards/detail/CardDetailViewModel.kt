package com.guagua.guapay.ui.cards.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guagua.guapay.domain.card.GetCardsUseCase
import com.guagua.guapay.ui.common.card.CardUiState
import com.guagua.guapay.ui.common.delegate.StateDelegator
import com.guagua.guapay.ui.common.delegate.StateDelegatorImpl
import com.guagua.guapay.ui.navigation.NavParam
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CardDetailViewModel(
    savedStateHandle: SavedStateHandle,
    id: String?,
    getCardsUseCase: GetCardsUseCase
) : ViewModel(),
    StateDelegator<CardDetailScreenUiState> by StateDelegatorImpl(CardDetailScreenUiState()) {
    private val cardId =
        id ?: savedStateHandle.get<String>(NavParam.CardId) ?: error("Card ID is required")

    init {
        getCardsUseCase.flow(cardId)
            .onEach { card ->
                setState {
                    it.copy(cardUiState = card?.let { CardUiState.from(card) })
                }
            }.launchIn(viewModelScope)
    }
}