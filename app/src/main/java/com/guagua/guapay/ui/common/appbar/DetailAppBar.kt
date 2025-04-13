package com.guagua.guapay.ui.common.appbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guagua.guapay.ui.common.button.GuaIconButton
import com.guagua.guapay.ui.theme.LocalColor
import com.guagua.guapay.ui.theme.LocalSpace
import com.guagua.guapay.ui.theme.LocalTypography

@Composable
fun DetailAppBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationType: NavigationType = NavigationType.Back,
    onBackClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (navigationType == NavigationType.Back) {
            GuaIconButton(
                modifier = Modifier.size(48.dp),
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "back",
                    modifier = Modifier.size(24.dp),
                    tint = LocalColor.current.base.gray._300
                )
            }
        } else {
            Spacer(modifier = Modifier.width(LocalSpace.current.margin.compact))
        }

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = LocalSpace.current.margin.compact),
            text = title,
            style = LocalTypography.current.titleLarge,
            color = LocalColor.current.text.primaryBlack,
            overflow = TextOverflow.Ellipsis
        )

        if (navigationType == NavigationType.Cancel) {
            GuaIconButton(
                modifier = Modifier.size(48.dp),
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Default.Cancel,
                    contentDescription = "back",
                    modifier = Modifier.size(24.dp),
                    tint = LocalColor.current.base.gray._300
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailAppBarPreview() {
    DetailAppBar(
        title = "Detail App Bar",
        onBackClick = {}
    )
}