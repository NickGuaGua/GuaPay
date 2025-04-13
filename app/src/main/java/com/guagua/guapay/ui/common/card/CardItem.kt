package com.guagua.guapay.ui.common.card

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guagua.data.card.CardOrganization
import com.guagua.data.card.CardTag
import com.guagua.data.card.CardType
import com.guagua.guapay.R
import com.guagua.guapay.ui.common.extension.color
import com.guagua.guapay.ui.theme.LocalTypography

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    state: CardUiState,
    onClick: () -> Unit = {},
) {
    CreditCardItem(
        modifier = modifier,
        onClick = onClick
    ) {
        CardTitleRow(
            modifier = Modifier.fillMaxWidth(),
            name = state.name,
            type = state.type,
            last4Digits = state.last4Digits,
            tag = state.tag
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "•••• ${state.last4Digits}",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                ),
                color = Color.White,
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.height(16.dp),
                painter = painterResource(id = state.organization.icon()),
                contentDescription = "organization",
                tint = Color.White,
            )
        }
    }
}

@Composable
fun CardTitleRow(
    modifier: Modifier = Modifier,
    name: String,
    type: CardType? = null,
    last4Digits: String? = null,
    tag: CardTag
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RippleIndicator(
            color = tag.color(),
            size = 8.dp,
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = LocalTypography.current.titleSmall,
                color = Color.White
            )
            last4Digits?.let {
                type?.let {
                    Text(
                        text = "${stringResource(type.text())} • $last4Digits",
                        style = LocalTypography.current.labelSmall,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
            }
        }

        Icon(
            modifier = Modifier.size(36.dp, 16.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "logo",
            tint = Color.White,
        )
    }
}

@Composable
fun RippleIndicator(
    color: Color,
    size: Dp,
    shape: Shape = CircleShape,
    hasRippleEffect: Boolean = true
) {
    val animationDuration = 2000
    val infiniteTransition = rememberInfiniteTransition()
    val animateAlpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0.4f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationDuration
                0f at 0
                0.4f at durationMillis / 2
                0f at durationMillis
            },
            repeatMode = RepeatMode.Restart
        )
    )

    val animateScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        if (hasRippleEffect) {
            Spacer(
                modifier = Modifier
                    .size(size)
                    .graphicsLayer(
                        alpha = animateAlpha,
                        scaleX = animateScale,
                        scaleY = animateScale
                    )
                    .clip(shape)
                    .background(color)
            )
        }
        Spacer(
            modifier = Modifier
                .size(size)
                .clip(shape)
                .background(color)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardItemPreview() {
    CardItem(
        modifier = Modifier.size(280.dp),
        state = CardUiState(
            id = "1",
            name = "Card Name",
            "1234 1234 1234 1234",
            expirationDate = "12/25",
            cvv = "123",
            type = CardType.PHYSICAL,
            owner = "John Doe",
            organization = CardOrganization.VISA,
            tag = CardTag.OTHER,
        )
    )
}