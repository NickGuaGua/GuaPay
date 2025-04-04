package com.guagua.data.card

import com.guagua.data.card.local.CardEntity
import com.guagua.data.card.remote.CardBean

data class Card(
    val id: String,
    val name: String,
    val number: String,
    val expirationData: String,
    val cvv: String
) {
    internal fun toBean() = CardBean(
        id = id,
        name = name,
        number = number,
        expirationData = expirationData,
        cvv = cvv
    )

    companion object {
        internal fun from(entity: CardEntity) = with(entity) {
            Card(
                id = id,
                name = name ?: return@with null,
                number = number ?: return@with null,
                expirationData = expirationData ?: return@with null,
                cvv = cvv ?: return@with null
            )
        }
    }
}