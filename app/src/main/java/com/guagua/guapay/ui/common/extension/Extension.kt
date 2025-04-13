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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

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