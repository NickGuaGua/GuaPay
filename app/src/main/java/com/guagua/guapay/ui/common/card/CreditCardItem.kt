package com.guagua.guapay.ui.common.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.guagua.guapay.ui.theme.LocalColor
import com.guagua.guapay.ui.theme.LocalSpace

@Composable
fun CreditCardItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .aspectRatio(1.6f)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .background(
                if (LocalColor.current.isDarkMode()) {
                    LocalColor.current.base.neutral._100
                } else {
                    LocalColor.current.base.neutral._900
                }
            )
            .border(1.dp, Color.White.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
            .padding(LocalSpace.current.margin.medium)
    ) {
        content()
    }
}