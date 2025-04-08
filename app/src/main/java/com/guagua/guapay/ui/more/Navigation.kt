package com.guagua.guapay.ui.more

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.guagua.guapay.ui.navigation.Screen

fun NavGraphBuilder.more() {
    composable(Screen.More.route) {
        MoreScreen()
    }
}