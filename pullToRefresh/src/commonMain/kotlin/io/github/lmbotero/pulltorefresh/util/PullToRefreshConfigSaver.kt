package io.github.lmbotero.pulltorefresh.util

import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.ui.unit.Dp

object PullToRefreshConfigSaver {
    val saver =
        mapSaver(
            save = { config ->
                mapOf(
                    "enabled" to config.enabled,
                    "threshold" to config.threshold.value,
                )
            },
            restore = { map ->
                PullToRefreshConfig(
                    enabled = map["enabled"] as Boolean,
                    threshold = Dp(map["threshold"] as Float),
                )
            },
        )
}
