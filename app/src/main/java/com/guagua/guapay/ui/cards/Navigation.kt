package com.guagua.guapay.ui.cards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val CARDS_ROUTE = "cards"

fun NavController.navigateToCards() {
    navigate(CARDS_ROUTE)
}

fun NavGraphBuilder.cards() {
    composable(CARDS_ROUTE) {
        CardsScreen(
            modifier = Modifier.fillMaxWidth()
        )
    }
}