package com.guagua.guapay.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.guagua.guapay.ui.cards.cardGraph
import com.guagua.guapay.ui.more.more
import com.guagua.guapay.ui.payments.payments
import com.guagua.guapay.ui.statements.statements
import com.guagua.guapay.ui.transactions.transactions

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    val throttler = remember { NavigationThrottler() }
    SharedTransitionLayout {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            transactions()
            cardGraph(
                sharedTransitionScope = this@SharedTransitionLayout,
                onNavigation = { route ->
                    navController.safeNavigate(throttler, route)
                },
                onBack = { navController.safePopBackStack(throttler) }
            )
            payments()
            statements()
            more()
        }
    }
}