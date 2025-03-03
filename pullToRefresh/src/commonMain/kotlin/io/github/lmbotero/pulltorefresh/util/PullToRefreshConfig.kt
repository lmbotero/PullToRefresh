package io.github.lmbotero.pulltorefresh.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalMaterial3Api::class)
data class PullToRefreshConfig(
    val enabled: Boolean = true,
    val threshold: Dp = PullToRefreshDefaults.PositionalThreshold,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberPullToRefreshConfig(
    enabled: Boolean = true,
    threshold: Dp = PullToRefreshDefaults.PositionalThreshold,
): PullToRefreshConfig =
    rememberSaveable(saver = PullToRefreshConfigSaver.saver) {
        PullToRefreshConfig(
            enabled = enabled,
            threshold = threshold,
        )
    }
