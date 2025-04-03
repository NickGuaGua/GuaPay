package com.guagua.guapay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.guagua.guapay.ui.home.HomeScreen
import com.guagua.guapay.ui.theme.GuaPayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuaPayTheme(dynamicColor = false) {
                HomeScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}