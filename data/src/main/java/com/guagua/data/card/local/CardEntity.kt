package com.guagua.data.card.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.guagua.data.card.remote.CardBean

@Entity
internal data class CardEntity(
    @PrimaryKey val id: String,
    val name: String? = null,
    val number: String? = null,
    val expirationData: String? = null,
    val cvv: String? = null
) {
    companion object {
        fun from(bean: CardBean) = with(bean) {
            CardEntity(
                id = id ?: return@with null,
                name = name,
                number = number,
                expirationData = expirationData,
                cvv = cvv
            )
        }
    }
}