package com.guagua.guapay.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.guagua.guapay.ui.cards.cards
import com.guagua.guapay.ui.more.more
import com.guagua.guapay.ui.payments.payments
import com.guagua.guapay.ui.statements.statements
import com.guagua.guapay.ui.transactions.transactions

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        transactions()
        cards()
        payments()
        statements()
        more()
    }
}