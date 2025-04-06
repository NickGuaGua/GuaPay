package com.guagua.guapay.ui.common.card

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.guagua.data.card.Card
import com.guagua.data.card.CardOrganization
import com.guagua.data.card.CardType
import com.guagua.guapay.R

data class CardUiState(
    val id: String,
    val name: String,
    val number: String,
    val expirationDate: String,
    val cvv: String,
    val owner: String,
    val type: CardType,
    val organization: CardOrganization,
) {
    val last4Digits: String = number.takeLast(4)

    companion object {
        fun from(card: Card): CardUiState {
            return CardUiState(
                id = card.id,
                name = card.name,
                number = card.number,
                expirationDate = card.expirationDate,
                cvv = card.cvv,
                owner = card.owner,
                type = card.type,
                organization = card.organization,
            )
        }
    }
}

@StringRes
fun CardType.text(): Int = when (this) {
    CardType.PHYSICAL -> R.string.card_type_physical
    CardType.VIRTUAL -> R.string.card_type_virtual
}

@DrawableRes
fun CardOrganization.icon(): Int = when (this) {
    CardOrganization.VISA -> R.drawable.ic_visa
    else -> -1
//    CardOrganization.MASTERCARD -> R.drawable.ic_mastercard
//    CardOrganization.AMERICAN_EXPRESS -> R.drawable.ic_amex
//    CardOrganization.DISCOVER -> R.drawable.ic_discover
//    CardOrganization.DINERS_CLUB -> R.drawable.ic_diners_club
//    CardOrganization.JCB -> R.drawable.ic_jcb
//    CardOrganization.UNION_PAY -> R.drawable.ic_union_pay
//    CardOrganization.UNKNOWN -> R.drawable.ic_unknown
}