package com.guagua.guapay.ui.transactions

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val TRANSACTIONS_ROUTE = "transactions"

fun NavController.navigateToTransactions() {
    navigate(TRANSACTIONS_ROUTE)
}

fun NavGraphBuilder.transactions() {
    composable(TRANSACTIONS_ROUTE) {
        TransactionsScreen()
    }
}