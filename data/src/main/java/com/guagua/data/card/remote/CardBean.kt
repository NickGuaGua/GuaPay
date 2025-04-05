package com.guagua.data.card.remote

import kotlinx.serialization.Serializable

@Serializable
internal data class CardBean(
    val id: String? = null,
    val name: String? = null,
    val number: String? = null,
    val expirationDate: String? = null,
    val cvv: String? = null
)