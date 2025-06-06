package com.guagua.guapay.ui.cards.detail

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.guagua.guapay.R
import com.guagua.guapay.ui.common.appbar.DetailAppBar
import com.guagua.guapay.ui.common.appbar.NavigationType
import com.guagua.guapay.ui.common.button.GuaIconButton
import com.guagua.guapay.ui.common.card.CardDetailItem
import com.guagua.guapay.ui.common.extension.safeLet
import com.guagua.guapay.ui.theme.LocalColor
import com.guagua.guapay.ui.theme.LocalSpace
import com.guagua.guapay.ui.theme.LocalTypography
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CardDetailScreen(
    modifier: Modifier = Modifier,
    cardId: String? = null,
    viewModel: CardDetailViewModel = koinViewModel(
        key = cardId,
        parameters = { parametersOf(cardId) }
    ),
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedContentScope: AnimatedContentScope? = null,
    navigationType: NavigationType = NavigationType.Back,
    onBack: () -> Unit = {},
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CardDetailScreenContent(
        modifier = modifier.statusBarsPadding(),
        sharedTransitionScope = sharedTransitionScope,
        animatedVisibilityScope = animatedContentScope,
        state = state,
        navigationType = navigationType,
        onBack = onBack
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun CardDetailScreenContent(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedVisibilityScope: AnimatedVisibilityScope? = null,
    state: CardDetailScreenUiState,
    navigationType: NavigationType = NavigationType.Back,
    onBack: () -> Unit = {}
) {
    Column(modifier = modifier) {
        DetailAppBar(
            title = state.cardUiState?.name.orEmpty(),
            navigationType = navigationType
        ) {
            onBack()
        }

        safeLet(sharedTransitionScope, animatedVisibilityScope) { shareScope, animateScope ->
            with(shareScope) {
                state.cardUiState?.let {
                    CardDetailItem(
                        modifier = Modifier
                            .sharedElement(
                                rememberSharedContentState(key = state.cardUiState.id),
                                animatedVisibilityScope = animateScope
                            )
                            .fillMaxWidth()
                            .aspectRatio(1.6f)
                            .padding(horizontal = LocalSpace.current.margin.compact)
                            .padding(top = 4.dp),
                        state = state.cardUiState,
                    )
                    Spacer(Modifier.height(20.dp))
                    OwnerRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LocalSpace.current.margin.compact),
                        name = state.cardUiState.owner
                    )
                }
            }
        } ?: run {
            state.cardUiState?.let {
                CardDetailItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.6f)
                        .padding(horizontal = LocalSpace.current.margin.compact)
                        .padding(top = 4.dp),
                    state = state.cardUiState,
                )
                Spacer(Modifier.height(20.dp))
                OwnerRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = LocalSpace.current.margin.compact),
                    name = state.cardUiState.owner
                )
            }
        }
    }
}

@Composable
private fun OwnerRow(
    modifier: Modifier = Modifier,
    name: String,
) {
    val clipboardManager = LocalClipboardManager.current
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(LocalSpace.current.radius.medium))
            .background(LocalColor.current.base.gray._0)
    ) {
        Text(
            modifier = Modifier.padding(
                start = LocalSpace.current.margin.compact,
                top = LocalSpace.current.margin.compact,
            ),
            text = stringResource(R.string.owner_title),
            style = LocalTypography.current.bodySmall,
            color = LocalColor.current.text.primaryBlack
        )
        Row(
            modifier = Modifier.padding(
                start = LocalSpace.current.margin.compact,
                end = LocalSpace.current.margin.compact,
                bottom = LocalSpace.current.margin.compact,
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = name,
                style = LocalTypography.current.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = LocalColor.current.text.primaryBlack
            )
            GuaIconButton(
                modifier = Modifier.size(24.dp),
                onClick = { clipboardManager.setText(AnnotatedString(name)) }
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(R.drawable.ic_copy),
                    contentDescription = "copy",
                    tint = LocalColor.current.base.neutral._700
                )
            }
        }
    }
}