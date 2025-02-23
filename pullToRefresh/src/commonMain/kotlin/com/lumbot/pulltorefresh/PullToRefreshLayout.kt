package com.lumbot.pulltorefresh

import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.PositionalThreshold
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshLayout(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    state: PullToRefreshState = rememberPullToRefreshState(),
    contentAlignment: Alignment = Alignment.TopStart,
    indicator: @Composable BoxScope.() -> Unit = {
        Indicator(
            modifier = Modifier.align(Alignment.TopCenter),
            isRefreshing = isRefreshing,
            state = state,
            containerColor = MaterialTheme.colorScheme.primary,
            color = MaterialTheme.colorScheme.onPrimary,
            threshold = PositionalThreshold,
        )
    },
    content: @Composable BoxScope.() -> Unit,
) {
    val scope = rememberCoroutineScope()
    val currentOnRefresh = rememberUpdatedState(onRefresh)

    val density = LocalDensity.current
    val thresholdPx = with(density) { PositionalThreshold.toPx() }

    Box(
        modifier =
            modifier
                .pullToRefresh(
                    state = state,
                    isRefreshing = isRefreshing,
                    onRefresh = currentOnRefresh.value,
                ).pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onVerticalDrag = { _, dragAmount ->
                            scope.launch {
                                state.snapTo(
                                    (state.distanceFraction + dragAmount / thresholdPx)
                                        .coerceIn(0f, 1f),
                                )
                            }
                        },
                        onDragEnd = {
                            scope.launch {
                                if (state.distanceFraction >= 1f) {
                                    currentOnRefresh.value()
                                }
                                state.animateToHidden()
                            }
                        },
                        onDragCancel = {
                            scope.launch { state.animateToHidden() }
                        },
                    )
                },
        contentAlignment = contentAlignment,
    ) {
        content()
        indicator()
    }
}
