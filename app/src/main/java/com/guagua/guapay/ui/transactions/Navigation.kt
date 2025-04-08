package com.guagua.guapay.ui.transactions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.guagua.guapay.ui.navigation.Screen

fun NavGraphBuilder.transactions() {
    composable(Screen.Transaction.route) {
        TransactionsScreen()
    }
}