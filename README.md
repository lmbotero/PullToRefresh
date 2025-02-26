# Compose Multiplatform PullToRefresh

A simple and lightweight Compose Multiplatform library that implements Material3-based
pull-to-refresh functionality for JVM, Android, and iOS platforms.

## ✨ Features

- **Material 3 Pull-to-Refresh**: Built following Material 3 design principles.
- **Multiplatform Support**: Works seamlessly across Android, iOS, and JVM.
- **Mouse Support for JVM**: Allows pull-to-refresh using mouse click and drag.
- **Customizable Indicator**: Easily style or replace the default refresh indicator.

## 📦 Installation

Add the dependency to your `build.gradle.kts` (or `build.gradle` for Groovy users):

```kotlin
implementation("com.lumbot.pulltorefresh:version")
```

## 🚀 Basic Usage

Wrap your composable content inside `PullToRefreshLayout`:

```kotlin
PullToRefreshLayout(
    isRefreshing = isLoading,
    onRefresh = { /* Execute your pull-to-refresh task */ },
) {
    // Your composable content here
}
```

## 🎨 Customization

### Modify Threshold & State

You can customize the refresh threshold and state using `rememberPullToRefreshConfig`.

```kotlin
val config = rememberPullToRefreshConfig(
    enabled = true,
    threshold = 60.dp
)

PullToRefreshLayout(
    isRefreshing = isLoading,
    onRefresh = { /* execute your pull-to-refresh task */ },
    config = config
) {
    // Your composable content here
}
```

### Custom Refresh Indicator

Override the default indicator using the `indicator` parameter.

See https://developer.android.com/reference/kotlin/androidx/compose/material3/pulltorefresh/package-summary.html
to see the official material3 `pull-to-refresh` reference

## 📚 API Reference

### PullToRefreshLayout

```kotlin
@Composable
fun PullToRefreshLayout(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    config: PullToRefreshConfig = rememberPullToRefreshConfig(),
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit
)
```

| Parameter          | Type                              | Description                                                 |
|--------------------|-----------------------------------|-------------------------------------------------------------|
| `isRefreshing`     | `Boolean`                         | Whether the content is currently refreshing                 |
| `onRefresh`        | `() -> Unit`                      | A function to execute when the content is pulled to refresh |
| `modifier`         | `Modifier`                        | Modifier for the layout                                     |
| `config`           | `PullToRefreshConfig`             | Configuration for the pull-to-refresh                       |
| `contentAlignment` | `Alignment`                       | Alignment for the content                                   |
| `content`          | `@Composable BoxScope.() -> Unit` | Composable content                                          |

### PullToRefreshConfig

```kotlin
data class PullToRefreshConfig(
    val enabled: Boolean = true,
    val threshold: Dp = PullToRefreshDefaults.PositionalThreshold,
    val state: PullToRefreshState,
)
```

### rememberPullToRefreshConfig

```kotlin
@Composable
fun rememberPullToRefreshConfig(
    enabled: Boolean = true,
    threshold: Dp = PullToRefreshDefaults.PositionalThreshold,
    state: PullToRefreshState = rememberPullToRefreshState(),
): PullToRefreshConfig
```

- Returns: A `PullToRefreshConfig` instance with saved state.

## 📜 License

This project is licensed under the MIT License. See the LICENSE file for details.

---

## 🚀 Happy coding!

This library simplifies pull-to-refresh for Compose Multiplatform, making it easy to use and
customize! 💙
