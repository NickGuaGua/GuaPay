package com.guagua.data.card.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.guagua.data.card.remote.CardBean

@Entity
internal data class CardEntity(
    @PrimaryKey val id: String,
    val name: String? = null,
    val number: String? = null,
    val expirationDate: String? = null,
    val cvv: String? = null,
    val owner: String? = null,
) {
    companion object {
        fun from(bean: CardBean) = with(bean) {
            CardEntity(
                id = id ?: return@with null,
                name = name,
                number = number,
                expirationDate = expirationDate,
                cvv = cvv,
                owner = owner,
            )
        }
    }
}