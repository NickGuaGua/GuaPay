package com.guagua.guapay.ui.statements

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val STATEMENTS_ROUTE = "statements"

fun NavController.navigateToStatements() {
    navigate(STATEMENTS_ROUTE)
}

fun NavGraphBuilder.statements() {
    composable(STATEMENTS_ROUTE) {
        StatementsScreen()
    }
}