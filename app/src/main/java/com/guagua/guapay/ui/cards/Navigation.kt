package com.guagua.guapay.ui.cards

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.guagua.guapay.ui.cards.detail.CardDetailScreen

const val CARDS_ROUTE = "cards"

const val CARD_ID = "card_id"

fun NavController.navigateToCards() {
    navigate(CARDS_ROUTE)
}

fun NavController.navigateToCardDetail(id: String) {
    navigate("$CARDS_ROUTE/$id")
}

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.cardGraph(
    sharedTransitionScope: SharedTransitionScope,
    navigateToCardDetail: (String) -> Unit,
    onBack: () -> Unit,
) {
    composable(CARDS_ROUTE) {
        CardsScreen(
            modifier = Modifier.fillMaxWidth(),
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = this,
            onNavigate = { cardId ->
                navigateToCardDetail(cardId)
            }
        )
    }

    composable(
        "$CARDS_ROUTE/{$CARD_ID}",
        arguments = listOf(navArgument(CARD_ID) { type = NavType.StringType })
    ) {
        CardDetailScreen(
            modifier = Modifier.fillMaxWidth(),
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = this,
            onBack = onBack
        )
    }
}