package com.guagua.guapay.ui.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guagua.data.card.CardTag
import com.guagua.guapay.domain.card.GetCardsUseCase
import com.guagua.guapay.ui.common.card.CardUiState
import com.guagua.guapay.ui.common.delegate.StateDelegator
import com.guagua.guapay.ui.common.delegate.StateDelegatorImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class CardsScreenViewModel(
    private val getCardsUseCase: GetCardsUseCase,
) : ViewModel(),
    StateDelegator<CardsScreenUiState> by StateDelegatorImpl(CardsScreenUiState()) {

    private val selectedTagFlow = MutableStateFlow(CardTag.OTHER)

    init {
        combine(
            getCardsUseCase.flow()
                .onStart {
                    getCardsUseCase.invoke()
                },
            selectedTagFlow,
        ) { cards, selectedTag ->
            cards.map { card ->
                CardUiState.from(card)
            }.filter { it.tag == selectedTag }
        }.onEach { cards ->
            setState {
                it.copy(
                    cards = cards,
                    selectedTag = selectedTagFlow.value
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onTagSelected(tag: CardTag) {
        selectedTagFlow.value = tag
    }
}