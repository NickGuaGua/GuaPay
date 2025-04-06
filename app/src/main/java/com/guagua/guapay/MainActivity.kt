package com.guagua.guapay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import com.guagua.guapay.ui.home.HomeScreen
import com.guagua.guapay.ui.theme.GuaPayTheme
import com.guagua.guapay.ui.theme.LocalColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuaPayTheme(dynamicColor = false) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(
                        LocalColor.current.primary.toArgb(),
                    ),
                )
                HomeScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}