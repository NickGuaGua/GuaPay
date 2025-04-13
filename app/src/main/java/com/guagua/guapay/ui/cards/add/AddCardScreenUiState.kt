package com.guagua.guapay.ui.cards.add

import com.guagua.data.card.CardTag

data class AddCardScreenUiState(
    val cardName: ValidateItem<String> = ValidateItem("") { it.isNotEmpty() },
    val cardOwner: ValidateItem<String> = ValidateItem("") { it.isNotEmpty() },
    val cardNumber: ValidateItem<String> = ValidateItem("") { it.length == 16 },
    val expireMonth: ValidateItem<String> = ValidateItem("") { it.isNotEmpty() },
    val expireYear: ValidateItem<String> = ValidateItem("") { it.isNotEmpty() },
    val cvv: ValidateItem<String> = ValidateItem("") { it.length == 3 },
    val tag: ValidateItem<CardTag> = ValidateItem(CardTag.OTHER) { true },
    val isLoading: Boolean = false
)

data class ValidateItem<T>(
    val value: T,
    val isValid: Boolean? = null,
    val condition: (T) -> Boolean = { true }
) {
    fun getOrThrow() = value.takeIf { isValid == true } ?: error("Value is not valid")

    fun validate() = copy(
        isValid = condition(value)
    )
}

sealed class AddCardScreenEvent {
    data object Success : AddCardScreenEvent()
}