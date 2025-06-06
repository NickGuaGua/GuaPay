package com.guagua.guapay.ui.common.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guagua.guapay.R
import com.guagua.guapay.ui.common.button.ActionBarButton
import com.guagua.guapay.ui.theme.LocalColor
import com.guagua.guapay.ui.theme.LocalSpace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    modifier: Modifier = Modifier,
    onActionClick: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = {
            ActionBarButton {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_menu_hamburger),
                    contentDescription = null,
                    tint = Color.White
                )
                Icon(
                    modifier = Modifier.size(36.dp, 16.dp),
                    painter = painterResource(R.drawable.ic_logo),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        actions = {
            ActionBarButton(
                modifier = Modifier.padding(end = LocalSpace.current.margin.compact),
                paddingValues = PaddingValues(
                    horizontal = LocalSpace.current.margin.medium,
                    vertical = LocalSpace.current.margin.small
                ),
                onClick = onActionClick
            ) {
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .clip(CircleShape)
                        .background(LocalColor.current.base.green._500),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "LC", color = Color.White, fontSize = 10.sp)
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LocalColor.current.surface.navigation,
        ),
    )
}