package com.guagua.guapay.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.guagua.guapay.ui.cards.cardGraph
import com.guagua.guapay.ui.cards.navigateToAddCard
import com.guagua.guapay.ui.cards.navigateToCardDetail
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
    SharedTransitionLayout {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            transactions()
            cardGraph(
                sharedTransitionScope = this@SharedTransitionLayout,
                navigateToCardDetail = navController::navigateToCardDetail,
                navigateToAddCard = navController::navigateToAddCard,
                onBack = { navController.popBackStack() }
            )
            payments()
            statements()
            more()
        }
    }
}