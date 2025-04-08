package com.guagua.guapay.ui.statements

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.guagua.guapay.ui.navigation.Screen

fun NavGraphBuilder.statements() {
    composable(Screen.Statements.route) {
        StatementsScreen()
    }
}