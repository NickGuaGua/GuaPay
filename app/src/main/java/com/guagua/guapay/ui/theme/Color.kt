package com.guagua.guapay.ui.theme

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
        _700 = Color(0xFF264D4F),
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

    val greenDark = Palette(
        _0 = green._900,
        _100 = green._800,
        _200 = green._700,
        _300 = green._600,
        _400 = green._500,
        _500 = green._400,
        _600 = green._300,
        _700 = green._200,
        _800 = green._100,
        _900 = green._0,
    )

    val grayDark = Palette(
        _0 = gray._900,
        _100 = gray._800,
        _200 = gray._700,
        _300 = gray._600,
        _400 = gray._500,
        _500 = gray._400,
        _600 = gray._300,
        _700 = gray._200,
        _800 = gray._100,
        _900 = gray._0,
    )

    val orangeDark = Palette(
        _0 = orange._900,
        _100 = orange._800,
        _200 = orange._700,
        _300 = orange._600,
        _400 = orange._500,
        _500 = orange._400,
        _600 = orange._300,
        _700 = orange._200,
        _800 = orange._100,
        _900 = orange._0,
    )

    val redDark = Palette(
        _0 = red._900,
        _100 = red._800,
        _200 = red._700,
        _300 = red._600,
        _400 = red._500,
        _500 = red._400,
        _600 = red._300,
        _700 = red._200,
        _800 = red._100,
        _900 = red._0,
    )

    val hintText = Color(0xFFB8B8B8)
    val textInputBorder = Color(0xFFD5D5D5)
}

val LocalColor = staticCompositionLocalOf<AppPalette> { AppPalette.Light() }

sealed class AppPalette {
    abstract val base: Base
    abstract val text: Text
    abstract val primary: Color
    abstract val tertiary: Color

    data class Base(
        val green: Palette,
        val gray: Palette,
        val orange: Palette,
        val red: Palette
    )

    data class Text(
        val primaryBlack: Color,
        val primaryWhite: Color,
        val secondary: Color
    )

    data class Light(
        override val base: Base = Base(
            green = AppColor.green,
            gray = AppColor.gray,
            orange = AppColor.orange,
            red = AppColor.red
        ),
        override val text: Text = Text(
            primaryBlack = Color.Black,
            primaryWhite = Color.White,
            secondary = AppColor.hintText
        ),
        override val primary: Color = AppColor.green._800,
        override val tertiary: Color = AppColor.orange._400
    ) : AppPalette()

    data class Dark(
        override val base: Base = Base(
            green = AppColor.greenDark,
            gray = AppColor.grayDark,
            orange = AppColor.orangeDark,
            red = AppColor.redDark
        ),
        override val text: Text = Text(
            primaryBlack = Color.White,
            primaryWhite = Color.Black,
            secondary = AppColor.gray._300
        ),
        override val primary: Color = AppColor.greenDark._200,
        override val tertiary: Color = AppColor.orangeDark._300
    ) : AppPalette()
}

val DarkColorScheme = darkColorScheme(
    primary = AppColor.greenDark._800,
    onPrimary = Color.White,
    primaryContainer = AppColor.greenDark._700,
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