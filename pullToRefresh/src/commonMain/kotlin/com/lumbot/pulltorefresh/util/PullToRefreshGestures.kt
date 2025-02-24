package com.lumbot.pulltorefresh.util

import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
fun Modifier.handlePullToRefreshGestures(
    scope: CoroutineScope,
    state: PullToRefreshState,
    enabled: Boolean = true,
    onRefresh: () -> Unit,
    thresholdPx: Float,
): Modifier =
    if (enabled) {
        this.pointerInput(Unit) {
            detectVerticalDragGestures(
                onVerticalDrag = { _, dragAmount ->
                    scope.launch {
                        state.snapTo(
                            (state.distanceFraction + dragAmount / thresholdPx).coerceIn(
                                0f,
                                1f,
                            ),
                        )
                    }
                },
                onDragEnd = {
                    scope.launch {
                        if (state.distanceFraction >= 1f) {
                            onRefresh()
                        }
                        state.animateToHidden()
                    }
                },
                onDragCancel = {
                    scope.launch { state.animateToHidden() }
                },
            )
        }
    } else {
        Modifier
    }
