package com.guagua.guapay.ui.cards

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.guagua.guapay.ui.cards.add.AddCardScreen
import com.guagua.guapay.ui.cards.detail.CardDetailScreen
import com.guagua.guapay.ui.navigation.NavParam
import com.guagua.guapay.ui.navigation.Screen

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.cardGraph(
    sharedTransitionScope: SharedTransitionScope,
    onNavigation: (String) -> Unit,
    onBack: () -> Unit,
) {
    composable(Screen.Cards.route) {
        CardsScreen(
            modifier = Modifier.fillMaxSize(),
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = this,
            onNavigation = onNavigation
        )
    }

    composable(
        Screen.CardDetail.route,
        arguments = listOf(navArgument(NavParam.CardId) { type = NavType.StringType }),
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { it } },
    ) {
        CardDetailScreen(
            modifier = Modifier.fillMaxWidth(),
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = this,
            onBack = onBack
        )
    }

    composable(
        Screen.AddCard.route,
        enterTransition = { slideInHorizontally { it } },
        exitTransition = { slideOutHorizontally { it } },
    ) {
        AddCardScreen(
            modifier = Modifier.fillMaxWidth(),
            onBack = onBack
        )
    }
}