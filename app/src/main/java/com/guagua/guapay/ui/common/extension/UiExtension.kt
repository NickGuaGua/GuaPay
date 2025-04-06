package com.guagua.guapay.ui.common.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString

@Composable
fun copyToClipboard(text: String) {
    val clipboardManager = LocalClipboardManager.current
    clipboardManager.setText(AnnotatedString(text))
}