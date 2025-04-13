package com.guagua.guapay.ui.cards

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.guagua.guapay.ui.cards.add.AddCardScreen
import com.guagua.guapay.ui.cards.detail.CardDetailScreen
import com.guagua.guapay.ui.common.appbar.NavigationType
import kotlinx.coroutines.launch

sealed class CardsParam {
    data class Card(val id: String) : CardsParam()
    data object AddCard : CardsParam()
}

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun CardsTabletScreen(
    modifier: Modifier = Modifier
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<CardsParam?>(
        scaffoldDirective = calculatePaneScaffoldDirective(
            currentWindowAdaptiveInfo()
        ).copy(
            maxHorizontalPartitions = 2 // Fix samsung tablet issue
        )
    )
    val scope = rememberCoroutineScope()
    NavigableListDetailPaneScaffold(
        modifier = modifier,
        navigator = navigator,
        listPane = {
            AnimatedPane {
                CardsScreen(
                    modifier = Modifier.fillMaxSize(),
                    navigateToAddCard = {
                        scope.launch {
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail,
                                contentKey = CardsParam.AddCard
                            )
                        }
                    },
                    navigateToCardDetail = {
                        scope.launch {
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail,
                                contentKey = CardsParam.Card(it)
                            )
                        }
                    }
                )
            }
        },
        detailPane = {
            val param = navigator.currentDestination?.contentKey

            AnimatedPane {
                AnimatedContent(
                    param,
                    transitionSpec = {
                        if (targetState == initialState) {
                            slideInVertically { it } + fadeIn() togetherWith slideOutVertically { it } + fadeOut()
                        } else {
                            slideInHorizontally { it } + fadeIn() togetherWith slideOutHorizontally { it } + fadeOut()
                        }
                    }
                ) {
                    when (it) {
                        CardsParam.AddCard -> {
                            AddCardScreen(
                                navigationType = NavigationType.Cancel
                            ) {
                                scope.launch {
                                    navigator.navigateTo(
                                        pane = ListDetailPaneScaffoldRole.List,
                                        contentKey = null
                                    )
                                }
                            }
                        }

                        is CardsParam.Card -> {
                            CardDetailScreen(
                                modifier = modifier.statusBarsPadding(),
                                cardId = it.id,
                                navigationType = NavigationType.Cancel,
                                onBack = {
                                    scope.launch {
                                        navigator.navigateTo(
                                            pane = ListDetailPaneScaffoldRole.List,
                                            contentKey = null
                                        )
                                    }
                                }
                            )
                        }

                        null -> {}
                    }
                }
            }
        }
    )
}