package com.guagua.guapay.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.guagua.guapay.ui.common.appbar.HomeAppBar
import com.guagua.guapay.ui.theme.LocalColor

@Composable
fun WipScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        HomeAppBar(
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text("Work in progress", color = LocalColor.current.text.primaryBlack)
        }
    }
}