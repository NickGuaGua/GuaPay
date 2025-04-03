package com.guagua.guapay.ui.home

import com.guagua.guapay.R
import com.guagua.guapay.ui.cards.CARDS_ROUTE
import com.guagua.guapay.ui.more.MORE_ROUTE
import com.guagua.guapay.ui.payments.PAYMENT_ROUTE
import com.guagua.guapay.ui.statements.STATEMENTS_ROUTE
import com.guagua.guapay.ui.transactions.TRANSACTIONS_ROUTE

enum class HomeTab {
    Transactions,
    Cards,
    Payments,
    Statements,
    More;
}

fun HomeTab.route() = when (this) {
    HomeTab.Transactions -> TRANSACTIONS_ROUTE
    HomeTab.Cards -> CARDS_ROUTE
    HomeTab.Payments -> PAYMENT_ROUTE
    HomeTab.Statements -> STATEMENTS_ROUTE
    HomeTab.More -> MORE_ROUTE
}

fun HomeTab.icon(selected: Boolean = false) = when (this) {
    HomeTab.Transactions -> {
        if (selected) R.drawable.ic_transactions_selected
        else R.drawable.ic_transactions_unselected
    }

    HomeTab.Cards -> {
        if (selected) R.drawable.ic_cards_selected
        else R.drawable.ic_cards_unselected
    }

    HomeTab.Payments -> {
        if (selected) R.drawable.ic_payments_selected
        else R.drawable.ic_payments_unselected
    }

    HomeTab.Statements -> {
        if (selected) R.drawable.ic_statements_selected
        else R.drawable.ic_statements_unselected
    }

    HomeTab.More -> {
        if (selected) R.drawable.ic_more_selected
        else R.drawable.ic_more_unselected
    }
}

fun HomeTab.text() = when (this) {
    HomeTab.Transactions -> R.string.transactions
    HomeTab.Cards -> R.string.cards
    HomeTab.Payments -> R.string.payments
    HomeTab.Statements -> R.string.statements
    HomeTab.More -> R.string.more
}