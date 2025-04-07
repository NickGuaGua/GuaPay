package com.guagua.guapay.ui.cards.add

data class AddCardScreenUiState(
    val cardName: ValidateItem<String> = ValidateItem(""),
    val cardOwner: ValidateItem<String> = ValidateItem(""),
    val cardNumber: ValidateItem<String> = ValidateItem(""),
    val expireMonth: ValidateItem<String> = ValidateItem(""),
    val expireYear: ValidateItem<String> = ValidateItem(""),
    val cvv: ValidateItem<String> = ValidateItem(""),
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