package com.guagua.data.card

import com.guagua.data.card.local.CardEntity
import com.guagua.data.card.remote.CardBean

data class Card(
    val id: String,
    val name: String,
    val number: String,
    val expirationDate: String,
    val cvv: String,
    val organization: CardOrganization = CardOrganization.VISA,
    val type: CardType = CardType.PHYSICAL,
) {
    internal fun toBean() = CardBean(
        id = id,
        name = name,
        number = number,
        expirationDate = this@Card.expirationDate,
        cvv = cvv
    )

    companion object {
        internal fun from(entity: CardEntity) = with(entity) {
            Card(
                id = id,
                name = name ?: return@with null,
                number = number ?: return@with null,
                expirationDate = expirationDate ?: return@with null,
                cvv = cvv ?: return@with null,
            )
        }
    }
}

enum class CardType {
    PHYSICAL, VIRTUAL;
}

enum class CardOrganization {
    VISA,
    MASTERCARD,
    AMERICAN_EXPRESS,
    DISCOVER,
    DINERS_CLUB,
    JCB,
    UNION_PAY,
    UNKNOWN;

    companion object {
        fun from(value: String) = values().find { it.name == value } ?: UNKNOWN
    }
}