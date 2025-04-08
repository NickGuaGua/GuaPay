package com.guagua.guapay.ui.payments

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.guagua.guapay.ui.navigation.Screen

fun NavGraphBuilder.payments() {
    composable(Screen.Payments.route) {
        PaymentsScreen()
    }
}