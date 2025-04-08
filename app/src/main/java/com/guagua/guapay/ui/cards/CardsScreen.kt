package com.guagua.guapay.ui.cards

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.guagua.guapay.R
import com.guagua.guapay.ui.common.appbar.HomeAppBar
import com.guagua.guapay.ui.common.button.PrimaryButton
import com.guagua.guapay.ui.common.card.CardItem
import com.guagua.guapay.ui.common.card.CardUiState
import com.guagua.guapay.ui.navigation.Screen
import com.guagua.guapay.ui.theme.LocalColor
import com.guagua.guapay.ui.theme.LocalSpace
import com.guagua.guapay.ui.theme.LocalTypography
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    viewModel: CardsScreenViewModel = koinViewModel(),
    onNavigation: (String) -> Unit = {},
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CardsScreenContent(
        modifier = modifier,
        state = state,
        sharedTransitionScope = sharedTransitionScope,
        animatedContentScope = animatedContentScope,
        onAddCardClick = { onNavigation(Screen.AddCard.route) },
        onCardClick = { card ->
            onNavigation(Screen.CardDetail.createRoute(card.id))
        }
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun CardsScreenContent(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    state: CardsScreenUiState,
    onAddCardClick: () -> Unit,
    onCardClick: (CardUiState) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        HomeAppBar(
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier.padding(
                vertical = LocalSpace.current.margin.compact,
                horizontal = LocalSpace.current.margin.compact
            )
        ) {
            Text(
                modifier = Modifier.padding(LocalSpace.current.margin.small),
                text = stringResource(R.string.cards),
                style = LocalTypography.current.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = LocalColor.current.text.primaryBlack,
            )
            Spacer(modifier = Modifier.padding(LocalSpace.current.margin.compact))
            CardsCategoryDropDown(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.padding(LocalSpace.current.margin.small))
        }

        when {
            state.cards == null -> {
                // TODO: show loading
            }

            state.cards.isEmpty() -> {
                NoCardsContent(
                    modifier = Modifier
                        .weight(1f)
                        .padding(LocalSpace.current.margin.compact),
                    onAddCardClick = onAddCardClick
                )
            }

            else -> {
                CardsContent(
                    modifier = Modifier.weight(1f),
                    sharedTransitionScope = sharedTransitionScope,
                    animatedContentScope = animatedContentScope,
                    cards = state.cards,
                    onCardClick = onCardClick,
                    onAddCardClick = onAddCardClick
                )
            }
        }
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
private fun CardsCategoryDropDown(modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    BoxWithConstraints(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(LocalSpace.current.radius.compact))
                .background(LocalColor.current.base.gray._100)
                .clickable { expanded = true }
                .padding(
                    horizontal = LocalSpace.current.margin.medium,
                    vertical = LocalSpace.current.margin.compact
                )
        ) {
            Text(
                text = stringResource(R.string.my_cards),
                style = LocalTypography.current.titleSmall,
                color = LocalColor.current.text.primaryBlack,
            )
        }
        DropdownMenu(
            modifier = Modifier.width(maxWidth),
            expanded = expanded,
            containerColor = LocalColor.current.base.gray._100,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                modifier = Modifier,
                text = { Text("Option 1") },
                onClick = { /* Do something... */ }
            )
            DropdownMenuItem(
                text = { Text("Option 2") },
                onClick = { /* Do something... */ }
            )
        }
    }
}

@Composable
private fun NoCardsContent(
    modifier: Modifier = Modifier,
    onAddCardClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                        .aspectRatio(1.5f),
                    painter = painterResource(R.drawable.img_no_credit_cards),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    text = stringResource(R.string.no_cards_description),
                    style = LocalTypography.current.titleSmall,
                    color = LocalColor.current.text.primaryBlack,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.no_cards_cta),
                onClick = onAddCardClick
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CardsContent(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    cards: List<CardUiState>,
    onCardClick: (CardUiState) -> Unit = {},
    onAddCardClick: () -> Unit = {}
) {
    Box(modifier = modifier) {
        CardList(
            modifier = Modifier.fillMaxSize(),
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = animatedContentScope,
            cards = cards,
            onCardClick = onCardClick
        )

        AddCreditCardFab(
            modifier = Modifier
                .padding(
                    bottom = LocalSpace.current.margin.compact,
                    end = LocalSpace.current.margin.compact
                )
                .align(Alignment.BottomEnd)
                .clickable { onAddCardClick() }
        )
    }
}

@Composable
fun AddCreditCardFab(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(LocalColor.current.tertiary)
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(R.drawable.ic_credit_card),
            contentDescription = null,
            tint = LocalColor.current.text.primaryWhite
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(R.string.add_card),
            style = LocalTypography.current.bodyMedium,
            color = LocalColor.current.text.primaryWhite,
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun CardList(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    cards: List<CardUiState>,
    onCardClick: (CardUiState) -> Unit = {}
) {
    var selected by rememberSaveable { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = LocalSpace.current.margin.medium,
            vertical = LocalSpace.current.margin.compact
        ),
        verticalArrangement = Arrangement.spacedBy(-130.dp)
    ) {
        itemsIndexed(cards, key = { index, item -> item.id }) { index, item ->
            AnimateListItem(
                modifier = Modifier.graphicsLayer {
                    rotationX = -3f
                    //cameraDistance = 120 * density // 提升立體感
                }
            ) {
                Column {
                    with(sharedTransitionScope) {
                        val contentState = rememberSharedContentState(key = item.id)

                        LaunchedEffect(isTransitionActive) {
                            if (isTransitionActive.not()) {
                                selected = null
                            }
                        }

                        Column(
                            modifier = Modifier.sharedElement(
                                contentState,
                                animatedVisibilityScope = animatedContentScope,
                            )
                        ) {
                            CardItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .animateItem(),
                                state = item,
                                onClick = {
                                    onCardClick(item)
                                    selected = item.id
                                }
                            )
                            val margin by animateDpAsState(
                                if (selected == item.id) 116.dp else 0.dp, tween(300)
                            )
                            Spacer(modifier = Modifier.height(margin))
                        }
                    }

                }
            }
        }
    }
}

@Composable
private fun AnimateListItem(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val visible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible.value = true
    }

    val alpha by animateFloatAsState(if (visible.value) 1f else 0f, tween(500))
    val offsetY by animateDpAsState(if (visible.value) 0.dp else 20.dp, tween(500))

    Box(
        modifier = modifier
            .graphicsLayer {
                this.alpha = alpha
                this.translationY = offsetY.toPx()
            }
    ) {
        content()
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
fun CardsScreenContentPreview() {
    SharedTransitionLayout {
        AnimatedContent(targetState = Unit) { _ ->
            CardsScreenContent(
                modifier = Modifier.fillMaxSize(),
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedContentScope = this@AnimatedContent,
                state = CardsScreenUiState(emptyList()),
                onAddCardClick = {},
                onCardClick = {}
            )
        }
    }
}