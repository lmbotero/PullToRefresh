package io.github.lmbotero.pulltorefresh.util

import androidx.compose.runtime.Immutable

@Immutable
data class RefreshStateEvent(
    val isRefreshing: Boolean,
    val onRefresh: () -> Unit,
)
