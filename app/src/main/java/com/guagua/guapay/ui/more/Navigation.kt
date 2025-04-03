package com.guagua.guapay.ui.more

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val MORE_ROUTE = "more"

fun NavController.navigateToMore() {
    navigate(MORE_ROUTE)
}

fun NavGraphBuilder.more() {
    composable(MORE_ROUTE) {
        MoreScreen()
    }
}