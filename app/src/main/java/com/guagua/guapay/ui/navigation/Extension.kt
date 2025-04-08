package com.guagua.guapay.ui.navigation

import androidx.navigation.NavController

class NavigationThrottler(private val intervalMs: Long = 500) {
    private var lastTime = 0L
    fun canTrigger(): Boolean {
        val now = System.currentTimeMillis()
        return if (now - lastTime > intervalMs) {
            lastTime = now
            true
        } else false
    }
}

fun NavController.safeNavigate(
    throttler: NavigationThrottler,
    route: String
) {
    if (throttler.canTrigger() && currentDestination?.route != route) {
        navigate(route)
    }
}

fun NavController.safePopBackStack(throttler: NavigationThrottler) {
    if (throttler.canTrigger()) {
        popBackStack()
    }
}