package com.guagua.guapay.ui.cards.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guagua.data.card.Card
import com.guagua.guapay.domain.card.AddCardUseCase
import com.guagua.guapay.ui.common.delegate.EventDelegator
import com.guagua.guapay.ui.common.delegate.EventDelegatorImpl
import com.guagua.guapay.ui.common.delegate.StateDelegator
import com.guagua.guapay.ui.common.delegate.StateDelegatorImpl
import kotlinx.coroutines.launch

class AddCardViewModel(
    private val addCardUseCase: AddCardUseCase
) : ViewModel(),
    StateDelegator<AddCardScreenUiState> by StateDelegatorImpl(AddCardScreenUiState()),
    EventDelegator<AddCardScreenEvent> by EventDelegatorImpl() {

    fun addCard() {
        setState { it.copy(isLoading = true) }
        viewModelScope.launch {
            runCatching {
                Card(
                    id = "",
                    name = state.value.cardName.getOrThrow(),
                    number = state.value.cardNumber.getOrThrow(),
                    expirationDate = state.value.expireMonth.getOrThrow() + "/" + state.value.expireYear.getOrThrow(),
                    cvv = state.value.cvv.getOrThrow(),
                    owner = state.value.cardOwner.getOrThrow()
                )
            }.onSuccess {
                addCardUseCase.invoke(it)
                setState { AddCardScreenUiState() }
                send(AddCardScreenEvent.Success)
            }.onFailure {
                setState {
                    it.copy(
                        cardName = it.cardName.validate(),
                        cardOwner = it.cardOwner.validate(),
                        cardNumber = it.cardNumber.validate(),
                        expireMonth = it.expireMonth.validate(),
                        expireYear = it.expireYear.validate(),
                        cvv = it.cvv.validate(),
                        isLoading = false
                    )
                }
            }
        }
    }

    fun setCardName(name: String) {
        setState {
            it.copy(
                cardName = it.cardName.copy(
                    value = name,
                    isValid = it.cardName.condition(name)
                )
            )
        }
    }

    fun setCardOwner(owner: String) {
        setState {
            it.copy(
                cardOwner = it.cardOwner.copy(
                    value = owner,
                    isValid = it.cardOwner.condition(owner)
                )
            )
        }
    }

    fun setCardNumber(number: String) {
        setState {
            it.copy(
                cardNumber = it.cardNumber.copy(
                    value = number,
                    isValid = it.cardNumber.condition(number)
                )
            )
        }
    }

    fun setExpireMonth(month: String) {
        setState {
            it.copy(
                expireMonth = it.expireMonth.copy(
                    value = if (month.length == 1) "0$month" else month,
                    isValid = it.expireMonth.condition(month)
                )
            )
        }
    }

    fun setExpireYear(year: String) {
        setState {
            it.copy(
                expireYear = it.expireYear.copy(
                    value = year.takeLast(2),
                    isValid = it.expireYear.condition(year)
                )
            )
        }
    }

    fun setCvv(cvv: String) {
        setState {
            it.copy(
                cvv = it.cvv.copy(
                    value = cvv,
                    isValid = it.cvv.condition(cvv)
                )
            )
        }
    }
}