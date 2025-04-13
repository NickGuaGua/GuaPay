package com.guagua.guapay.ui.cards

import com.guagua.data.card.CardTag
import com.guagua.guapay.ui.common.card.CardUiState

data class CardsScreenUiState(
    val cards: List<CardUiState>? = null,
    val tags: List<CardTag> = CardTag.entries,
    val selectedTag: CardTag = CardTag.ALL
)

