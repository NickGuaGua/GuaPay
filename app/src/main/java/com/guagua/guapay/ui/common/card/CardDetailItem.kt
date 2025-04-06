package com.guagua.guapay.ui.common.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guagua.data.card.CardOrganization
import com.guagua.data.card.CardType
import com.guagua.guapay.R
import com.guagua.guapay.ui.common.button.GuaIconButton
import com.guagua.guapay.ui.theme.LocalSpace
import com.guagua.guapay.ui.theme.LocalTypography

@Composable
fun CardDetailItem(
    modifier: Modifier = Modifier,
    state: CardUiState,
    onClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .aspectRatio(1.6f)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .background(Color.Black)
            .border(1.dp, Color.White.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
            .padding(LocalSpace.current.margin.medium)
    ) {
        CardTitleRow(
            modifier = Modifier.fillMaxWidth(),
            name = state.name
        )

        Spacer(modifier = Modifier.weight(2f))

        CopyRow(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.card_number),
            value = state.number
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(modifier = Modifier.wrapContentHeight()) {
            CopyRow(
                modifier = Modifier.wrapContentSize(),
                title = stringResource(R.string.expire_date),
                value = state.expirationDate
            )
            Spacer(Modifier.width(56.dp))
            CopyRow(
                modifier = Modifier.wrapContentSize(),
                title = stringResource(R.string.cvv),
                value = state.cvv
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
private fun CopyRow(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    hide: Boolean = true,
    hideValue: (String) -> String = { "-" },
    onCopyClick: () -> Unit = {},
) {
    val clipboardManager = LocalClipboardManager.current

    var visible by remember { mutableStateOf(!hide) }
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = LocalTypography.current.bodySmall,
                color = Color.White,
            )
            GuaIconButton(
                modifier = Modifier.size(24.dp),
                onClick = { visible = !visible },
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = if (visible) {
                        Icons.Default.Visibility
                    } else Icons.Default.VisibilityOff,
                    contentDescription = "visibility",
                    tint = Color.White,
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (visible) value else hideValue(value),
                style = LocalTypography.current.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White,
            )
            if (visible) {
                GuaIconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = {
                        onCopyClick()
                        clipboardManager.setText(AnnotatedString(value))
                    },
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.ic_copy),
                        contentDescription = "copy",
                        tint = Color.White,
                    )
                }
            } else {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardDetailItemPreview() {
    CardDetailItem(
        modifier = Modifier.fillMaxWidth(),
        state = CardUiState(
            id = "1",
            name = "Card Name",
            "1234 1234 1234 1234",
            expirationDate = "12/25",
            cvv = "123",
            type = CardType.PHYSICAL,
            owner = "John Doe",
            organization = CardOrganization.VISA
        )
    )
}