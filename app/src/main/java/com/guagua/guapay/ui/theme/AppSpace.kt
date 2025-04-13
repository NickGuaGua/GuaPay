package com.guagua.guapay.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalWindowSize = staticCompositionLocalOf<AppWindowSize> { AppWindowSize.Compact }

enum class AppWindowSize {
    Small, Compact, Medium, Expand;

    companion object {
        fun from(width: Dp): AppWindowSize {
            return when {
                width <= 320.dp -> Small
                width <= 600.dp -> Compact
                width <= 840.dp -> Medium
                else -> Expand
            }
        }
    }
}

val LocalSpace = staticCompositionLocalOf<AppSpace> { AppSpace.Compact() }

sealed class AppSpace {
    abstract val margin: Space
    abstract val radius: Space

    data class Compact(
        override val margin: Space = Space(
            small = 8.dp,
            compact = 12.dp,
            medium = 16.dp,
            large = 24.dp
        ),
        override val radius: Space = Space(
            small = 2.dp,
            compact = 4.dp,
            medium = 8.dp,
            large = 12.dp
        )
    ) : AppSpace()
}

data class Space(
    val small: Dp,
    val compact: Dp,
    val medium: Dp,
    val large: Dp,
)

