package com.guagua.data.card

import com.guagua.data.card.local.CardEntity
import com.guagua.data.card.local.CardLocalDataSource
import com.guagua.data.card.remote.CardRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CardRepository(
    private val local: CardLocalDataSource,
    private val remote: CardRemoteDataSource,
) {
    suspend fun getCards(): List<Card> {
        return remote.getCards().mapNotNull {
            CardEntity.from(it)
        }.let { entities ->
            local.update(entities)
            entities.mapNotNull { Card.from(it) }
        }
    }

    fun getCardsFlow(): Flow<List<Card>> {
        return local.getCardsFlow().map { entities ->
            entities.mapNotNull { Card.from(it) }
        }
    }

    fun getCardFlow(cardId: String): Flow<Card?> {
        return local.getCardFlow(cardId).map { entity ->
            entity?.let { Card.from(it) }
        }
    }

    suspend fun addCard(card: Card) {
        remote.addCard(card.toBean())?.let {
            CardEntity.from(it)?.let {
                local.upsert(it)
            }
        }
    }
}