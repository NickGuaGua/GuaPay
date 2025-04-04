package com.guagua.data.card.remote

import kotlinx.coroutines.delay

class CardRemoteDataSource {

    internal suspend fun getCards(): List<CardBean> {
        delay(1000) // Simulate network delay
        return listOf(
            CardBean("1", "John Doe", "1234 5678 9012 3456", "12/23", "123"),
            CardBean("2", "Jane Smith", "9876 5432 1098 7654", "11/24", "456")
        )
    }

    internal suspend fun addCard(cardBean: CardBean): CardBean? {
        delay(1000)
        return cardBean
    }
}