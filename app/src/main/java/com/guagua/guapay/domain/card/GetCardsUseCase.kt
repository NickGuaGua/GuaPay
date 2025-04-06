package com.guagua.guapay.domain.card

import com.guagua.data.card.CardRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetCardsUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val cardRepository: CardRepository
) {
    fun flow() = cardRepository.getCardsFlow()

    fun flow(cardId: String) = cardRepository.getCardFlow(cardId)

    suspend operator fun invoke() = withContext(dispatcher) {
        runCatching {
            cardRepository.getCards()
        }
    }
}