package com.guagua.data.card.remote

import com.guagua.data.card.CardTag
import kotlinx.coroutines.delay
import java.util.UUID

class CardRemoteDataSource {

    internal suspend fun getCards(): List<CardBean> {
        delay(1000) // Simulate network delay
        return emptyList()
        return (1..100).map {
            CardBean(
                id = it.toString(),
                name = "Card Holder $it",
                number = "1234 5678 9012 1234",
                expirationDate = "${(it % 12 + 1).toString().padStart(2, '0')}/${
                    (it % 30 + 1).toString().padStart(2, '0')
                }",
                cvv = "123",
                owner = "Owner $it",
                tag = CardTag.entries.random().name
            )
        }
    }

    internal suspend fun addCard(cardBean: CardBean): CardBean? {
        delay(1000)
        return cardBean.copy(id = UUID.randomUUID().toString())
    }
}