package com.guagua.guapay.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object AppColor {
    val green = Palette(
        _0 = Color(0xFFF2FAF9),
        _100 = Color(0xFFCCE7E6),
        _200 = Color(0xFF99CECD),
        _300 = Color(0xFF66B5B3),
        _400 = Color(0xFF339C9A),
        _500 = Color(0xFF238180),
        _600 = Color(0xFF2A6668),
        _700 = Color(0xFF235253),
        _800 = Color(0xFF1E3C3E),
        _900 = Color(0xFF162A2B),
    )
    val gray = Palette(
        _0 = Color(0xFFF4F5F6),
        _100 = Color(0xFFE3E6E8),
        _200 = Color(0xFFD5DADC),
        _300 = Color(0xFFC6CDD0),
        _400 = Color(0xFFB5BDC1),
        _500 = Color(0xFF9CA6AC),
        _600 = Color(0xFF73838C),
        _700 = Color(0xFF5D6A72),
        _800 = Color(0xFF464F55),
        _900 = Color(0xFF2E3438),
    )
    val orange = Palette(
        _0 = Color(0xFFFFF8F4),
        _100 = Color(0xFFFFE0CC),
        _200 = Color(0xFFFFC299),
        _300 = Color(0xFFFFA366),
        _400 = Color(0xFFFB7429),
        _500 = Color(0xFFE9651F),
        _600 = Color(0xFFD4571A),
        _700 = Color(0xFFBF4A14),
        _800 = Color(0xFFA83C0F),
        _900 = Color(0xFF8F2F08),
    )

    val red = Palette(
        _0 = Color(0xFFFFF8F7),
        _100 = Color(0xFFF9DEDC),
        _200 = Color(0xFFF2B8B5),
        _300 = Color(0xFFEC928E),
        _400 = Color(0xFFE46962),
        _500 = Color(0xFFB3261E),
        _600 = Color(0xFF9C1B17),
        _700 = Color(0xFF821512),
        _800 = Color(0xFF601410),
        _900 = Color(0xFF4A0E0A),
    )

    val hintText = Color(0xFFB8B8B8)
    val textInputBorder = Color(0xFFD5D5D5)
}

val LocalColorScheme = staticCompositionLocalOf<ColorScheme> { LightColorScheme }

val DarkColorScheme = darkColorScheme(
    primary = AppColor.green._800,
    onPrimary = Color.White,
    primaryContainer = AppColor.green._700,
    onPrimaryContainer = AppColor.green._100,

    secondary = AppColor.gray._300,
    onSecondary = AppColor.gray._900,
    secondaryContainer = AppColor.gray._600,
    onSecondaryContainer = AppColor.gray._100,

    tertiary = AppColor.orange._400,
    onTertiary = Color.Black,
    tertiaryContainer = AppColor.orange._600,
    onTertiaryContainer = Color.White,

    background = AppColor.gray._900,
    onBackground = AppColor.gray._100,

    surface = AppColor.gray._800,
    onSurface = AppColor.gray._100,

    error = AppColor.red._200,
    onError = AppColor.red._800,
)

val LightColorScheme = lightColorScheme(
    primary = AppColor.green._800,
    onPrimary = Color.White,
    primaryContainer = AppColor.green._100,
    onPrimaryContainer = AppColor.green._900,

    secondary = AppColor.gray._500,
    onSecondary = Color.White,
    secondaryContainer = AppColor.gray._100,
    onSecondaryContainer = AppColor.gray._800,

    tertiary = AppColor.orange._500,
    onTertiary = Color.White,
    tertiaryContainer = AppColor.orange._100,
    onTertiaryContainer = AppColor.orange._800,

    background = AppColor.gray._0,
    onBackground = AppColor.gray._900,

    surface = Color.White,
    onSurface = AppColor.gray._900,

    error = AppColor.red._500,
    onError = Color.White,
)

data class Palette(
    val _0: Color,
    val _100: Color,
    val _200: Color,
    val _300: Color,
    val _400: Color,
    val _500: Color,
    val _600: Color,
    val _700: Color,
    val _800: Color,
    val _900: Color
)