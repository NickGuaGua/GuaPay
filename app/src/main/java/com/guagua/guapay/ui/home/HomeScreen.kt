package com.guagua.guapay.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.navigation.compose.rememberNavController
import com.guagua.guapay.ui.navigation.AppNavHost
import com.guagua.guapay.ui.theme.LocalColor
import com.guagua.guapay.ui.theme.LocalTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { HomeTab.entries.size }
    val navController = HomeTab.entries.associateWith { rememberNavController() }

    Column(modifier = modifier) {
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState,
            beyondViewportPageCount = pagerState.pageCount,
        ) {
            val tab = HomeTab.entries.getOrNull(it) ?: return@HorizontalPager
            AppNavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController[tab] ?: return@HorizontalPager,
                startDestination = tab.route(),
            )
        }
        HomeBottomNavigationBar(
            modifier = Modifier.fillMaxWidth(),
            tabs = HomeTab.entries,
            selectedIndex = pagerState.currentPage,
            onSelect = {
                pagerState.requestScrollToPage(it.ordinal)
            }
        )
    }
}

@Composable
private fun HomeBottomNavigationBar(
    modifier: Modifier = Modifier,
    tabs: List<HomeTab> = HomeTab.entries,
    selectedIndex: Int,
    onSelect: (HomeTab) -> Unit = {}
) {
    NavigationBar(
        modifier = modifier,
        containerColor = LocalColor.current.primary,
    ) {
        tabs.fastForEachIndexed { i, homeTab ->
            NavigationBarItem(
                modifier = Modifier.weight(1f),
                selected = selectedIndex == i,
                icon = {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(homeTab.icon(selectedIndex == i)),
                        contentDescription = stringResource(homeTab.text()),
                        tint = Color.White
                    )
                },
                label = {
                    Text(
                        text = stringResource(homeTab.text()),
                        style = LocalTypography.current.labelSmall,
                        color = LocalColor.current.text.primaryWhite.copy(
                            if (selectedIndex == i) 1f else 0.4f
                        )
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                ),
                onClick = { onSelect(homeTab) }
            )
        }
    }
}

@Preview
@Composable
fun HomeBottomNavigationBarPreview() {
    HomeBottomNavigationBar(
        modifier = Modifier.fillMaxWidth(),
        tabs = HomeTab.entries,
        selectedIndex = 4
    )
}