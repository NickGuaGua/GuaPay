package com.guagua.guapay.ui.cards

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
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
import com.guagua.data.card.CardTag
import com.guagua.guapay.R
import com.guagua.guapay.ui.common.appbar.HomeAppBar
import com.guagua.guapay.ui.common.button.PrimaryButton
import com.guagua.guapay.ui.common.card.CardItem
import com.guagua.guapay.ui.common.card.CardUiState
import com.guagua.guapay.ui.common.extension.color
import com.guagua.guapay.ui.common.extension.fadeIn
import com.guagua.guapay.ui.common.extension.safeLet
import com.guagua.guapay.ui.common.extension.text
import com.guagua.guapay.ui.theme.AppWindowSize
import com.guagua.guapay.ui.theme.LocalColor
import com.guagua.guapay.ui.theme.LocalSpace
import com.guagua.guapay.ui.theme.LocalTypography
import com.guagua.guapay.ui.theme.LocalWindowSize
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedContentScope: AnimatedContentScope? = null,
    viewModel: CardsScreenViewModel = koinViewModel(),
    navigateToAddCard: () -> Unit,
    navigateToCardDetail: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CardsScreenContent(
        modifier = modifier,
        state = state,
        sharedTransitionScope = sharedTransitionScope,
        animatedContentScope = animatedContentScope,
        onActionButtonClick = {
            viewModel.addRandomCard()
        },
        onAddCardClick = navigateToAddCard,
        onCardClick = { card ->
            navigateToCardDetail(card.id)
        },
        onCardTagSelect = viewModel::onTagSelected
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CardsScreenContent(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedContentScope: AnimatedContentScope? = null,
    state: CardsScreenUiState,
    onActionButtonClick: () -> Unit,
    onAddCardClick: () -> Unit,
    onCardClick: (CardUiState) -> Unit,
    onCardTagSelect: (CardTag) -> Unit
) {
    val listState = rememberLazyListState()
    Column(
        modifier = modifier
    ) {
        HomeAppBar(
            modifier = Modifier.fillMaxWidth(),
            onActionClick = onActionButtonClick
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
            CardsTagDropDown(
                modifier = Modifier.fillMaxWidth(),
                tags = state.tags,
                selected = state.selectedTag,
                onSelect = {
                    onCardTagSelect(it)
                    listState.requestScrollToItem(0)
                }
            )
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
                    listState = listState,
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
private fun CardsTagDropDown(
    modifier: Modifier = Modifier,
    tags: List<CardTag> = emptyList(),
    selected: CardTag = CardTag.OTHER,
    onSelect: (CardTag) -> Unit = {}
) {
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
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(selected.color()))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = selected.text(),
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
            tags.forEach {
                DropdownMenuItem(
                    modifier = Modifier,
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(it.color())
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(it.text())
                        }
                    },
                    onClick = {
                        expanded = false
                        onSelect(it)
                    }
                )
            }
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
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedContentScope: AnimatedContentScope? = null,
    listState: LazyListState = rememberLazyListState(),
    cards: List<CardUiState>,
    onCardClick: (CardUiState) -> Unit = {},
    onAddCardClick: () -> Unit = {}
) {
    Box(modifier = modifier) {
        CardList(
            modifier = Modifier.fillMaxSize(),
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = animatedContentScope,
            listState = listState,
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
    sharedTransitionScope: SharedTransitionScope? = null,
    animatedContentScope: AnimatedContentScope? = null,
    listState: LazyListState = rememberLazyListState(),
    cards: List<CardUiState>,
    onCardClick: (CardUiState) -> Unit = {}
) {
    var selected by rememberSaveable { mutableStateOf<String?>(null) }

    val cardInterval = when (LocalWindowSize.current) {
        AppWindowSize.Expand -> 80.dp
        AppWindowSize.Medium -> 80.dp
        AppWindowSize.Compact -> 130.dp
        AppWindowSize.Small -> 80.dp
    }

    LazyColumn(
        modifier = modifier,
        state = listState,
        contentPadding = PaddingValues(
            horizontal = LocalSpace.current.margin.medium,
            vertical = LocalSpace.current.margin.compact
        ),
        verticalArrangement = Arrangement.spacedBy(-cardInterval)
    ) {
        itemsIndexed(cards, key = { index, item -> item.id }) { index, item ->
            safeLet(sharedTransitionScope, animatedContentScope) { shareScope, animateScope ->
                with(shareScope) {
                    LaunchedEffect(isTransitionActive) {
                        if (isTransitionActive.not()) {
                            selected = null
                        }
                    }

                    val contentState = rememberSharedContentState(key = item.id)

                    val margin by animateDpAsState(
                        if (selected == item.id) (cardInterval + LocalSpace.current.margin.compact) else 0.dp,
                        tween(300)
                    )

                    CardItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = margin)
                            .graphicsLayer { rotationX = -3f }
                            .sharedElement(
                                state = contentState,
                                animatedVisibilityScope = animateScope,
                            )
                            .fadeIn(),
                        state = item,
                        onClick = {
                            onCardClick(item)
                            selected = item.id
                        }
                    )
                }
            } ?: run {
                CardItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer { rotationX = -3f }
                        .fadeIn(),
                    state = item,
                    onClick = { onCardClick(item) }
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@SuppressLint("UnusedContentLambdaTargetStateParameter")
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
                onActionButtonClick = {},
                onAddCardClick = {},
                onCardClick = {},
                onCardTagSelect = {}
            )
        }
    }
}