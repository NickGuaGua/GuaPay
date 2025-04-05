package com.guagua.data.card

import com.guagua.data.card.local.CardEntity
import com.guagua.data.card.remote.CardBean

data class Card(
    val id: String,
    val name: String,
    val number: String,
    val expirationDate: String,
    val cvv: String
) {
    internal fun toBean() = CardBean(
        id = id,
        name = name,
        number = number,
        expirationData = expirationDate,
        cvv = cvv
    )

    companion object {
        internal fun from(entity: CardEntity) = with(entity) {
            Card(
                id = id,
                name = name ?: return@with null,
                number = number ?: return@with null,
                expirationDate = expirationData ?: return@with null,
                cvv = cvv ?: return@with null
            )
        }
    }
}