package com.guagua.guapay.domain.card

import com.guagua.data.card.Card
import com.guagua.data.card.CardRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AddCardUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val cardRepository: CardRepository,
) {
    suspend operator fun invoke(card: Card) = withContext(dispatcher) {
        cardRepository.addCard(card)
    }
}