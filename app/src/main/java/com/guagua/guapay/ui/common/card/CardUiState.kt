package com.guagua.guapay.ui.common.card

import com.guagua.data.card.Card

data class CardUiState(
    val id: String,
    val name: String,
    val number: String,
    val expirationDate: String,
    val cvv: String,
) {
    companion object {
        fun from(card: Card): CardUiState {
            return CardUiState(
                id = card.id,
                name = card.name,
                number = card.number,
                expirationDate = card.expirationDate,
                cvv = card.cvv
            )
        }
    }
}