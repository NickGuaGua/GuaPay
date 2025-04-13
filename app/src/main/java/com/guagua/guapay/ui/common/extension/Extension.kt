package com.guagua.guapay.ui.common.extension

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.guagua.data.card.CardTag
import com.guagua.guapay.R
import com.guagua.guapay.ui.theme.LocalColor

inline fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

@Composable
fun Modifier.fadeIn(): Modifier {
    val visible = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible.value = true
    }

    val alpha by animateFloatAsState(if (visible.value) 1f else 0f, tween(500))
    val offsetY by animateDpAsState(if (visible.value) 0.dp else 20.dp, tween(500))

    return this.graphicsLayer {
        this.alpha = alpha
        this.translationY = offsetY.toPx()
    }
}

@Composable
fun CardTag.text(): String = when (this) {
    CardTag.OTHER -> stringResource(R.string.my_cards)
    else -> this.name.lowercase().replaceFirstChar { it.uppercase() }
}

@Composable
fun CardTag.color(): Color {
    val palette = LocalColor.current.base
    return when (this) {
        CardTag.ALL -> Color.Transparent
        CardTag.OTHER -> palette.blueGray._300
        CardTag.FOOD -> palette.green._300
        CardTag.TRANSPORT -> palette.red._300
        CardTag.SHOPPING -> palette.orange._300
        CardTag.ENTERTAINMENT -> palette.blueGray._600
        CardTag.TRAVEL -> palette.green._600
        CardTag.HEALTH -> palette.red._600
        CardTag.EDUCATION -> palette.orange._600
    }
}