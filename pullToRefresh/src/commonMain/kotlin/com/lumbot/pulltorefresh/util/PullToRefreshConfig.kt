package com.lumbot.pulltorefresh.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalMaterial3Api::class)
data class PullToRefreshConfig(
    val enabled: Boolean = true,
    val threshold: Dp = PullToRefreshDefaults.PositionalThreshold,
    val state: PullToRefreshState,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberPullToRefreshConfig(
    enabled: Boolean = true,
    threshold: Dp = PullToRefreshDefaults.PositionalThreshold,
    state: PullToRefreshState = rememberPullToRefreshState(),
): PullToRefreshConfig {
    return rememberSaveable {
        PullToRefreshConfig(
            enabled = enabled,
            threshold = threshold,
            state = state,
        )
    }
}