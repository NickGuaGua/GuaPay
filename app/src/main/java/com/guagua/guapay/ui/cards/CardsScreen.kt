package com.guagua.guapay.ui.cards

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.guagua.guapay.R
import com.guagua.guapay.ui.common.button.PrimaryButton
import com.guagua.guapay.ui.theme.LocalColor
import com.guagua.guapay.ui.theme.LocalSpace
import com.guagua.guapay.ui.theme.LocalTypography
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardsScreen(
    modifier: Modifier = Modifier,
    viewModel: CardsScreenViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CardsScreenContent(
        modifier = modifier,
        state = state,
        onAddCardClick = {}
    )
}

@Composable
private fun CardsScreenContent(
    modifier: Modifier = Modifier,
    state: CardsScreenUiState,
    onAddCardClick: () -> Unit
) {
    Column(
        modifier = modifier.padding(LocalSpace.current.margin.compact)
    ) {
        Text(
            modifier = Modifier.padding(LocalSpace.current.margin.small),
            text = stringResource(R.string.cards),
            style = LocalTypography.current.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            ),
        )
        Spacer(modifier = Modifier.padding(LocalSpace.current.margin.compact))
        CardsCategoryDropDown(modifier = Modifier.fillMaxWidth())

        when {
            state.cards == null -> {
                // TODO: show loading
            }

            state.cards.isEmpty() -> {
                NoCardsContent(
                    modifier = Modifier.weight(1f),
                    onAddCardClick = onAddCardClick
                )
            }

            else -> {
                // TODO: Show cards
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

@Preview(showBackground = true)
@Composable
fun CardsScreenContentPreview() {
    CardsScreenContent(
        modifier = Modifier.fillMaxSize(),
        state = CardsScreenUiState(emptyList()),
        onAddCardClick = {}
    )
}