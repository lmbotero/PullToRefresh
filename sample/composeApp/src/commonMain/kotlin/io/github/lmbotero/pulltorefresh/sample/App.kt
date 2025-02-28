package io.github.lmbotero.pulltorefresh.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.lmbotero.pulltorefresh.sample.theme.AppTheme
import io.github.lmbotero.pulltorefresh.ui.PullToRefreshLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun App() =
    AppTheme {
        val coroutineScope = rememberCoroutineScope()
        var isLoading by remember { mutableStateOf(false) }

        val onPullToRefresh: () -> Unit = {
            coroutineScope.launch {
                isLoading = true
                delay(5000)
                isLoading = false
            }
        }

        PullToRefreshLayout(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .windowInsetsPadding(WindowInsets.safeDrawing)
                    .padding(16.dp),
            isRefreshing = isLoading,
            onRefresh = onPullToRefresh,
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = if (isLoading) "Loading, please wait" else "Pull to refresh")
            }
        }
    }
