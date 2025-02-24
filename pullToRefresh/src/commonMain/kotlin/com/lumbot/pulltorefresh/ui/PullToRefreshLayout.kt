package com.lumbot.pulltorefresh.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.lumbot.pulltorefresh.util.handlePullToRefreshGestures

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshLayout(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    state: PullToRefreshState = rememberPullToRefreshState(),
    enabled: Boolean = true,
    threshold: Dp = PullToRefreshDefaults.PositionalThreshold,
    contentAlignment: Alignment = Alignment.TopStart,
    indicator: @Composable BoxScope.() -> Unit = {
        DefaultIndicator(
            isRefreshing = isRefreshing,
            state = state,
            threshold = threshold
        )
    },
    content: @Composable BoxScope.() -> Unit,
) {
    val scope = rememberCoroutineScope()
    val currentOnRefresh = rememberUpdatedState(onRefresh)

    val thresholdPx = with(LocalDensity.current) { threshold.toPx() }

    Box(
        modifier =
            modifier
                .pullToRefresh(
                    isRefreshing = isRefreshing,
                    state = state,
                    enabled = enabled,
                    threshold = threshold,
                    onRefresh = currentOnRefresh.value,
                )
                .handlePullToRefreshGestures(
                    scope = scope,
                    state = state,
                    enabled = enabled,
                    onRefresh = currentOnRefresh.value,
                    thresholdPx = thresholdPx
                ),
        contentAlignment = contentAlignment,
    ) {
        content()
        indicator()
    }
}
