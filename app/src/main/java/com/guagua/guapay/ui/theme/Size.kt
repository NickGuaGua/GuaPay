package com.guagua.guapay.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalWindowSize = staticCompositionLocalOf<AppWindowSize> { AppWindowSize.Compact }

enum class AppWindowSize {
    Expand, Medium, Compact, Small;

    companion object {
        fun from(width: Dp): AppWindowSize {
            return when {
                width < 320.dp -> Small
                width < 600.dp -> Compact
                width < 840.dp -> Medium
                else -> Expand
            }
        }
    }
}

object Size {
    val margin: Space = Space(
        small = 12.dp,
        compact = 16.dp,
        medium = 24.dp,
        large = 32.dp
    )
}

data class Space(
    val small: Dp,
    val compact: Dp,
    val medium: Dp,
    val large: Dp,
)

