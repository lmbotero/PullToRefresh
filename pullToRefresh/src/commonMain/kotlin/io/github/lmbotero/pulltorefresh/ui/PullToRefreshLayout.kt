package io.github.lmbotero.pulltorefresh.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import io.github.lmbotero.pulltorefresh.util.PullToRefreshConfig
import io.github.lmbotero.pulltorefresh.util.handlePullToRefreshGestures
import io.github.lmbotero.pulltorefresh.util.rememberPullToRefreshConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshLayout(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    config: PullToRefreshConfig = rememberPullToRefreshConfig(),
    state: PullToRefreshState = rememberPullToRefreshState(),
    indicator: @Composable BoxScope.() -> Unit = {
        DefaultIndicator(
            state = state,
            isRefreshing = isRefreshing,
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
                    state = state,
                    enabled = config.enabled,
                    threshold = config.threshold,
                    onRefresh = currentOnRefresh.value,
                ).handlePullToRefreshGestures(
                    scope = scope,
                    state = state,
                    enabled = config.enabled,
                    onRefresh = currentOnRefresh.value,
                    thresholdPx = thresholdPx,
                ),
        contentAlignment = contentAlignment,
    ) {
        content()
        indicator()
    }
}
