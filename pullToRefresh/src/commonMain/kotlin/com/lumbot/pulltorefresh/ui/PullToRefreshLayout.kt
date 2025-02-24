package com.lumbot.pulltorefresh.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.lumbot.pulltorefresh.util.PullToRefreshConfig
import com.lumbot.pulltorefresh.util.handlePullToRefreshGestures
import com.lumbot.pulltorefresh.util.rememberPullToRefreshConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshLayout(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    config: PullToRefreshConfig = rememberPullToRefreshConfig(),
    indicator: @Composable BoxScope.() -> Unit = {
        DefaultIndicator(
            isRefreshing = isRefreshing,
            state = config.state,
            threshold = config.threshold
        )
    },
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit,
) {
    val scope = rememberCoroutineScope()
    val currentOnRefresh = rememberUpdatedState(onRefresh)

    val thresholdPx = with(LocalDensity.current) { config.threshold.toPx() }

    Box(
        modifier =
            modifier
                .pullToRefresh(
                    isRefreshing = isRefreshing,
                    state = config.state,
                    enabled = config.enabled,
                    threshold = config.threshold,
                    onRefresh = currentOnRefresh.value,
                )
                .handlePullToRefreshGestures(
                    scope = scope,
                    state = config.state,
                    enabled = config.enabled,
                    onRefresh = currentOnRefresh.value,
                    thresholdPx = thresholdPx
                ),
        contentAlignment = contentAlignment,
    ) {
        content()
        indicator()
    }
}
