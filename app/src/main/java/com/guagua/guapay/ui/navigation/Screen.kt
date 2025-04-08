package com.guagua.guapay.ui.navigation

sealed class Screen(val route: String) {

    data object Transaction : Screen("/transaction")

    data object Cards : Screen("/cards")

    data object CardDetail : Screen("/cards/{${NavParam.CardId}}") {
        fun createRoute(cardId: String) = "/cards/$cardId"
    }

    data object AddCard : Screen("/cards/add")

    data object Payments : Screen("/payments")

    data object Statements : Screen("/statements")

    data object More : Screen("/more")
}

object NavParam {
    const val CardId = "card_id"
}