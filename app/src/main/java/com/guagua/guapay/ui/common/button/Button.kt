package com.guagua.guapay.ui.common.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.guagua.guapay.ui.theme.AppPalette
import com.guagua.guapay.ui.theme.LocalColor
import com.guagua.guapay.ui.theme.LocalSpace
import com.guagua.guapay.ui.theme.LocalTypography

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    isLoading: Boolean = false,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(LocalSpace.current.radius.compact))
            .background(LocalColor.current.tertiary)
            .clickable(enabled = isLoading.not()) { onClick() }
            .padding(14.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = LocalColor.current.text.primaryWhite,
                strokeWidth = 2.dp,
            )
        } else {
            Text(
                text = text,
                style = LocalTypography.current.bodyLarge,
                color = LocalColor.current.text.primaryWhite,
            )
        }
    }
}

@Composable
fun ActionBarButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    paddingValues: PaddingValues = PaddingValues(
        horizontal = LocalSpace.current.margin.medium,
        vertical = LocalSpace.current.margin.small
    ),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(LocalSpace.current.margin.large),
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(LocalSpace.current.radius.compact))
            .background(
                if (LocalColor.current is AppPalette.Light) {
                    LocalColor.current.base.blueGray._500
                } else LocalColor.current.surface.content
            )
            .clickable { onClick() }
            .padding(paddingValues),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement
    ) {
        content()
    }
}

@Composable
fun GuaIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}