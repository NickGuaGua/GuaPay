package com.guagua.guapay.ui.payments

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val PAYMENT_ROUTE = "payment"

fun NavController.navigateToPayments() {
    navigate(PAYMENT_ROUTE)
}

fun NavGraphBuilder.payments() {
    composable(PAYMENT_ROUTE) {
        PaymentsScreen()
    }
}